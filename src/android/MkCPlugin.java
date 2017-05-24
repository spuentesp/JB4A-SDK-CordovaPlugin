package cl.spuentes.mkcplugin;

import android.app.Application;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import android.util.Log;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.exacttarget.etpushsdk.ETException;
import com.exacttarget.etpushsdk.ETPush;
import com.exacttarget.etpushsdk.ETPushConfig;
import com.exacttarget.etpushsdk.ETPushConfigureSdkListener;
import com.exacttarget.etpushsdk.ETRequestStatus;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This class echoes a string called from JavaScript.
 */
public class MkCPlugin extends CordovaPlugin {
    
    private Application application = this.cordova.getActivity().getApplication();
    JSONObject msg;
    private ETPush etPush;
    private String TAG = "MkCPlugin";
    Context context;
    
    private static boolean isActive;
    public static Activity mainActivity;
    private static CordovaWebView gWebView;

    ETPushConfigureSdkListener listener = new ETPushConfigureSdkListener() {
        @Override
        public void onETPushConfigurationSuccess(ETPush etPush, ETRequestStatus etRequestStatus) {
            Log.i(TAG, "onETPushConfigurationSuccess");
            Log.i(TAG, "GooglePlayServiceStatusCode :: " + String.valueOf(etRequestStatus.getGooglePlayServiceStatusCode()));
            Log.i(TAG, "GoogleApiAvailability :: " + String.valueOf(GoogleApiAvailability.getInstance().isUserResolvableError(etRequestStatus.getGooglePlayServiceStatusCode())));

            // Verify Google Play Services availability and notify the user of any exceptions
            if (etRequestStatus.getGooglePlayServiceStatusCode() != ConnectionResult.SUCCESS && GoogleApiAvailability.getInstance().isUserResolvableError(etRequestStatus.getGooglePlayServiceStatusCode())) {
                Log.e(TAG, "GoogleApiERROR");
                GoogleApiAvailability.getInstance().showErrorNotification(application.getApplicationContext(), etRequestStatus.getGooglePlayServiceStatusCode());
            }
        }

        @Override
        public void onETPushConfigurationFailed(ETException e) {
            // If we're here then your application will _NOT_ receive push notifications.
            Log.e(TAG, "onETPushConfigurationFailed");
            Log.e(TAG, e.getMessage());
        }
    };



    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.e(TAG, "TEST INIT");
        init();
    }
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        
        if (action.equals("getDeviceId")) {
            getDeviceId(null,callbackContext);
            return true;
        }
        if (action.equals("getSDKState")) {
            getSDKState(null,callbackContext);
            return true;
        }
        return false;
    }
    
    private void getDeviceId(String message, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                callbackContext.error("ETPush not initialized");
            }
        }
        callbackContext.success(etPush.getDeviceId());
    }
    
    private void getSDKState(String message, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                callbackContext.error("ETPush not initialized");
            }
        }
        callbackContext.success(etPush.getSDKState());
    }

    private void init(){

        if(context == null){
            context = this.cordova.getActivity().getApplicationContext();
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
            ETPushConfig config = new ETPushConfig.Builder(application)
                    .setEtAppId(bundle.getString("ETApplicationID"))
                    .setAccessToken(bundle.getString("AccessToken"))
                    .setGcmSenderId(bundle.getString("GCMSenderID"))
                    .setAnalyticsEnabled(Boolean.valueOf(bundle.getString("UseAnalytics")))    // ET Analytics, default = false
                    .setWamaEnabled(Boolean.valueOf(bundle.getString("UseGeofences")))
                    .build();
            etPush.configureSdk(config, listener);
        } catch (ETException e) {
            Log.e(TAG, e.getMessage());
        }  catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}

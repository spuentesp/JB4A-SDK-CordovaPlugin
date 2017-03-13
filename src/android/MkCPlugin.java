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
    
    private Application application;
    JSONObject msg;
    private ETPush etPush;
    private String TAG = "MkCPlugin";
    Context context;
    
    private static boolean isActive;
    public static Activity mainActivity;
    private static CordovaWebView gWebView;
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        application = this.cordova.getActivity().getApplication();
    }
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        
        if (action.equals("getDeviceId")) {
            getDeviceId(message,callbackContext);
            return true;
        }
        if (action.equals("getSDKState")) {
            getSDKState(message,callbackContext);
            return true;
        }
        if (action.equals("initMkC")) {
            String message = args.getString(0);
            this.initMkC(message, callbackContext);
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
    
    private void initMkC(String message, CallbackContext callbackContext) {
        ETPushConfig config;
        if(context == null){
            context = this.cordova.getActivity().getApplicationContext();
        }
        
        
        try {
            
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
            String appID;
            String accessToken;
            String gcmSenderID;
            
                
            if(msg != null && msg.length() > 0) {
                
                appID = bundle.getString("ETApplicationID");
                accessToken = bundle.getString("AccessToken");
                gcmSenderID = bundle.getString("GCMSenderID");
                
                Log.i(TAG, appID);
                Log.i(TAG, accessToken);
                Log.i(TAG, gcmSenderID);
                
                config = new ETPushConfig.Builder(application)
                .setEtAppId(appID)
                .setAccessToken(accessToken)
                .setGcmSenderId(gcmSenderID)
                .setAnalyticsEnabled(true)    // ET Analytics, default = false
                .setWamaEnabled(true)
                .build();
                
                
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
                etPush.configureSdk(config, listener);
                callbackContext.success("etPush sdk Configured successfully");
            }else{
                callbackContext.error("Incorrect parameters");
            }
        } catch (ETException e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error("Error during etPush Configuration");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error("Incorrect parameters");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error("Unknown Error");
        }
        
        
    }
}

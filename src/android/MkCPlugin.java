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
        if (action.equals("initMkC")) {
            this.initMkC(null, callbackContext);
            return true;
        }
        if (action.equals("registerForNotifications")) {
            this.registerForNotifications(null, callbackContext);
            return true;
        }
        if (action.equals("setSubscriberKey")) {
            this.setSubscriberKey(null, callbackContext);
            return true;
        }
        if (action.equals("getSubscriberKey")) {
            this.getSubscriberKey(null, callbackContext);
            return true;
        }
        if (action.equals("addAttribute")) {
            this.addAttribute(null, callbackContext);
            return true;
        }
        if (action.equals("removeAttribute")) {
            this.initMkC(null, callbackContext);
            return true;
        }
        if (action.equals("addTag")) {
            this.addTag(null, callbackContext);
            return true;
        }
        if (action.equals("getTags")) {
            this.getTags(null, callbackContext);
            return true;
        }
        return false;
    }
    
    private void getDeviceId(String message, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        callbackContext.success(etPush.getDeviceId());
    }
    
    private void getSDKState(String message, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        callbackContext.success(etPush.getSDKState());
    }

    private void initMkC(String message, CallbackContext callbackContext) {


        if(context == null){
            context = this.cordova.getActivity().getApplicationContext();
        }
        try {
            application = this.cordova.getActivity().getApplication();
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;


            ETPushConfig config = new ETPushConfig.Builder(application)
                    .setEtAppId(bundle.getString("ETApplicationID"))
                    .setAccessToken(bundle.getString("ETAccessToken"))
                    .setGcmSenderId(bundle.getString("GCMSenderID"))
                    .setAnalyticsEnabled(Boolean.valueOf(bundle.getString("UseAnalytics")))    // ET Analytics, default = false
                    .setWamaEnabled(Boolean.valueOf(bundle.getString("UseGeofences")))
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
            Log.i(TAG, "Configuring SDK");
            etPush.configureSdk(config, listener);
            callbackContext.success("Configuring SDK");
        } catch (ETException e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }  catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }


    private void registerForNotificatons(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        callbackContext.success(etPush.getSDKState());
    }

    private void setSubscriberKey(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{
            etPush.setSubscriberKey(args.getString(0));
            callbackContext.success("subscriber key set::"+args.getString(0));
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }


    }

    private void getSubscriberKey(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{
            callbackContext.success(etPush.getSubscriberKey());

        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }

    private void addAttribute(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{
            etPush.addAttribute(args.getString(0));
            callbackContext.success("Attribute set::"+args.getString(0));
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }

    private void removeAttribute(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{
            etPush.removeAttribute(args.getString(0));
            callbackContext.success("attribute removed::"+args.getString(0));
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }

    private void getAttributes(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{
            callbackContext.success(etPush.getAttributes());
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }

    private void addTag(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{
            etPush.addTag(args.getString(0));
            callbackContext.success("tag added::"+args.getString(0));
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }

    private void removeTag(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{

            etPush.removeTag(args.getString(0));
            callbackContext.success("tag removed::"+args.getString(0));
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }

    private void getTags(JSONArray args, CallbackContext callbackContext){
        if(etPush == null){
            try {
                etPush = ETPush.getInstance();
            } catch (ETException e) {
                Log.e(TAG, e.getMessage());
                callbackContext.error(e.getMessage());
            }
        }
        try{
            callbackContext.success(etPush.getTags());
        }catch (Exception e) {
            Log.e(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }

}

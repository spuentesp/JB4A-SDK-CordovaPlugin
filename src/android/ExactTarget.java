package com.atsistemas.exacttarget;

import android.app.Application;

import com.exacttarget.etpushsdk.ETException;
import com.exacttarget.etpushsdk.ETPush;
import com.exacttarget.etpushsdk.ETPushConfig;
import com.exacttarget.etpushsdk.ETPushConfigureSdkListener;
import com.exacttarget.etpushsdk.ETRequestStatus;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ExactTarget extends CordovaPlugin implements ETPushConfigureSdkListener {

    private Application application;
    private CallbackContext tmpCallbackContext;
    private ETPush etPush;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.application = cordova.getActivity().getApplication();
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("configurePush")) {
            this.configurePush(data, callbackContext);
            return true;
        } else if (action.equals("setAttributes")) {
            this.setAttributes(data, callbackContext);
            return true;
        } else {
            return false;
        }
    }

    private void configurePush(JSONArray data, CallbackContext callbackContext) throws JSONException {

        this.tmpCallbackContext = callbackContext;

        try {
            ETPush.configureSdk(new ETPushConfig.Builder(this.application)
                            .setEtAppId(data.getJSONObject(0).getString("appId"))
                            .setAccessToken(data.getJSONObject(0).getString("accessToken"))
                            .setGcmSenderId(data.getJSONObject(0).getString("gcmSenderId"))
                            .setAnalyticsEnabled(false) // ET Analytics
                            .setWamaEnabled(false)      // Web & Mobile Analytics
                            .build()
                    , this);

        } catch (ETException e) {
            callbackContext.error(e.getMessage());
        } catch (NullPointerException e) {
            callbackContext.error("It seems the push service has been configured too late. " +
                "It must be configured in the first seconds of the app launching.");
        }
    }

    private void setAttributes(JSONArray data, CallbackContext callbackContext) throws JSONException {

        JSONObject object = data.getJSONObject(0);
        Iterator<String> keys = object.keys();

        try {

            while( keys.hasNext() ) {
                String key = keys.next();
                this.etPush.addAttribute(key, object.getString(key));
            }

            callbackContext.success();

        } catch (ETException e) {
            callbackContext.error(e.getMessage());
        }
    }

    @Override
    public void onETPushConfigurationSuccess(ETPush etPush, ETRequestStatus etRequestStatus) {

        // Verify Google Play Services availability and notify the user of any exceptions
        GoogleApiAvailability gaa = GoogleApiAvailability.getInstance();
        int status = etRequestStatus.getGooglePlayServiceStatusCode();

        if (status == ConnectionResult.SUCCESS || gaa.isUserResolvableError(status)) {
            this.etPush = etPush;
            this.tmpCallbackContext.success();
        } else {
            this.tmpCallbackContext.error("It seems Google Play Services are not available.");
        }
    }

    @Override
    public void onETPushConfigurationFailed(ETException e) {
        this.tmpCallbackContext.error(e.getMessage());
    }
}
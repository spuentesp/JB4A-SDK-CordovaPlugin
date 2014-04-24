package com.exacttarget.hybridPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class SdkWrapper extends CordovaPlugin {
	
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize((CordovaInterface) cordova, webView);
    }
	
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("test")) {
        	Log.v("test", "test");
            callbackContext.success();
        }
        else {
            return false;
        }
        return true;
    }
    
}

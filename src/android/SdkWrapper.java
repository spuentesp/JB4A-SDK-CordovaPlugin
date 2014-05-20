package com.exacttarget.hybridPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import com.exacttarget.etpushsdk.ETException;
import com.exacttarget.etpushsdk.ETLocationManager;
import com.exacttarget.etpushsdk.ETPush;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class SdkWrapper extends CordovaPlugin implements OnSharedPreferenceChangeListener{
	
	private static final String TAG = "SDKWrapper";
	Context context;
	public static Activity mainActivity;
    public SdkWrapper () {
        
    }
    
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize((CordovaInterface) cordova, webView);
    }
	
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        PluginResult.Status status = PluginResult.Status.OK;
        String result = "";
        Log.v("action", action);
        if(context == null)
        	context =this.cordova.getActivity().getApplicationContext();
		
		if (action.equals("test")) {
        	float f = 2;
        	callbackContext.sendPluginResult(new PluginResult(status, f));
            return true;
        }
        else if(action.equals("enablePush"))
        {
            TogglePush(true);
        }
        else if(action.equals("disablePush"))
        {
            TogglePush(false);
        }
        else if(action.equals("enableGeoLocation"))
        {
        	ToggleGeoLocation(true);
        }
        else if(action.equals("disableGeoLocation"))
        {
        	ToggleGeoLocation(false);
        }
        else if(action.equals("addAttributes"))
        {
        	Log.v("console", args.getString(0) + args.getString(1));
    		try {
                ETPush.pushManager().addAttribute(args.getString(0), args.getString(1));
    		}
    		catch (ETException e) {
                Log.e(TAG, e.getMessage(), e);
    		}
        }
        else if(action.equals("removeAttributes"))
        {
    		try {
                ETPush.pushManager().removeAttribute(args.getString(0));
    		}
    		catch (ETException e) {
                Log.e(TAG, e.getMessage(), e);
    		}
        }
        else if(action.equals("addTag"))
        {
    		try {
                ETPush.pushManager().addTag(args.getString(0));
    		}
    		catch (ETException e) {
                Log.e(TAG, e.getMessage(), e);
    		}
        }
        else if(action.equals("removeTag"))
        {
    		try {
                ETPush.pushManager().removeTag(args.getString(0));
    		}
    		catch (ETException e) {
                Log.e(TAG, e.getMessage(), e);
    		}
        }
        else if(action.equals("register"))
        {
            register();
        }
        else if(action.equals("setSubscriberKey"))
        {
            try {
				ETPush.pushManager().setSubscriberKey(args.getString(0));
			} catch (ETException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else {
            return false;
        }
        try {
			ETPush.pushManager().enablePush(null);
		} catch (ETException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return true;
    }
    
    public void register()
    {
    	context =this.cordova.getActivity().getApplicationContext();
        try {
        	ETPush.setLogLevel(Log.DEBUG);
            //This method sets up the ExactTarget mobile push system
            ETPush.readyAimFire(context, "8dd3c0b3-bdbf-4bb8-8ddc-73bddba010a4",
                                "7sq6z6g83rwgbjbqe3ddpysa", false, false, false);
            ETPush pushManager = ETPush.pushManager();
            pushManager.setGcmSenderID("5671317166");
            //A good practice is to add the versionName of your app from the manifest as a tag
            //so you can target specific app versions with a push message later if necessary.
            //String versionName = getPackageManager().getPackageInfo(getPackageName(),
            //0).versionName;
            //pushManager.addTag(versionName);
        }
        catch (ETException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        
        // enable push manager
        try {
            if(!ETPush.pushManager().isPushEnabled()) {
                ETPush.pushManager().enablePush(null);
            }
        }
        catch (ETException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        
    }
    
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        Log.d(TAG, "Preference changed: " + key);
        try {
            if ("pref_notify".equals(key)) {
                boolean pushEnabled = sharedPreferences.getBoolean(key, true);
                if (pushEnabled) {
                    ETPush.pushManager().enablePush(mainActivity);
                }
                else {
                    ETPush.pushManager().disablePush(mainActivity);
                }
            }
            else if (key.startsWith("pref_")) {
                // for other standard prefs, do nothing special here.
            }
            else {
                try {
                    // they changed a building subscription, add it as a tag.
                    boolean subscribed = sharedPreferences.getBoolean(key, false);
                    if (subscribed) {
                        ETPush.pushManager().addTag(key);
                    }
                    else {
                        ETPush.pushManager().removeTag(key);
                    }
                }
                catch(Throwable e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        }
        catch (ETException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
    
    
    public void reRegister()
    {
        try {
            if (ETPush.pushManager().isPushEnabled()) {
                ETPush.pushManager().enablePush(mainActivity);
            }
        }
        catch (ETException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
    
    public void setTags(String Tags)
    {
        String[] splitTags = Tags.split(",");
        for(int i=0; i < splitTags.length; i++)
        {
            try {
                ETPush.pushManager().addTag(splitTags[i]);
            } catch (ETException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }
    
    public void ToggleGeoLocation(boolean enabled)
    {
        if(enabled)
        {
            try {
                if(!ETLocationManager.locationManager().isWatchingLocation()) {
                    Log.d(TAG, "Geo enabled");
                    ETLocationManager.locationManager().startWatchingLocation();
                }
            }
            catch(ETException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        else
        {
            try {
                if(ETLocationManager.locationManager().isWatchingLocation()) {
                    Log.d(TAG, "Geo Disbaled");
                    ETLocationManager.locationManager().stopWatchingLocation();;
                }
            }
            catch(ETException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }
    
    public void TogglePush(boolean enablePush)
    {
        if(enablePush)
        {
            // enable push manager
            try {
                if(!ETPush.pushManager().isPushEnabled()) {
                    ETPush.pushManager().enablePush(mainActivity);
                }
            }
            catch (ETException e) {
                Log.e("catch", e.getMessage(), e);
            }
        }
        else
        {
            try {
                if(ETPush.pushManager().isPushEnabled()) {
                    ETPush.pushManager().disablePush(mainActivity);
                }
            }
            catch (ETException e) {
                Log.e("catch", e.getMessage(), e);
            }
            
        }
    }
    
    
    
}

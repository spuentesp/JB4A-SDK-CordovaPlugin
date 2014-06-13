package com.exacttarget.hybridPlugin;

import java.util.HashMap;

import org.json.JSONObject;

import com.exacttarget.etpushsdk.ETPush;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class PushNotificationRecipient extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle payload = intent.getBundleExtra("payload");
        
		HashMap<String, String> map = new HashMap<String, String>();
        
		JSONObject jo = new JSONObject();
		try {
			for (String key : payload.keySet()) {
				jo.put(key, payload.get(key));
			}
		}
		catch (Exception e) {
			if (ETPush.getLogLevel() <= Log.ERROR) {
				Log.e("NotificationTapHandler", e.getMessage(), e);
			}
		}
		SdkWrapper.sendPushPayload(jo);
        
        
		finish();
        
		if (!SdkWrapper.isActive()) {
			forceMainActivityReload();
		}
	}
	
	@Override
	protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle payload = intent.getBundleExtra("payload");
        
        HashMap<String, String> map = new HashMap<String, String>();
        
        JSONObject jo = new JSONObject();
        try {
            for (String key : payload.keySet()) {
                jo.put(key, payload.get(key));
            }
        }
        catch (Exception e) {
            if (ETPush.getLogLevel() <= Log.ERROR) {
                Log.e("NotificationTapHandler", e.getMessage(), e);
            }
        }
        SdkWrapper.sendPushPayload(jo);
        
	}
    
    
	/**
	 * Forces the main activity to re-launch if it's unloaded.
	 */
	private void forceMainActivityReload()
	{
		PackageManager pm = getPackageManager();
		Intent launchIntent = pm.getLaunchIntentForPackage(getApplicationContext().getPackageName());    		
		startActivity(launchIntent);
		
		
	}
    
    
}

ETPushSDK plugin
===============

Cordova plugin that uses that implements the ExactTarget mobile push sdk to add push functionality to your Phonegap or Cordova applications.


Version History
============
# ExactTarget MobilePush SDK for iOS

This is the git repository for the ET MobilePush SDK for iOS.

For more information, please see [Code@ExactTarget](http://code.exacttarget.com), or visit the online documentation at [here](http://exacttarget.github.io/MobilePushSDK-CordovaPlugin/).

## Release History

### Version 1.0.1
_Released June 19, 2014_

* Custom key functionality and callbacks to Javascript from native added.

### Version 1.0.0
_Released June 10, 2014_

* First public version live
* Uses ios sdk version 3.1.1 and android version 3.1.2

Installing the Plugin
=====================

Follow these [instructions](https://code.exacttarget.com/mobilepush/integrating-mobilepush-sdk-your-ios-mobile-app#How) for ios to provision and setup your [Code@ExactTarget](http://code.exacttarget.com) apps

Follow these [instructions](https://code.exacttarget.com/mobilepush/integrating-mobilepush-sdk-your-android-mobile-app#How) for android to provision and setup your [Code@ExactTarget](http://code.exacttarget.com) apps

Once provisioning and code@ apps are setup we can install the plugin with the following command:
**be sure to replace the values below with your code@ app ids/access tokens and the gcm sender ids.

https://github.com/exacttarget/MobilePushSDK-CordovaPlugin --variable DEVAPPID='427c085f-5358944f2-a8f7-bbc5150c77c5' --variable DEVACCESSTOKEN='yay73bzx6eygw8ypaqr67fvt' --variable PRODAPPID='35a19ebc-50ae-4ed5-9d6c-404290ada3cd' --variable PRODACCESSTOKEN='cghknp9rjrmk9pkf6qh392u3' --variable GCMSENDERIDDEV='123456' --variable GCMSENDERIDPROD='123456' --variable USEGEO='true' --variable USEANALYTICS='true'

## Android Installation

##### add the android gcm library to your project

1. via terminal use comand 'android' this will open the android sdk manager

2. under extras choose the google play services library

3. update the GCM library with the command 'android update lib-project --path path/to/android/sdk/lib/project --target 'android api version you want to target' ' you can get a list of the currently installed targets with command 'android list targets'

4. add 'android.library.reference.2=path/to/android/sdk/installation/extras/google/google_play_services/libproject/google-play-services_lib' to the android platforms local.properties file
''* **note the number after reference as this needs to be above any current references that are in your local.properties or project.properties files**

##### add the following lines of code to the main activity class in your project usually patforms/android/src/packageName/projectname/class.java this is needed for analytics

`
@Override 
protected void onResume() {
	super.onResume(); 
	try {
		ETPush.pushManager().activityResumed(this); 
	}
	catch (ETException e) { 
	Log.e(TAG, e.getMessage(), e);
	}
} 

@Override
protected void onPause() { 
	super.onPause();
	try { 
		ETPush.pushManager().activityPaused(this);
	} 
	catch (ETException e) {
		Log.e(TAG, e.getMessage(), e); 
	}
}
`

## ios installation

##### add the following to the appDelegate.m file in the did finish with lauching with options function

`
NSBundle* mainBundle = [NSBundle mainBundle];
    NSDictionary* ETSettings = [mainBundle objectForInfoDictionaryKey:@"ETAppSettings"];
    BOOL useGeoLocation = [[ETSettings objectForKey:@"UseGeofences"] boolValue];
    BOOL useAnalytics = [[ETSettings objectForKey:@"UseAnalytics"] boolValue];
#ifdef DEBUG
    NSString* devAppID = [ETSettings objectForKey:@"ApplicationID-Dev"];
    NSString* devAccessToken = [ETSettings objectForKey:@"AccessToken-Dev"];
    //use your debug app id and token you setup in code.exacttarget.com here
    [[ETPush pushManager] configureSDKWithAppID:devAppID
                                 andAccessToken:devAccessToken
                                  withAnalytics:useAnalytics
                            andLocationServices:useGeoLocation
                                  andCloudPages:NO];
    
#else
    NSString* prodAppID = [ETSettings objectForKey:@"ApplicationID-Prod"];
    NSString* prodAccessToken = [ETSettings objectForKey:@"AccessToken-Prod"];
    //use your production app id and token you setup in code.exacttarget.com here
    
    [[ETPush pushManager] configureSDKWithAppID:prodAppID
                                 andAccessToken:prodAccessToken
                                  withAnalytics:useAnalytics
                            andLocationServices:useGeoLocation
                                  andCloudPages:NO];
    
#endif
    
    [[ETPush pushManager] registerForRemoteNotificationTypes:UIRemoteNotificationTypeAlert|UIRemoteNotificationTypeBadge| UIRemoteNotificationTypeSound];
    [[ETPush pushManager] shouldDisplayAlertViewIfPushReceived:YES];
    [[ETPush pushManager] applicationLaunchedWithOptions:launchOptions];
    NSString* token = [[ETPush pushManager] deviceToken];
    NSString* deviceID = [ETPush safeDeviceIdentifier]; NSLog(@"token %@", token);
    NSLog(@"Device ID %@", deviceID);
    if (useGeoLocation) {
        [[ETLocationManager locationManager] startWatchingLocation]; 
    }
`

##### add the following functions to the appDelegate.m file

`
 (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    [[ETPush pushManager] registerDeviceToken:deviceToken];
}

- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error {
    
    [[ETPush pushManager] applicationDidFailToRegisterForRemoteNotificationsWithError:error];
}
-(void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
    [[ETPush pushManager] handleNotification:userInfo forApplicationState:application.applicationState];
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:userInfo
                                                       options:0
                                                         error:&error];
    if (!jsonData) {
        
        NSLog(@"jsn error: %@", error);
        
    } else {
        
        [ETSdkWrapper.etPlugin notifyOfMessage:jsonData];
    }
}
`






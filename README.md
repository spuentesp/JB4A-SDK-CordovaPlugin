# Journey Builder for APPS SDK for Cordova/PhoneGap

Cordova plugin that implements the Journey Builder for APPS SDK to add push functionality to your Phonegap or Cordova applications.

## Release History

### Version 1.1.0
_Released November 17, 2014_

* Updated to use the 3.3.0 Journey Builder for APPS SDK.
* Support for Xcode 6 added as well as iOS8.
* get methods for attributes and device information added.

### Version 1.0.1
_Released June 19, 2014_

* Custom key functionality and callbacks to Javascript from native added.

### Version 1.0.0
_Released June 10, 2014_

* First public version live
* Uses iOS SDK version 3.1.1 and android version 3.1.2

## Installing the Plugin

Follow these [instructions](https://code.exacttarget.com/mobilepush/integrating-mobilepush-sdk-your-ios-mobile-app#How) for ios to provision and setup your [Code@ExactTarget](http://code.exacttarget.com) apps

Follow these [instructions](https://code.exacttarget.com/mobilepush/integrating-mobilepush-sdk-your-android-mobile-app#How) for android to provision and setup your [Code@ExactTarget](http://code.exacttarget.com) apps

Once provisioning and [Code@ExactTarget](http://code.exacttarget.com) apps are setup we can install the plugin with the following command:
**be sure to replace the values below with your Code@ET app ids/access tokens and the GCM Sender IDs.

```Bash
cordova plugin add https://github.com/exacttarget/MobilePushSDK-CordovaPlugin 
	--variable DEVAPPID='427c085f-5358944f2-a8f7-bbc5150c77c5' 
	--variable DEVACCESSTOKEN='yay73bzx6eygw8ypaqr67fvt'
	--variable PRODAPPID='35a19ebc-50ae-4ed5-9d6c-404290ada3cd'
	--variable PRODACCESSTOKEN='cghknp9rjrmk9pkf6qh392u3'
	--variable GCMSENDERIDDEV='123456'
	--variable GCMSENDERIDPROD='123456'
	--variable USEGEO='true'
	--variable USEANALYTICS='true'
```

### Android Installation

#### Add the android gcm library to your project

1. Via terminal use comand 'android' this will open the android sdk manager

2. Under extras choose the Google Play Services library

3. Update the GCM library with the command ```android update lib-project --path path/to/android/sdk/lib/project --target '<android api version you want to target>'```. You can get a list of the currently installed targets with command ```android list targets```.

4. Add ```android.library.reference.2=relative/path/from/local.properties/to/android/sdk/installation/extras/google/google_play_services/libproject/google-play-services_lib``` to the Android platforms local.properties file.
 **Note the number after reference as this needs to be above any current references that are in your local.properties or project.properties files.**

#### Add the following lines of code to the main activity class in your project, usually ```platforms/android/src/packageName/<projectname>/<class>.java```. This is needed for analytics.

```java
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
```

### iOS Installation

#### [Changed in this fork]
There's no need to change or add anything in sorce code. All changes are implemented in a new AppDelegate Category. 






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
**be sure to replace the values below with your Code@ET app ids/access tokens.

```Bash
cordova plugin add https://github.com/exacttarget/MobilePushSDK-CordovaPlugin 
	--variable DEVAPPID='427c085f-5358944f2-a8f7-bbc5150c77c5' 
	--variable DEVACCESSTOKEN='yay73bzx6eygw8ypaqr67fvt'
	--variable PRODAPPID='35a19ebc-50ae-4ed5-9d6c-404290ada3cd'
	--variable PRODACCESSTOKEN='cghknp9rjrmk9pkf6qh392u3'
	--variable USEGEO='true'
	--variable USEANALYTICS='true'
```

### Android usage

* The push service must be configure as soon as posible, in the first seconds of the app launching.
* The devices doesn't receive push notifications after about 3 minutes since it has been configured.
* Add in `config.xml` for the android platform node:

```
    <preference name="android-minSdkVersion" value="15" />
```

* Usage:

```js
    var pushParams = {
        appId: 'appId',
        accessToken: 'accessToken',
        gcmSenderId: 'gcmSenderId'
    };

    ETPush.configurePush(pushParams, function(){
        //Success
    }, function(error){
        //Error
    });
```

### iOS Installation

#### [Changed in this fork]
There's no need to change or add anything in sorce code. All changes are implemented in a new AppDelegate Category.

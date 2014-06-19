hybridAppPlugin
===============

sdk wrapper plugin for the hybrid app

used with cordova and phonegap to bridge the gap between hybrid apps and our native sdks with a javascript interface

Cordova Setup
=============

May need to use sudo to run on the command line as an admin.

Install Node.js
http://nodejs.org/

Install phonegap with nodejs
http://phonegap.com/install/

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

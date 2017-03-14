# MkCPlugin
ExactTarget Salesforce Marketing Cloud plugin

This is a basic implementation of the JB4A SDK for salesforce.(http://salesforce-marketingcloud.github.io/JB4A-SDK-Android and http://salesforce-marketingcloud.github.io/JB4A-SDK-iOS/)

## WIP
This plugin is a work in progress. For now, it's working on iOS and Android.
Capabilities:
getSdkState
getDeviceId

## Installation

cordova plugin add https://github.com/spuentesp/JB4A-SDK-CordovaPlugin/ --variable ETAPPID=XXXXXX-XXXX-XXXX-XXXX-XXXXXXXXX --variable ACCESSTOKEN=XXXXXXXXXX --variable GCMSENDERID=XXXXXXXX --variable USEGEO=true --variable USEANALYTICS=true

## Usage

To init:

## Android
### YOU MUST CALL THE INIT METHOD AS SOON AS THE APP STARTS. THIS WILL NOT WORK IF YOU DO IT LATER.

create some callback function
```
var cback = function(a){
      console.log(a);
    };
```
And then, call MkCPlugin.initMkC using the
```
 MkCPlugin.initMkC(cback,cback);
```
## iOS
The AppDelegate methods are swizzled to start with the app. No initialization is required.


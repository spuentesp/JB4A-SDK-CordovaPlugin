# MkCPlugin
ExactTarget Salesforce Marketing Cloud plugin

This is a basic implementation of the JB4A SDK for salesforce.(http://salesforce-marketingcloud.github.io/JB4A-SDK-Android)

##WIP
Only Android support for the moment.

##Usage

To init:

###YOU MUST CALL THE INIT METHOD AS SOON AS THE APP STARTS. THIS WILL NOT WORK IF YOU DO IT LATER.

create some callback function
```
var cback = function(a){
      console.log(a);
    };
```
 
after that, create an object with the appId, Access token and Gcm sender id:
```
    var pushParams = {
      etAppId: 'XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX',
      accessToken: 'AAAAAAAAAAAAAAAAAAAAAAA',
      gcmSenderId: 'SSSSSSSSSSSS'
    };
```  
finally, call MkCPlugin.initMkC using the
```
 MkCPlugin.initMkC(pushParams,cback,cback);
```
if you wish to know about the app and its instances, call

```
MkCPlugin.getSDKState("",cback,cback)
```


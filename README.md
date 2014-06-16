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

Files
============

plugin.xml  -- defines all of the attributes of the plugin as well as links all the file names to their node counterparts and has platform specific xml editing features. This is the file you will need to update if you change the sdk version or file names changes

www/   -- This directory contains the Push.js file and is all the web specific code that will get added to the www/ folder of the platform specific folders. This is your javascript representation of the plugin for use by anyone using the plugin. If you want to add a new function useable to anyone using the plugin through their hybrid app you will need to add the js implementation here.

src/ -- Platform specific code goes in this folder and is sorted by platform

src/android/  -- Android specific code and all the android specific files and libraries to be installed by the plugin. If you update one of these files and the name would change make sure you update it in the plugin.xml. 

src/android/SDKWrapper.java  -- Android native interface of the push.js file. This file accepts the calls from the push.js calls and does something with them. Mostly just wraps the sdk. If you want to add more features you would add another if(action.equals("action")) line and then fire code off there. Then make sure the "action" matches a js function in push.js.

src/ios -- iOS specific code and libraries to be installed with the plugin.

src/ios/SDKWrapper.h .m --iOS native interface of the push.js file. This file accepts the push.js cordova exec calls and is what is responsible for interacting with the native environment and responding back to the push.js file. If you need to add a feature here you would add a new function with the exact name of what you are calling in the exec push.js method call.

Installing the Plugin
=====================

cordova plugin add https://github.exacttarget.com/Mobile/MobilePush-SDK-Cordova 
 cordova plugin add https://github.exacttarget.com/Mobile/MobilePush-SDK-Cordova --variable DEVAPPID='427c085f-5358944f2-a8f7-bbc5150c77c5' --variable DEVACCESSTOKEN='yay73bzx6eygw8ypaqr67fvt' --variable PRODAPPID='35a19ebc-50ae-4ed5-9d6c-404290ada3cd' --variable PRODACCESSTOKEN='cghknp9rjrmk9pkf6qh392u3' --variable GCMSENDERIDDEV='5671317166' --variable GCMSENDERIDPROD='5671317166' --variable USEGEO='true' --variable USEANALYTICS='true'
for installing from this repo

or 

cordova plugin add path/to/local/repo  
to install from your local repo. great for not having to commmit up your changes to reinstall every time.

to uninstall plugin
cordova plugin remove ETPushSDK

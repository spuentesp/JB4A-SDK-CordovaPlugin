var exec = require('cordova/exec');
//all functions called with the ETPush module example call:

//      var success = function(message, attributes){console.log("success " + message);};
//      var error = function(error){console.log("error " + error);};
//      ETPush.setSubscriberKey( success, error, "CustomerID123");

 module.exports = {
    //set the current subscriber key

//      ETPush.setSubscriberKey(success, error, "SubscriberKey");
    setSubscriberKey: function(SuccessCallback, errorCallback, subscriberKey) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "setSubscriberKey", [subscriberKey]);
    },
    //get the current subscriber key

//      ETPush.getSubscriberKey(success, error);
    getSubscriberKey: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    // add a tag 

//      ETPush.addTag(success, error, "NamedTag");
    addTag: function(SuccessCallback, errorCallback, tag) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "addTag", [tag]);
    },
    //remove a tag

//      ETPush.removeTag(success, error, "NamedTag");
    removeTag: function(SuccessCallback, errorCallback, tag) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "removeTag", [tag]);
    },
    //set an attribute's and set its value

//      ETPush.addAttribute(success, error, "FirstName", "Steve");
    addAttribute: function(SuccessCallback, errorCallback, name, value) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "addAttribute", [name, value]);
    },
    //remove an attributes value

//      ETPush.removeAttribute(success, error, "FirstName");
    removeAttribute: function(SuccessCallback, errorCallback, name) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "removeAttribute", [name]);
    },
    //get an attribute's value error returns if it does not exist or attribute has different name that what was provided

//      ETPush.getAttribute(success, error, "FirstName");
    getAttribute: function(SuccessCallback, errorCallback, name) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "getAttribute", [name]);
    },
    //ANDROID ONLY enable geolocation

//      ETPush.enableGeolocation(success, error);
    enableGeolocation: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "enableGeoLocation", []);
    },
    //ANDROID ONLY disable geolocation

//      ETPush.disableGeolocation(success, error);
    disableGeolocation: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "disableGeoLocation", []);
    },
    //ANDROID ONLY Opt in to push notifications

//      ETPush.enablePush(success, error);
    enablePush: function (SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "enablePush", []);
    }, 
    //ANDROID ONLY Opt out of push notifications

//      ETPush.disablePush(success, error);
    disablePush: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "disablePush", []);
    },
    //get the unique device identifier

//      ETPush.getDeviceID(success, error);
    getDeviceID: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "getDeviceID", []);
    },
    //get the APNS or GCM sending device token

//      ETPush.getDeviceToken(success, error);
    getDeviceToken: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "getDeviceToken", []);
    },
    //get the current opt in status

//      ETPush.isPushEnabled(success, error);
    isPushEnabled: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "isPushEnabled", []);
    },
    //Set a js function as a callback for when you receive a notification

//      ETPush.registerForNotifications: function(success, error, onNotificationCallBack)
//      iOS:
//      onNotificationCallBack(payload) {
//          example payload = "{"aps":{"badge":5,"sound":"default","alert":"test"},"testKey":"test","_m":"ODc6MTE0OjA"}";
//          parse the above payload for the keys you are looking for
//      }
//      Android:
//      onNotificationCallBack(payload) {
//          payload is key value pairs json
 //         payload.testKey 
//      }
    registerForNotifications: function(SuccessCallback, errorCallback, notificationRecipient)
    {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "registerForNotifications", [notificationRecipient]);
    },

    //IOS ONLY resets the badge count

 //     ETPush.resetBadgeCount(success, error);
    resetBadgeCount: function(SuccessCallback, errorCallback) {
         cordova.exec(SuccessCallback, errorCallback, "ETPush", "resetBadgeCount", []);       
    },   
};

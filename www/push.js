var exec = require('cordova/exec');

 module.exports = {
 Test: function(successCallback, errorCallback) {
    console.log("Testing etpush");
        cordova.exec(successCallback, errorCallback, "ETPush", "test", []);
    },
    //returns boolean with whether device notifications are active
    isPushEnabled: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "isPushEnabled", []);
    },
    //set the current subscriber key
    setSubscriberKey: function(SuccessCallback, errorCallback,subscriberKey, value) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    // add a tag 
    addTag: function(SuccessCallback, errorCallback, tag, value) {
        cordova.exec(successCallback, errorCallback, "ETPush", "addTag", []);
    },
    //remove a tag
    removeTag: function(SuccessCallback, errorCallback, tag) {
        cordova.exec(successCallback, errorCallback, "ETPush", "removeTag", []);
    },
    //add an attribute and set its value
    addAttribute: function(SuccessCallback, errorCallback, name, value) {
        cordova.exec(successCallback, errorCallback, "ETPush", "addAttributes", []);
    },
    //remove an attribute
    removeAttribute: function(SuccessCallback, errorCallback, name) {
        cordova.exec(successCallback, errorCallback, "ETPush", "removeAttributes", []);
    },
    //enable geolocation
    enableGeolocation: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "enableGeoLocation", []);
    },
    //disable geolocation
    disableGeolocation: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "disableGeoLocation", []);
    },
    //enable Geolocation
    enablePush: function (SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "enablePush", []);
    }, 
    //disableGeolocation
    disbalePush: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "disablePush", []);
    }
};

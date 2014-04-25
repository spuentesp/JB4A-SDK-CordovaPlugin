var exec = require('cordova/exec');

 module.exports = {
 Test: function(successCallback, errorCallback) {
    console.log("Testing etpush");
        cordova.exec(successCallback, errorCallback, "ETPush", "test", []);
    },
    register: function(successCallback, errorCallback, accessToken, appID)
    {
        
    },
    //returns boolean with whether device notifications are active
    isPushEnabled: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "isPushEnabled", []);
    },
    //returns the uniqe device identifier
    safeDeviceIdentifier: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "safeDeviceIdentifier", []);
    },
    //get the current subscriber key
    getSubscriberKey: function(SuccessCallback, errorCallback, subscriberKey) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    //set the current subscriber key
    setSubscriberKey: function(SuccessCallback, errorCallback,subscriberKey, value) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    // add a tag 
    addTag: function(SuccessCallback, errorCallback, tag, value) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    //remove a tag
    removeTag: function(SuccessCallback, errorCallback, tag) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    //return all the tags
    allTags: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    //add an attribute and set its value
    addAttribute: function(SuccessCallback, errorCallback, name, value) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    //remove an attribute
    removeAttribute: function(SuccessCallback, errorCallback, name, value) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
    //returns all attributes
    allAttributes: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ETPush", "getSubscriberKey", []);
    },
};

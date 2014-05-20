var exec = require('cordova/exec');

 module.exports = {
 Test: function(successCallback, errorCallback) {
    console.log("Testing etpush");
        cordova.exec(successCallback, errorCallback, "ETPush", "test", []);
    },
    //returns boolean with whether device notifications are active
    isPushEnabled: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "isPushEnabled", []);
    },
    //set the current subscriber key
    setSubscriberKey: function(SuccessCallback, errorCallback,subscriberKey, value) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "getSubscriberKey", [subscriberKey, value);
    },
    // add a tag 
    addTag: function(SuccessCallback, errorCallback, tag) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "addTag", [tag]);
    },
    //remove a tag
    removeTag: function(SuccessCallback, errorCallback, tag) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "removeTag", [tag]);
    },
    //add an attribute and set its value
    addAttribute: function(SuccessCallback, errorCallback, name, value) {
                alert("add " + name + " " + value);
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "addAttributes", [name, value]);
    },
    //remove an attribute
    removeAttribute: function(SuccessCallback, errorCallback, name) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "removeAttributes", [name]);
    },
    //enable geolocation
    enableGeolocation: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "enableGeoLocation", []);
    },
    //disable geolocation
    disableGeolocation: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "disableGeoLocation", []);
    },
    //enable Geolocation
    enablePush: function (SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "enablePush", []);
    }, 
    //disableGeolocation
    disbalePush: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "disablePush", []);
    }
};

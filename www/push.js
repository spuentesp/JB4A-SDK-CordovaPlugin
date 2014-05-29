var exec = require('cordova/exec');
//all functions called with the ETPush module example call:

//      var success = function(message, attributes){console.log("success " + message);};
//      var error = function(error){console.log("error " + error);};
//      ETPush.register( success, error, true, true);

 module.exports = {
    //set the current subscriber key

//      ETPush.addTag(success, error, "SubscriberKey");
    setSubscriberKey: function(SuccessCallback, errorCallback,subscriberKey) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "getSubscriberKey", [subscriberKey]);
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
                alert("add " + name + " " + value);
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "addAttributes", [name, value]);
    },
    //remove an attributes value

//      ETPush.removeAttribute(success, error, "FirstName");
    removeAttribute: function(SuccessCallback, errorCallback, name) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "removeAttributes", [name]);
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

//      ETPush.disbalePush(success, error);
    disbalePush: function(SuccessCallback, errorCallback) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "disablePush", []);
    },
    //ANDROID ONLY MUST FIRE BEFORE OTHER FUNCTIONS CAN BE CALLED fire first time device registration setup 

//      ETPush.register(success, error, true, true);
    register: function(SuccessCallback, errorCallback, analytics, locaiton) {
        cordova.exec(SuccessCallback, errorCallback, "ETPush", "register", [analytics, location]);
    },

    //IOS ONLY resets the badge count

 //     ETPush.resetBadgeCount(success, error);
    resetBadgeCount: function(SuccessCallback, errorCallback) {
         cordova.exec(SuccessCallback, errorCallback, "ETPush", "resetBadgeCount", []);       
    },   
};

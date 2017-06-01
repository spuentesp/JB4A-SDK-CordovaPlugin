

var exec = require('cordova/exec');

exports.getDeviceId = function(success, error) {
    exec(success, error, "MkCPlugin", "getDeviceId", []);
};

exports.initMkC = function( success, error) {
    exec(success, error, "MkCPlugin", "initMkC", []);
};

exports.getSDKState = function(success, error) {
    exec(success, error, "MkCPlugin", "getSDKState", []);
};

exports.registerForNotifications = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", [arg0]);
};

exports.setSubscriberKey = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "setSubscriberKey", [arg0]);
};

exports.getSubscriberKey = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", []);
};

exports.addAttribute = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", [arg0]);
};

exports.removeAttribute = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", [arg0]);
};

exports.addTag = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", [arg0]);
};

exports.getTags = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", []);
};

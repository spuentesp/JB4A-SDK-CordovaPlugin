

var exec = require('cordova/exec');

exports.getDeviceId = function(success, error) {
    exec(success, error, "MkCPlugin", "getDeviceId", []);
};

exports.initMkC = function(success, error) {
    exec(success, error, "MkCPlugin", "initMkC", []);
};

exports.getSDKState = function(success, error) {
    exec(success, error, "MkCPlugin", "getSDKState", []);
};
exports.setSubscriberKey = function(subscriberKey,success, error) {
    exec(success, error, "MkCPlugin", "setSubscriberKey", [subscriberKey]);
};
exports.getSubscriberKey = function(success, error) {
    exec(success, error, "MkCPlugin", "getSubscriberKey", []);
};

exports.addAttribute = function(name,value,success, error) {
    exec(success, error, "MkCPlugin", "addAttribute", [name,value]);
};

exports.removeAttribute = function(name,success, error) {
    exec(success, error, "MkCPlugin", "removeAttribute", [name]);
};
exports.getAttributes = function(success, error) {
    exec(success, error, "MkCPlugin", "getAttributes", []);
};
exports.addTag = function(name,value,success, error) {
    exec(success, error, "MkCPlugin", "addAttribute", [name,value]);
};
exports.removeTag = function(key,val,success, error) {
    exec(success, error, "MkCPlugin", "removeTag", [key,val]);
};
exports.getTags = function(success, error) {
    exec(success, error, "MkCPlugin", "getTags", []);
};



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

exports.getSDKState = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", [arg0]);
};



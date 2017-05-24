

var exec = require('cordova/exec');

exports.getDeviceId = function(success, error) {
    exec(success, error, "MkCPlugin", "getDeviceId", []);
};

exports.getSDKState = function(success, error) {
    exec(success, error, "MkCPlugin", "getSDKState", []);
};

exports.registerForNotifications = function(callback,success, error) {
    exec(success, error, "MkCPlugin", "registerForNotifications", [arg0]);
};



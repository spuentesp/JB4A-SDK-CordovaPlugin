
var exec = require('cordova/exec');

exports.getDeviceId = function(arg0, success, error) {
    exec(success, error, "MkCPlugin", "getDeviceId", [arg0]);
};

exports.initMkC = function(arg0, success, error) {
    exec(success, error, "MkCPlugin", "initMkC", [arg0]);
};

exports.getSDKState = function(arg0, success, error) {
    exec(success, error, "MkCPlugin", "getSDKState", [arg0]);
};

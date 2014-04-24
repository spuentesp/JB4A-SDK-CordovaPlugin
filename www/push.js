/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

/**
 * This represents the mobile device, and provides properties for inspecting the model, version, UUID of the
 * phone, etc.
 * @constructor
 */
/*function ETPush() {
    this.available = false;
    this.platform = null;
    this.version = null;
    this.uuid = null;
    this.cordova = null;
    this.model = null;

    var me = this;

    channel.onCordovaReady.subscribe(function() {
        me.getInfo(function(info) {
            //ignoring info.cordova returning from native, we should use value from cordova.version defined in cordova.js
            //TODO: CB-5105 native implementations should not return info.cordova
            var buildLabel = cordova.version;
            me.available = true;
            me.platform = info.platform;
            me.version = info.version;
            me.uuid = info.uuid;
            me.cordova = buildLabel;
            me.model = info.model;
            channel.onCordovaInfoReady.fire();
        },function(e) {
            me.available = false;
            utils.alert("[ERROR] Error initializing Cordova: " + e);
        });
    });
}
*/

/**
 * Get device info
 *
 * @param {Function} successCallback The function to call when the heading data is available
 * @param {Function} errorCallback The function to call when there is an error getting the heading data. (OPTIONAL)
 */

 var ETPush = {
    Test: function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "test", []);
    },
    register: function(successCallback, errorCallback, accessToken, appID)
    {
        
    },
    //returns boolean with whether device notifications are active
    isPushEnabled: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "isPushEnabled", []);
    },
    //returns the uniqe device identifier
    safeDeviceIdentifier: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "safeDeviceIdentifier", []);
    },
    //get the current subscriber key
    getSubscriberKey: function(SuccessCallback, errorCallback, subscriberKey) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
    //set the current subscriber key
    setSubscriberKey: function(SuccessCallback, errorCallback,subscriberKey, value) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
    // add a tag 
    addTag: function(SuccessCallback, errorCallback, tag, value) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
    //remove a tag
    removeTag: function(SuccessCallback, errorCallback, tag) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
    //return all the tags
    allTags: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
    //add an attribute and set its value
    addAttribute: function(SuccessCallback, errorCallback, name, var value) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
    //remove an attribute
    removeAttribute: function(SuccessCallback, errorCallback, name, var value) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
    //returns all attributes
    allAttributes: function(SuccessCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SdkWrapper", "getSubscriberKey", []);
    },
 }


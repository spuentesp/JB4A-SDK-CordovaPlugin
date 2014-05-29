/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at
 
 http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

#include <sys/types.h>
#include <sys/sysctl.h>

#import <Cordova/CDV.h>
#import "SdkWrapper.h"
#import "ETPush.h"


@interface SdkWrapper () {}
@end

@implementation SdkWrapper

- (void)test:(CDVInvokedUrlCommand*)command
{
    NSLog(@"Test");
    
}
- (void)isPushEnabled:(CDVInvokedUrlCommand *)command
{
    NSLog(@"testing");
    CDVPluginResult* pluginResult = nil;
    BOOL pushEnabled = [ETPush isPushEnabled];
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:pushEnabled];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}
- (void) setSubscriberKey:(CDVInvokedUrlCommand *)command
{
    NSString* subKey = [command.arguments objectAtIndex:0];
    NSLog(@"setting sub key %@", subKey);
    [[ETPush pushManager] setSubscriberKey:subKey];
    
}
- (void) addTag:(CDVInvokedUrlCommand *)command
{
    NSString* tag = [command.arguments objectAtIndex:0];
    [[ETPush pushManager] addTag:tag];
}

- (void) removeTag:(CDVInvokedUrlCommand *)command
{
    NSString* tag = [command.arguments objectAtIndex:0];
    [[ETPush pushManager] removeTag:tag];
    
}
- (void)addAttribute:(CDVInvokedUrlCommand *)command
{
    NSString* attName = [command.arguments objectAtIndex:0];
    NSString* attVal = [command.arguments objectAtIndex:1];
    [[ETPush pushManager] addAttributeNamed:attName
                                      value:attVal];
    
}
- (void) removeAttribute:(CDVInvokedUrlCommand *)command
{
    NSString* attName = [command.arguments objectAtIndex:0];
    [[ETPush pushManager] removeAttributeNamed:attName];
    
}

- (void) resetBadgeCount:(CDVInvokedUrlCommand *)command
{
    [[ETPush pushManager] resetBadgeCount];
}




@end

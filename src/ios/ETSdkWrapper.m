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
#import "ETSdkWrapper.h"
#import "ETPush.h"


@interface ETSdkWrapper () {}
@end

@implementation ETSdkWrapper

static NSString *notificationCallback;
static ETSdkWrapper *etPluginInstance;

+ (ETSdkWrapper *) etPlugin {
    
    return etPluginInstance;
}

- (void)isPushEnabled:(CDVInvokedUrlCommand *)command
{
    [self.commandDelegate runInBackground:^{
        
        CDVPluginResult* pluginResult = nil;
        BOOL pushEnabled = [ETPush isPushEnabled];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:pushEnabled];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}
- (void) setSubscriberKey:(CDVInvokedUrlCommand *)command
{
    NSString* subKey = [command.arguments objectAtIndex:0];
    NSLog(@"setting sub key %@", subKey);
    [self.commandDelegate runInBackground:^{
        
        [[ETPush pushManager] setSubscriberKey:subKey];
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:subKey];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void) addTag:(CDVInvokedUrlCommand *)command
{
    NSString* tag = [command.arguments objectAtIndex:0];
    [self.commandDelegate runInBackground:^{
        
        [[ETPush pushManager] addTag:tag];
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:tag];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
    
}

- (void) removeTag:(CDVInvokedUrlCommand *)command
{
    NSString* tag = [command.arguments objectAtIndex:0];
    [self.commandDelegate runInBackground:^{
        
        [[ETPush pushManager] removeTag:tag];
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:tag];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
    
}

-(void) getTags:(CDVInvokedUrlCommand *)command
{
    NSSet* tags = [[ETPush pushManager] allTags];
    
    [self.commandDelegate runInBackground:^{
        NSString* result =[NSString stringWithFormat:@"%@", tags];
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)addAttribute:(CDVInvokedUrlCommand *)command
{
    NSString* attName = [command.arguments objectAtIndex:0];
    NSString* attVal = [command.arguments objectAtIndex:1];
    
    [self.commandDelegate runInBackground:^{
        [[ETPush pushManager] addAttributeNamed:attName
                                          value:attVal];
        NSString* result =[NSString stringWithFormat:@" %@ : %@",attName, attVal];
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
    
}
- (void) removeAttribute:(CDVInvokedUrlCommand *)command
{
    NSString* attName = [command.arguments objectAtIndex:0];
    [self.commandDelegate runInBackground:^{
        
        [[ETPush pushManager] removeAttributeNamed:attName];
        
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:attName];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
    
    
}
- (void)getAttribute:(CDVInvokedUrlCommand *)command
{
    NSString* attName = [command.arguments objectAtIndex:0];
    NSDictionary* attributes = [[ETPush pushManager] allAttributes];
    NSString* attval = [ attributes objectForKey:attName];
    
    [self.commandDelegate runInBackground:^{
        NSString* result =[NSString stringWithFormat:@"%@", attval];
        CDVPluginResult* pluginResult = nil;
        if(result != NULL)
        {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
        }
        else
        {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[NSString stringWithFormat:@"The attribute %@ does not appear to be set",attName]];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void) resetBadgeCount:(CDVInvokedUrlCommand *)command
{
    [[ETPush pushManager] resetBadgeCount];
    [self.commandDelegate runInBackground:^{
        
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void) registerForNotifications:(CDVInvokedUrlCommand *)command
{
    notificationCallback = [command.arguments objectAtIndex:0];
    etPluginInstance = self;
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:notificationCallback];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

-(void) notifyOfMessage:(NSData *)payload
{
    NSString *JSONString = [[NSString alloc] initWithBytes:[payload bytes] length:[payload length] encoding:NSUTF8StringEncoding];
    NSString * notifyJS = [NSString stringWithFormat:@"%@('%@');", notificationCallback, JSONString];
    NSLog(@"stringByEvaluatingJavaScriptFromString %@", notifyJS);
    
    NSString *jsResults = [self.webView stringByEvaluatingJavaScriptFromString:notifyJS];
}

- (void)getDeviceID:(CDVInvokedUrlCommand *)command
{
    [self.commandDelegate runInBackground:^{
        NSString* deviceID = [ETPush safeDeviceIdentifier];
        NSString* result =[NSString stringWithFormat:@"%@",deviceID];
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)getDeviceToken:(CDVInvokedUrlCommand *)command
{
    [self.commandDelegate runInBackground:^{
        NSString* deviceToken = [[ETPush pushManager] deviceToken];
        NSString* result =[NSString stringWithFormat:@"%@",deviceToken];
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

/* Android only methods here to keep errors from clogging the log */
- (void)initApp:(CDVInvokedUrlCommand *)command
{
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)enablePush:(CDVInvokedUrlCommand *)command
{
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)enableGeoLocation:(CDVInvokedUrlCommand *)command
{
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}


@end

/********* MkCPlugin.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import "ETPush.h"
#import "MkCPlugin.h"

@implementation MkCPlugin

static NSString *notificationCallback;
static MkCPlugin *mkcInstance;

+ (MkCPlugin *) mkcInstance {
    return mkcInstance;
}

- (void)initMkC:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
    
}

- (void)getDeviceId:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    //NSString* params = [command.arguments objectAtIndex:0];
    NSString* result = [ETPush safeDeviceIdentifier];
    
    
    if (result != nil && [result length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    
}

- (void)getSDKState:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    //NSString* params = [command.arguments objectAtIndex:0];
    NSString* result = [ETPush getSDKState];
    
    if (result != nil && [result length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    
}


- (void) registerForNotifications:(CDVInvokedUrlCommand *)command
{
    notificationCallback = [command.arguments objectAtIndex:0];
    mkcInstance = self;
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
    
    //NSString *jsResults = [self.webView stringByEvaluatingJavaScriptFromString:notifyJS];
    [self.commandDelegate evalJs:notifyJS];
    
}

@end

/********* MkCPlugin.m Cordova Plugin Implementation *******/

#include <sys/types.h>
#include <sys/sysctl.h>

#import <Cordova/CDV.h>
#import "MkCPlugin.h"
#import "ETPush.h"

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
    //NSString* params = [command.arguments objectAtIndex:0];
    NSString* result = [ETPush safeDeviceIdentifier];
    
    [self.commandDelegate runInBackground:^{
        NSLog(@"%@", result);
        CDVPluginResult* pluginResult = nil;
        if (result != nil) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)getSDKState:(CDVInvokedUrlCommand*)command
{
    //NSString* params = [command.arguments objectAtIndex:0];
    NSString* result = [ETPush getSDKState];
    
    [self.commandDelegate runInBackground:^{
        NSLog(@"%@", result);
        CDVPluginResult* pluginResult = nil;
        if (result != nil) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
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
    
    UIWebView *view = self.webView;
    
    NSString *jsResults = [view stringByEvaluatingJavaScriptFromString:notifyJS];
    
    
}

@end

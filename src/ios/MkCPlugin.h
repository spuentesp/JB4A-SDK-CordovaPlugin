/********* MkCPlugin.h Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface MkCPlugin : CDVPlugin {
    // Member variables go here.
}

+ (MkCPlugin *) mkcInstance;
- (void)getDeviceId:(CDVInvokedUrlCommand*)command;
- (void)getSDKState:(CDVInvokedUrlCommand*)command;
- (void) registerForNotifications:(CDVInvokedUrlCommand *)command;
- (void) notifyOfMessage:(NSData *)payload;
@end


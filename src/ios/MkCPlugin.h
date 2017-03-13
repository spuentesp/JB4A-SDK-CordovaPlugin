/********* MkCPlugin.h Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import "ETPush.h"

@interface MkCPlugin : CDVPlugin {
  // Member variables go here.
}

- (void)getDeviceId:(CDVInvokedUrlCommand*)command;
- (void)getSDKState:(CDVInvokedUrlCommand*)command;
@end


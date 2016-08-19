//
//  AppDelegate+JB4A.m
//
//  Created by Manuel Alfonso Terol on 19/08/2016.
//
//

#import "AppDelegate+JB4A.h"

#import <objc/runtime.h>
#import "ETPush.h"
#import "ETSdkWrapper.h"


@implementation AppDelegate (JB4A)

+ (void) load
{
    Method original, swizzled;

    original = class_getInstanceMethod(self, @selector(init));
    swizzled = class_getInstanceMethod(self, @selector(swizzled_init));
    method_exchangeImplementations(original, swizzled);
}

- (AppDelegate *) swizzled_init
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(createNotificationChecker:)
                                                 name:@"UIApplicationDidFinishLaunchingNotification" object:nil];

    // This actually calls the original init method over in AppDelegate. Equivilent to calling super
    // on an overrided method, this is not recursive, although it appears that way. neat huh?
    return [self swizzled_init];
}

// This code will be called immediately after application:didFinishLaunchingWithOptions:. We need
// to process notifications in cold-start situations
- (void) createNotificationChecker:(NSNotification *)notification
{
    if (notification)
    {
        NSDictionary *launchOptions = [notification userInfo];
        NSBundle* mainBundle = [NSBundle mainBundle];
            NSDictionary* ETSettings = [mainBundle objectForInfoDictionaryKey:@"ETAppSettings"];
            BOOL useGeoLocation = [[ETSettings objectForKey:@"UseGeofences"] boolValue];
            BOOL useAnalytics = [[ETSettings objectForKey:@"UseAnalytics"] boolValue];
        #ifdef DEBUG
            NSString* devAppID = [ETSettings objectForKey:@"ApplicationID-Dev"];
            NSString* devAccessToken = [ETSettings objectForKey:@"AccessToken-Dev"];
            //use your debug app id and token you setup in code.exacttarget.com here
            [[ETPush pushManager] configureSDKWithAppID:devAppID
                                         andAccessToken:devAccessToken
                                          withAnalytics:useAnalytics
                                    andLocationServices:useGeoLocation
                                          andCloudPages:NO];

        #else
            NSString* prodAppID = [ETSettings objectForKey:@"ApplicationID-Prod"];
            NSString* prodAccessToken = [ETSettings objectForKey:@"AccessToken-Prod"];
            //use your production app id and token you setup in code.exacttarget.com here

            [[ETPush pushManager] configureSDKWithAppID:prodAppID
                                         andAccessToken:prodAccessToken
                                          withAnalytics:useAnalytics
                                    andLocationServices:useGeoLocation
                                          andCloudPages:NO];

        #endif

            // IPHONEOS_DEPLOYMENT_TARGET = 6.X or 7.X
        #if __IPHONE_OS_VERSION_MIN_REQUIRED < 80000
        #if __IPHONE_OS_VERSION_MAX_ALLOWED >= 80000
            // Supports IOS SDK 8.X (i.e. XCode 6.X and up)
            // are we running on IOS8 and above?
            if ([[UIApplication sharedApplication] respondsToSelector:@selector(registerForRemoteNotifications)]) {
                UIUserNotificationSettings *settings = [UIUserNotificationSettings settingsForTypes:
                                                         UIUserNotificationTypeBadge | UIUserNotificationTypeSound | UIUserNotificationTypeAlert
                                                                                          categories:nil];
                [[ETPush pushManager] registerUserNotificationSettings:settings];
                [[ETPush pushManager] registerForRemoteNotifications];
            }
            else {
                [[ETPush pushManager] registerForRemoteNotificationTypes:UIRemoteNotificationTypeAlert|UIRemoteNotificationTypeBadge|UIRemoteNotificationTypeSound];
            }
        #else
            // Supports IOS SDKs < 8.X (i.e. XCode 5.X or less)
            [[ETPush pushManager] registerForRemoteNotificationTypes:UIRemoteNotificationTypeAlert|UIRemoteNotificationTypeBadge|UIRemoteNotificationTypeSound];
        #endif
        #else
            // IPHONEOS_DEPLOYMENT_TARGET >= 8.X
            // Supports IOS SDK 8.X (i.e. XCode 6.X and up)
            UIUserNotificationSettings *settings = [UIUserNotificationSettings settingsForTypes:
                                                     UIUserNotificationTypeBadge | UIUserNotificationTypeSound | UIUserNotificationTypeAlert
                                                                                      categories:nil];
            [[ETPush pushManager] registerUserNotificationSettings:settings];
            [[ETPush pushManager] registerForRemoteNotifications];
        #endif
            [[ETPush pushManager] shouldDisplayAlertViewIfPushReceived:YES];
            [[ETPush pushManager] applicationLaunchedWithOptions:launchOptions];
            NSString* token = [[ETPush pushManager] deviceToken];
            NSString* deviceID = [ETPush safeDeviceIdentifier]; NSLog(@"token %@", token);
            NSLog(@"Device ID %@", deviceID);
            if (useGeoLocation) {
                [[ETLocationManager locationManager] startWatchingLocation];
            }
    }
}

-(void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
    [[ETPush pushManager] handleNotification:userInfo forApplicationState:application.applicationState];
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:userInfo
                                                       options:0
                                                         error:&error];
    if (!jsonData) {

        NSLog(@"json error: %@", error);

    } else {

        [ETSdkWrapper.etPlugin notifyOfMessage:jsonData];
    }
}

- (void) application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
    [[ETPush pushManager] registerDeviceToken:deviceToken];
    // re-post ( broadcast )
    NSString* token = [[[[deviceToken description]
                         stringByReplacingOccurrencesOfString: @"<" withString: @""]
                        stringByReplacingOccurrencesOfString: @">" withString: @""]
                       stringByReplacingOccurrencesOfString: @" " withString: @""];

    [[NSNotificationCenter defaultCenter] postNotificationName:CDVRemoteNotification object:token];
}

- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error {

    [[ETPush pushManager] applicationDidFailToRegisterForRemoteNotificationsWithError:error];
    // re-post ( broadcast )
    [[NSNotificationCenter defaultCenter] postNotificationName:CDVRemoteNotificationError object:error];
}

- (void)application:(UIApplication*)application didReceiveLocalNotification:(UILocalNotification*)notification
{
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:notification.userInfo
                                                       options:0
                                                         error:&error];
    if (!jsonData) {

        NSLog(@"jsn error: %@", error);

    } else {

        [ETSdkWrapper.etPlugin notifyOfMessage:jsonData];
    }
    // re-post ( broadcast )
    [[NSNotificationCenter defaultCenter] postNotificationName:CDVLocalNotification object:notification];
}


@end

//
//  AppDelegate+ETPush.m
//  CordovaLib
//
//  Created by Rodrigo Palma Fanjul on 13-03-17.
//
//

#import <Foundation/Foundation.h>
#import "AppDelegate.h"
#import <objc/runtime.h> // Needed for method swizzling
#import "ETPush.h"
#import "MkCPlugin.h"

#import "AppDelegate+MkCPlugin.h"

@implementation AppDelegate (MkCPlugin)

+ (void) load
{
    Method original, swizzled;
    
    original = class_getInstanceMethod(self, @selector(init));
    swizzled = class_getInstanceMethod(self, @selector(swizzled_init));
    method_exchangeImplementations(original, swizzled);
}

//se llama al init swizzled. esto ejecuta el metodo de inicializacion de notificaciones, y luego carga el init normal.
- (AppDelegate *) swizzled_init
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(initNotifications:)
                                                 name:@"UIApplicationDidFinishLaunchingNotification" object:nil];
    NSLog(@"swizzled!");
    return [self swizzled_init];
    
}

- (void) initNotifications:(NSNotification *)notification
{
    if (notification)
    {
        NSDictionary *launchOptions = [notification userInfo];
        NSBundle* mainBundle = [NSBundle mainBundle];
        NSDictionary* ETSettings = [mainBundle objectForInfoDictionaryKey:@"ETAppSettings"];
        BOOL useGeoLocation = [[ETSettings objectForKey:@"UseGeofences"] boolValue];
        BOOL useAnalytics = [[ETSettings objectForKey:@"UseAnalytics"] boolValue];
        NSString* appID = [ETSettings objectForKey:@"ETApplicationID"];
        NSString* accessToken = [ETSettings objectForKey:@"AccessToken"];
        [[ETPush pushManager] configureSDKWithAppID:appID
                                     andAccessToken:accessToken
                                      withAnalytics:useAnalytics
                                andLocationServices:useGeoLocation
                               andProximityServices:NO
                                      andCloudPages:NO
                                    withPIAnalytics:NO
                                              error:nil];
        NSLog(@"notificaciones");
        //inicializa notificaciones
        UIUserNotificationSettings *settings = [UIUserNotificationSettings settingsForTypes:
                                                UIUserNotificationTypeBadge | UIUserNotificationTypeSound |UIUserNotificationTypeAlert categories:nil];
        [[ETPush pushManager] registerUserNotificationSettings:settings];
        [[ETPush pushManager] registerForRemoteNotifications];
        [[ETPush pushManager] shouldDisplayAlertViewIfPushReceived:YES];
        [[ETPush pushManager] applicationLaunchedWithOptions:launchOptions];
        NSString* token = [[ETPush pushManager] deviceToken];
        NSString* deviceID = [ETPush safeDeviceIdentifier]; NSLog(@"token %@", token);
        NSLog(@"Device ID %@", deviceID);
        if (useGeoLocation) {
            [[ETLocationManager sharedInstance] startWatchingLocation];
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
        
        [MkCPlugin.mkcInstance notifyOfMessage:jsonData];
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
        
        [MkCPlugin.mkcInstance notifyOfMessage:jsonData];
    }
    [[NSNotificationCenter defaultCenter] postNotificationName:CDVLocalNotification object:notification];
}




@end

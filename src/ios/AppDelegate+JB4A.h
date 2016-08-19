//
//  AppDelegate+JB4A.h
//
//  Created by Manuel Alfonso Terol on 19/08/2016.
//
//

#import "AppDelegate.h"

@interface AppDelegate (JB4A)
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken;
- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error;
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo;
- (void)application:(UIApplication*)application didReceiveLocalNotification:(UILocalNotification*)notification;

@end
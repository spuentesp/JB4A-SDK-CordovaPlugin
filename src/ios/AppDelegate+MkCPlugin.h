//
//  AppDelegate+ETPush.h
//  test-plugin
//
//  Created by Rodrigo Palma Fanjul on 13-03-17.
//
//

#ifndef AppDelegate_ETPush_h
#define AppDelegate_ETPush_h

#endif /* AppDelegate_ETPush_h */
@interface AppDelegate (JB4A)
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo;
- (void)application:(UIApplication*)application didReceiveLocalNotification:(UILocalNotification*)notification;
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken;
- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error;


@end

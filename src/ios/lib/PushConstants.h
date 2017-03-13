//
//  PushConstants.h
//  JB4A-SDK-iOS
//
//  JB4A iOS SDK GitHub Repository
//  https://salesforce-marketingcloud.github.io/JB4A-SDK-iOS/
//

/**
 Notification that is sent when a push fails for some reason.
 */
/** @constant ETPush Notifications Sent when the data request process has started */
#define ETRequestStarted                @"ETRequestStarted"
#define ETRequestNoData                 @"ETRequestNoData"
#define ETRequestFailed                 @"ETRequestFailed"
#define ETRequestComplete               @"ETRequestComplete"
#define ETRequestFailedOnLoadingResult  @"ETRequestFailedOnLoadingResult"
#define ETRequestServiceReturnedError   @"ETRequestServiceReturnedError"
#define ETRequestServiceResponseSuccess @"ETRequestServiceResponseSuccess"
#define ETRequestFinishedWithFailure    @"ETRequestFinishedWithFailure"

/**
  Constants for dealing with other stuff
  */
#define AppLifecycleForeground          @"AppLifecycleForeground"
#define AppLifecycleBackground          @"AppLifecycleBackground"

/**
 Geofence Constants
 */
#define ETLargeGeofence                 433 // Get it? 433 North Capitol. It's been a long journey getting here.
#define ETLargeGeofenceIdentifier       @"ExactTargetMagicGlobalFence"

/**
 Caches
 */
#define ET_TAG_CACHE                    @"ET_TAG_CACHE"
#define ET_SUBKEY_CACHE                 @"ET_SUBKEY_CACHE"
#define ET_ATTR_CACHE                   @"ET_ATTR_CACHE"

/**
 Deprecation warning declaration
 */
#define __DEPRECATED_WARNING(message) __attribute((deprecated(message)))

// Tracks the BOOL for each in NSUserDefaults
static NSString * const ETProximityServicesActive = @"ETProximityServicesActive";
static NSString * const ETLocationServicesActive  = @"ETLocationServicesActive";
static NSString * const ETCloudPagesActive        = @"ETCloudPagesActive";
static NSString * const ETAnalyticsActive         = @"ETAnalyticsActive";
static NSString * const ETPIAnalyticsActive       = @"ETPIAnalyticsActive";

/**
 Push Origination State
 */
typedef NS_ENUM (NSUInteger, pushOriginationState){
    firstPushOriginationStateIndex = 0,
    /** PushOriginationStateBackground  */
    PushOriginationStateBackground = firstPushOriginationStateIndex,
    /** PushOriginationStateForeground  */
    PushOriginationStateForeground,
    /** Unknown state  */
    PushOriginationStateUnknown = 999,
    /** lastPushOriginationStateIndex */
    lastPushOriginationStateIndex = PushOriginationStateUnknown
};

/**
 configureSDKWithAppID errors
 */
typedef NS_ENUM(NSUInteger, configureSDKWithAppIDError) {
    firstconfigureSDKWithAppIDIndex = 0,
    /** configureSDKWithAppIDNoError  */
    configureSDKWithAppIDNoError = firstconfigureSDKWithAppIDIndex,
    /** configureSDKWithAppIDInvalidAppIDError  */
    configureSDKWithAppIDInvalidAppIDError,
    /** configureSDKWithAppIDInvalidAccessTokenError  */
    configureSDKWithAppIDInvalidAccessTokenError,
    /** configureSDKWithAppIDUnableToReadRandomError  */
    configureSDKWithAppIDUnableToReadRandomError,
    /** configureSDKWithAppIDDatabaseAccessError  */
    configureSDKWithAppIDDatabaseAccessError,
    /** configureSDKWithAppIDUnableToKeyDatabaseError  */
    configureSDKWithAppIDUnableToKeyDatabaseError,
    /** configureSDKWithAppIDCCKeyDerivationPBKDFError  */
    configureSDKWithAppIDCCKeyDerivationPBKDFError,
    /** configureSDKWithAppIDCCSymmetricKeyWrapError  */
    configureSDKWithAppIDCCSymmetricKeyWrapError,
    /** configureSDKWithAppIDCCSymmetricKeyUnwrapError  */
    configureSDKWithAppIDCCSymmetricKeyUnwrapError,
    /** configureSDKWithAppIDKeyChainError  */
    configureSDKWithAppIDKeyChainError,
    /** configureSDKWithAppIDUnableToReadCertificateError  */
    configureSDKWithAppIDUnableToReadCertificateError,
    /** configureSDKWithAppIDRunOnceSimultaneouslyError  */
    configureSDKWithAppIDRunOnceSimultaneouslyError,
    /** configureSDKWithAppIDRunOnceError  */
    configureSDKWithAppIDRunOnceError,
    /** configureSDKWithAppIDInvalidLocationAndProximityError  */
    configureSDKWithAppIDInvalidLocationAndProximityError,
    /** configureSDKWithAppIDSimulatorBlobError  */
    configureSDKWithAppIDSimulatorBlobError,
    /** configureSDKWithAppIDKeyChainInvalidError  */
    configureSDKWithAppIDKeyChainInvalidError,
    /** lastconfigureSDKWithAppIDIndex */
    lastconfigureSDKWithAppIDIndex = configureSDKWithAppIDKeyChainInvalidError
};

/**
 requestPIRecommendations errors
 */
typedef NS_ENUM(NSUInteger, requestPIRecommendationsError) {
    firstrequestPIRecommendationsInvalidIndex = 0,
    /** requestPIRecommendationsNoError  */
    requestPIRecommendationsNoError = firstrequestPIRecommendationsInvalidIndex,
    /** requestPIRecommendationsInvalidMidParameterError  */
    requestPIRecommendationsInvalidMidParameterError  = 1024,
    /** requestPIRecommendationsInvalidRetailerParameterError  */
    requestPIRecommendationsInvalidRetailerParameterError,
    /** requestPIRecommendationsInvalidPageParameterError  */
    requestPIRecommendationsInvalidPageParameterError,
    /** requestPIRecommendationsInvalidCompletionHandlerError  */
    requestPIRecommendationsInvalidCompletionHandlerError,
    /** lastrequestPIRecommendationsInvalidIndex */
    lastrequestPIRecommendationsInvalidIndex = requestPIRecommendationsInvalidCompletionHandlerError
};


/**
 ETEventBus constants
 */

static NSString * const kDidDisplayLocationMessageNotification = @"ETEventBusDidDisplayLocationMessageNotification";
static NSString * const kDidReceiveGeofenceResponseNotification = @"ETEventBusDidReceiveGeofenceResponseNotification";
static NSString * const kDidReceiveBeaconResponseNotification = @"ETEventBusDidReceiveBeaconResponseNotification";
static NSString * const kDidEnterGeofenceNotification = @"ETEventBusDidEnterGeofenceNotification";
static NSString * const kDidExitGeofenceNotification = @"ETEventBusDidExitGeofenceNotification";
static NSString * const kDidRangeBeaconNotification = @"ETEventBusDidRangeBeaconNotification";
static NSString * const kDidReceiveRichMessagesNotification = @"ETEventBusDidReceiveRichMessagesNotification";
static NSString * const kDidReceiveCloudPagesNotification = @"ETEventBusDidReceiveRichMessagesNotification";
static NSString * const kDidReceiveLocationUpdateNotification = @"ETEventBusDidReceiveLocationUpdateNotification";

/**
 Enumeration of the type of ETMessage this is.
 */
typedef NS_ENUM(NSUInteger, MobilePushMessageType)
{
    firstMobilePushMessageTypeIndex = 0,
    /** Unknown */
    MobilePushMessageTypeUnknown = firstMobilePushMessageTypeIndex,
    /** Basic - A standard push message */
    MobilePushMessageTypeBasic,
    /** Geofence Entry */
    MobilePushMessageTypeFenceEntry = 3,
    /** Geofence Exit */
    MobilePushMessageTypeFenceExit,
    /** Proximity */
    MobilePushMessageTypeProximity,
    /** lastMobilePushMessageTypeIndex */
    lastMobilePushMessageTypeIndex = MobilePushMessageTypeProximity
};

/**
 Enumeration of the type of ETRegion that this is - Circle (Geofence) or Proximity (ibeacon). Polygon is not currently used.
 */
typedef NS_ENUM(NSUInteger, MobilePushGeofenceType) {
    firstMobilePushGeofenceTypeIndex = 0,
    /** MobilePushGeofenceTypeNone */
    MobilePushGeofenceTypeNone = firstMobilePushGeofenceTypeIndex,
    /** MobilePushGeofenceTypeCircle */
    MobilePushGeofenceTypeCircle,
    /** MobilePushGeofenceTypeProximity */
    MobilePushGeofenceTypeProximity = 3,
    /** lastMobilePushGeofenceTypeIndex */
    lastMobilePushGeofenceTypeIndex = MobilePushGeofenceTypeProximity
};

/**
 Enumeration to keep track of if the request is for Geofences or Proximity messages.
 */
typedef NS_ENUM(NSUInteger, ETRegionRequestType) {
    firstETRegionRequestTypeProximityIndex = 0,
    /** ETRegionRequestTypeUnknown */
    ETRegionRequestTypeUnknown = firstETRegionRequestTypeProximityIndex,
    /** ETRegionRequestTypeGeofence */
    ETRegionRequestTypeGeofence,
    /** ETRegionRequestTypeProximity */
    ETRegionRequestTypeProximity,
    /** lastETRegionRequestTypeProximityIndex */
    lastETRegionRequestTypeProximityIndex = ETRegionRequestTypeProximity
};

/**
 Bitmask of features that a message has. This is the representation of Push (AlertMessage), Push+Page (AlertMessage + Page), Page Only (Page) in the MobilePush UI.
 */
typedef NS_OPTIONS(NSUInteger, MobilePushContentType) {
    /** Unknown */
    MobilePushContentTypeNone           = 0,
    /** Push Message */
    MobilePushContentTypeAlertMessage   = 1 << 0,
    /** CloudPage */
    MobilePushContentTypePage           = 1 << 1,
    /** Enhanced Cloud Page – Pushed Cloud Pages */
    MobilePushContentTypeEcp = 1 << 31
};

/**
 Time Unit enumeration for Message limiting.
 */
typedef NS_ENUM(NSUInteger, MobilePushMessageFrequencyUnit) {
    firstMobilePushMessageFrequencyIndex = 0,
    /** Unknown */
    MobilePushMessageFrequencyUnitNone = firstMobilePushMessageFrequencyIndex,
    /** Year */
    MobilePushMessageFrequencyUnitYear,
    /** Month */
    MobilePushMessageFrequencyUnitMonth,
    /** Week */
    MobilePushMessageFrequencyUnitWeek,
    /** Day */
    MobilePushMessageFrequencyUnitDay,
    /** Hour */
    MobilePushMessageFrequencyUnitHour,
    /** lastMobilePushMessageFrequencyIndex */
    lastMobilePushMessageFrequencyIndex = MobilePushMessageFrequencyUnitHour
};


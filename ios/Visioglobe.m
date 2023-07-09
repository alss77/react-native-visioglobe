#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Visioglobe, NSObject)

RCT_EXTERN_METHOD(multiply:(float)a withB:(float)b
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(presentVisioglobe)

RCT_EXTERN_METHOD(setMapHash:(NSString *)mapHash)

RCT_EXTERN_METHOD(getMapHash:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(initController)

RCT_EXTERN_METHOD(loadMapData)

RCT_EXTERN_METHOD(setLifeCycleListener)

RCT_EXTERN_METHOD(placeAllColorSwitchAction:(NSNumber *)activate)

RCT_EXTERN_METHOD(unloadMapData)

RCT_EXTERN_METHOD(unloadMapView)

RCT_EXTERN_METHOD(setPoisColor:(NSArray<NSString *> *)poiIDs colors:(NSArray<NSString *> *)colors withResolver:(RCTPromiseResolveBlock)resolve withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(setPoisSize:(NSArray<NSString *> *)lpoiIDs sizes:(NSArray<NSNumber *> *)sizes animated:(NSArray<NSNumber *> *)animated)

RCT_EXTERN_METHOD(resetPoisColor:(NSArray<NSString *> *)lpoiIDs callback:(RCTResponseSenderBlock)callback)

RCT_EXTERN_METHOD(initRouteRequest)

RCT_EXTERN_METHOD(setOrigin:(NSDictionary *)position)

RCT_EXTERN_METHOD(addDestinations:(NSArray<NSDictionary *> *)positions)

RCT_EXTERN_METHOD(computeRoute)

RCT_EXTERN_METHOD(createLocationFromLocation:(NSDictionary *)location callback:(RCTResponseSenderBlock)callback)

RCT_EXTERN_METHOD(createPositionFromLocation:(NSDictionary *)location callback:(RCTResponseSenderBlock)callback)

/* RCT_EXPORT_METHOD(exportMapDataDidLoad:(RCTResponseSenderBlock)callback) {
  self.mapDataDidLoadCallback = callback;
}

- (void)mapDataDidLoad {
  if (self.mapDataDidLoadCallback) {
    self.mapDataDidLoadCallback(@[]);
  }
} */

@end

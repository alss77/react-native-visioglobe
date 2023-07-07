#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Visioglobe, NSObject)

/* RCT_EXTERN_METHOD(multiply:(float)a withB:(float)b
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject) */

RCT_EXTERN_METHOD(presentVisioglobe:(NSString *)mapHash)

RCT_EXTERN_METHOD(initController)

RCT_EXTERN_METHOD(loadMapData)

RCT_EXTERN_METHOD(setLifeCycleListener)

RCT_EXTERN_METHOD(placeAllColorSwitchAction:(NSNumber *)activate)

RCT_EXTERN_METHOD(unloadMapData)

RCT_EXTERN_METHOD(unloadMapView)

/* RCT_EXPORT_METHOD(exportMapDataDidLoad:(RCTResponseSenderBlock)callback) {
  self.mapDataDidLoadCallback = callback;
}

- (void)mapDataDidLoad {
  if (self.mapDataDidLoadCallback) {
    self.mapDataDidLoadCallback(@[]);
  }
} */

@end

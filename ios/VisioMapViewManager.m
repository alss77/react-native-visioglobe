//
//  MapViewManager.m
//  Visioglobe
//
//  Created by Alassane on 17/07/2023.
//  Copyright © 2023 Facebook. All rights reserved.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTViewManager.h>

@interface RCT_EXTERN_MODULE(VisioMapViewManager, RCTViewManager)
RCT_EXPORT_VIEW_PROPERTY(mapHash, NSString)
RCT_EXPORT_VIEW_PROPERTY(mapPath, NSString)
RCT_EXPORT_VIEW_PROPERTY(mapSecret, NSNumber)
RCT_EXTERN_METHOD(customFunctionToCall: (nonnull NSNumber *) reactTag)
RCT_EXTERN_METHOD(setPois: (nonnull NSNumber *) reactTag
                  data: (nonnull NSString *)data)
RCT_EXTERN_METHOD(setPoisColor: (nonnull NSNumber *) reactTag
                  poiIDs: (nonnull NSArray<NSString *> *)poiIDs)
RCT_EXTERN_METHOD(resetPoisColor: (nonnull NSNumber *) reactTag)
RCT_EXTERN_METHOD(computeRoute: (nonnull NSNumber *) reactTag
                  origin: (nonnull NSString *) origin
                  destinations: (nonnull NSArray<NSString *> *) destinations)
RCT_EXTERN_METHOD(getVersion: (nonnull NSNumber *) reactTag
                  resolver: (RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(customFunctionToCall: () reactTag)
@end

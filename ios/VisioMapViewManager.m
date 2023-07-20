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
@end

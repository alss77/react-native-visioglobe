//
//  VisioModule.swift
//  Visioglobe
//
//  Created by Alassane on 20/07/2023.
//  Copyright © 2023 Facebook. All rights reserved.
//

import Foundation
import VisioMoveEssential
import React

@objc(VisioModule)
class VisioModule: NSObject, RCTBridgeModule {
    
    // static var manager: VMEMapViewManager!;

    static func moduleName() -> String! {
        "VisioModule"
    }
    
    public static func requiresMainQueueSetup() -> Bool {
      return true
    }
    
    @objc
    func customFunctionToCall(_ reactTag: NSNumber) {
        print("=====> FIRST CUSTOM CALL")
        /* DispatchQueue.main.async {
            let mapInstance = VisioModule.manager.bridge.uiManager.view(forReactTag: reactTag) as! VMEMapViewFrame;
            mapInstance.testRef()
        } */
    }
}

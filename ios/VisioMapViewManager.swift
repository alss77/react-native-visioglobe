//
//  MapViewManager.swift
//  Visioglobe
//
//  Created by Alassane on 17/07/2023.
//  Copyright © 2023 Facebook. All rights reserved.
//

import Foundation
import UIKit
import VisioMoveEssential

@objc(VisioMapViewManager)
class VisioMapViewManager: RCTViewManager {
    override func view() -> UIView! {
        return VisioMapView()
      }

    @objc
    func customFunctionToCall(_ reactTag: NSNumber) {
        print("=====> FIRST CUSTOM CALL FROM VIEW MANAGER")
        print(reactTag)
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.customFunctionToCall()
            }
        }
    }
    
  override static func requiresMainQueueSetup() -> Bool {
    return true
  }
}

class VisioMapView: UIView, VMELifeCycleListener {
    var mMapController: VMEMapController!
    var mMapView: VMEMapView!  // assuming VMEMapView is the correct type
    let label: UILabel = UILabel()
    
    @objc var mapHash: NSString = ""
    @objc var mapPath: NSString = ""
    @objc var mapSecret: NSNumber = 0

    override init(frame: CGRect) {
        super.init(frame: frame)
        print("====> INIT")

        self.backgroundColor = UIColor.blue

        // Initialize the map view

        // label.frame = CGRect(x: 10, y: 10, width: 100, height: 30)
        // self.addSubview(label)

        /* mMapController = VMEMapController.initController(builderBlock: { builder in
            builder.mapHash = ""
            builder.mapSecretCode = 0
        })
        mMapView = VMEMapView(mapController: mMapController, frame: self.bounds)
        self.addSubview(mMapView)

        mMapController.setLifeCycleListener(self)
        mMapController.loadMapData() */
    }
    
    override func didSetProps(_ changedProps: [String]!) {
        print("====> DID SET PROPS")
        print(self.mapHash as String)
        print(self.mapPath as String)
        print(Int32(truncating: self.mapSecret))
        
        mMapController = VMEMapController.initController(builderBlock: { builder in
            builder.mapHash = self.mapHash as String
            builder.mapSecretCode = Int(truncating: self.mapSecret)
        })
        mMapView = VMEMapView(mapController: mMapController, frame: self.bounds)
        self.addSubview(mMapView)

        mMapController.setLifeCycleListener(self)
        mMapController.loadMapData()
    }
    

    func customFunctionToCall() {
        print("=====> FIRST CUSTOM CALL FROM VIEW CLASS")
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()

        // Ensure the map view fills the entire view
        mMapView.frame = self.bounds
        label.frame = CGRect(x: 10, y: 10, width: self.bounds.width - 20, height: 30)
    }
    
    func mapDataDidLoad(mapController: VMEMapController, venueData: [String: Any]) {
        mMapController?.loadMapView(mapView: mMapView)
    }
}

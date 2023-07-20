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
        return VisioMapView(frame: .zero, mapHash: _mapHash, mapPath: _mapPath, mapSecret: _mapSecret)
      }


    private var _mapHash: String = ""
    private var _mapPath: String = ""
    private var _mapSecret: Int = 0

    @objc var mapHash: String {
        get {
            return _mapHash
        }
        set {
            _mapHash = newValue
            print("RECEIVE MAP HASH")
            print(_mapHash)
        }
    }
    
    @objc var mapPath: String {
        get {
            return _mapPath
        }
        set {
            _mapPath = newValue
            print("RECEIVE MAP PATH")
            print(_mapPath)
        }
    }
    
    @objc var mapSecret: String {
        get {
            return String(_mapSecret)
        }
        set {
            _mapSecret = Int(newValue) ?? 0
            print("RECEIVE MAP SECRET")
            print(_mapSecret)
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
    
    private var mapHash: String = ""
    private var mapPath: String = ""
    private var mapSecret: Int = 0

    override init(frame: CGRect) {
        super.init(frame: frame)

        self.backgroundColor = UIColor.blue

        // Initialize the map view

        // label.frame = CGRect(x: 10, y: 10, width: 100, height: 30)
        // self.addSubview(label)

        mMapController = VMEMapController.initController(builderBlock: { builder in
            builder.mapHash = self.mapHash
            builder.mapSecretCode = self.mapSecret
        })
        mMapView = VMEMapView(mapController: mMapController, frame: self.bounds)
        self.addSubview(mMapView)

        mMapController.setLifeCycleListener(self)
        mMapController.loadMapData()
    }
    

    // This is your custom initializer
    convenience init(frame: CGRect, mapHash: String, mapPath: String, mapSecret: Int) {
        self.init(frame: frame)
        self.mapHash = mapHash
        self.mapPath = mapPath
        self.mapSecret = mapSecret
        // other initialization stuff you need
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

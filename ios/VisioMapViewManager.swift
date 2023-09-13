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
import React

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
    
    @objc
    func setPois(_ reactTag: NSNumber, data: NSString) {
        print("=====> SET POIS FROM VIEW MANAGER")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.setPois(String(data))
            }
        }
    }
    
    class func randomColor() -> UIColor? {
        let lRed = Int(arc4random() % 255)
        let lGreen = Int(arc4random() % 255)
        let lBlue = Int(arc4random() % 255)
        let lRandomColor = UIColor(red: CGFloat(Double(lRed) / 255.0), green: CGFloat(Double(lGreen) / 255.0), blue: CGFloat(Double(lBlue) / 255.0), alpha: 1.0)
        return lRandomColor
    }
    
    @objc
    func setPoisColor(_ reactTag: NSNumber, poiIDs: [NSString]) {
        print("=====> SET POIS COLOR FROM VIEW MANAGER")
        print(poiIDs)
        let poiIDsToString = poiIDs.map { $0 as String }
        let lRandomColor = VisioMapViewManager.randomColor()
        var lColors = [UIColor]()
        for _ in poiIDsToString {
            lColors.append(lRandomColor!)
        }
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.setPoisColor(poiIDsToString, lColors: lColors)
            }
        }
    }
    
    @objc
    func resetPoisColor(_ reactTag: NSNumber) {
        print("=====> SET POIS FROM VIEW MANAGER")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.resetPoisColor()
            }
        }
    }
    
    @objc
    func computeRoute(_ reactTag: NSNumber, origin: NSString, destinations: [NSString]) {
        print("=====> COMPUTE ROUTE FROM VIEW MANAGER")
        let lDestinations = destinations.map { $0 as String }
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                // view.computeRoute(String(origin), lDestinations: lDestinations)
            }
        }
    }
    
    @objc
    func getVersion(_ reactTag: NSNumber,
                    resolve: @escaping RCTPromiseResolveBlock,
                    rejecter reject: @escaping RCTPromiseRejectBlock)->Void{
        resolve("version");
        /**DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                let version = view.getVersion()
                if !version.isEmpty {
                    resolve(version)
                } else {
                    reject("error", "Failed to get version in iOS", nil)
                }
            }
        }**/
    }

    
  /* override static func requiresMainQueueSetup() -> Bool {
    return true
  } */
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
        print("=====> LOG FROM CUSTOM FUNCTION")
    }
    
    
    func setPois(_ data: String) {
        let result = mMapController.setPois(data: data);
        print("=====> SET POIS RESULT")
        print(result)
    }
    
    func setPoisColor(_ poiIDs: [String], lColors: [UIColor]) {
        let result = mMapController.setPoisColor(poiIDs: poiIDs, colors: lColors)
        print("=====> SET POIS COLOR RESULT")
        print(result)
    }
    
    func resetPoisColor() {
        let lPoiIDs = mMapController.queryAllPoiIDs()
        print("=====> RESET POIS FROM FRAGMENT")
        print(lPoiIDs)
        let result = mMapController.resetPoisColor(poiIDs: lPoiIDs);
        print("=====> RESET POIS COLOR RESULT")
        print(result)
    }

    func getVersion() -> String {
        let lVersion = mMapController.getDataSDKVersion();
        print("=====> GET DATA SDK VERSION")
        return(lVersion)
    }
    
    // MARK: - VMEComputeRouteCallback
    /* @objc
    func computeRouteDidFinish(mapController: VMEMapController, parameters routeRequest: VMERouteRequest, result routeResult: VMERouteResult) -> Bool {
        print(String(format: "computeRouteDidFinish duration: %.0fmins and length: %.0fm ", (routeResult.duration) / 60, routeResult.length))
        return true
    }
    
    func computeRouteDidFail(mapController: VMEMapController, parameters routeRequest: VMERouteRequest, error: String) {
        print("computeRouteDidFail error: \(error)")
    }
    
    
    func computeRoute(_ origin: String, lDestinations: [String]) {
        print("=====> COMPUTE ROUTE")
        let lDestOrder = VMERouteDestinationsOrder.inOrder
        let lRouteRequest = VMERouteRequest(requestType: VMERouteRequestType.fastest, destinationsOrder: lDestOrder)
        lRouteRequest.setOrigin(origin)
        if (lRouteRequest.addDestinations(lDestinations)) {
            mMapController.computeRoute(lRouteRequest, callback: self)
        }
    } */

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

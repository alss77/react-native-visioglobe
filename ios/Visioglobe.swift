import Foundation
import UIKit
import VisioMoveEssential
import CoreLocation

@objc(Visioglobe)
class Visioglobe: UIViewController, VMEComputeRouteCallback, VMELifeCycleListener, VMEPoiFilterCallback {
    func computeRouteDidFinish(mapController: VisioMoveEssential.VMEMapController, parameters routeRequest: VisioMoveEssential.VMERouteRequest, result routeResult: VisioMoveEssential.VMERouteResult) -> Bool {
        return true;
    }
    
    func computeRouteDidFail(mapController: VisioMoveEssential.VMEMapController, parameters routeRequest: VisioMoveEssential.VMERouteRequest, error: String) {
    }
    
    @IBOutlet @objc var mMapView: VMEMapView!

    var mMapHash: String!

    var mMapPath: String!

    @objc
    var mMapController: VMEMapController!

    @objc
    var lRouteRequest: VMERouteRequest!
    
    @objc
    func presentVisioglobe() {
        print(self.mMapHash)
        DispatchQueue.main.async {
            print(self.mMapHash)
            print("=======> CALLING VIEW CONTROLLER")
            let visioglobeViewController = Visioglobe(nibName: "Visioglobe", bundle: nil)
            visioglobeViewController.mMapHash = self.mMapHash
            visioglobeViewController.mMapController = self.mMapController
            // visioglobeViewController.modalPresentationStyle = .fullScreen
            // visioglobeViewController.modalTransitionStyle = .crossDissolve
            UIApplication.shared.keyWindow?.rootViewController?.present(visioglobeViewController, animated: true, completion: nil)
            print("=======> CALLED VIEW CONTROLLER")
        }
    }

    @objc
    func setMapHash(_ mapHash: String) {
        mMapHash = mapHash
    }

    @objc
    func getMapHash(_ resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if let mapHash = mMapHash {
            resolve(mapHash)
        } else {
            let error = NSError(domain: "com.yourdomain", code: -1, userInfo: [NSLocalizedDescriptionKey: "Map hash not set"])
            reject("MAP_HASH_NOT_SET", "Map hash is not set", error)
        }
    }

    @objc
    func getMapController(_ resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        print("====> getMapController")
        print(mMapController)
        print(self.mMapController)
        if let mapController = mMapController {
            resolve(mapController)
        } else {
            let error = NSError(domain: "com.yourdomain", code: -1, userInfo: [NSLocalizedDescriptionKey: "Map Controller not set"])
            reject("MAP_CONTROLLER_NOT_SET", "Map Controller is not set", error)
        }
    }
    

    class func randomColor() -> UIColor? {
        let lRed = Int(arc4random() % 255)
        let lGreen = Int(arc4random() % 255)
        let lBlue = Int(arc4random() % 255)
        let lRandomColor = UIColor(red: CGFloat(Double(lRed) / 255.0), green: CGFloat(Double(lGreen) / 255.0), blue: CGFloat(Double(lBlue) / 255.0), alpha: 1.0)
        return lRandomColor
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        print("=======> VIEW DID LOAD")
        self.title = NSLocalizedString("Visioglobe.title", comment: "")

        print("=======> mMapController")
        print(mMapController)

        print("=======> VIEW DID LOAD LOAD MAP DATA")
        mMapController.loadMapData()

        print("=======> VIEW DID LOAD SET LISTENER")
        mMapController.setLifeCycleListener(self)

        /* if (self.responds(to: #selector(getter: UIViewController.edgesForExtendedLayout))) {
            self.edgesForExtendedLayout = UIRectEdge.init(rawValue: 0);
        } */
    }
    
    @objc
    func mapDataDidLoad(mapController: VMEMapController, venueData: [String: Any]) {
        print("=======> MAP DATA DID LOAD FUNCTION")
        print("=======>  mMapView data")
        print(mMapView)
        mMapController?.loadMapView(mapView: mMapView)
    }

    @objc
    func initController() -> VMEMapController? {
        guard Thread.isMainThread else {
            print("======> INIT CONTROLLER THREAD")
            print("======> Map Hash")
            print(self.mMapHash)
            var mapController: VMEMapController?
            DispatchQueue.main.sync {
                mapController = self.initController()
            }
            return mapController
        }
        print("======> INIT CONTROLLER")
        print("======> Map Hash")
        print(self.mMapHash)
        
        if mMapController == nil {
            mMapController = VMEMapController.initController(builderBlock: { builder in
                builder.mapHash = self.mMapHash
                builder.mapSecretCode = 0
            })
            return mMapController
        }

        return mMapController
    }

    @objc
    func loadMapData() {
        print("=======> LOAD MAP DATA FUNCTION")
        print("=======>  mMapController")
        print(mMapController)
        mMapController?.loadMapData()
    }

    @objc
    func setLifeCycleListener() {
        print("=======>  mMapController")
        print(mMapController)
        mMapController?.setLifeCycleListener(self)
    }

    /* @objc func listenerMapDataDidLoad() {
        mapDataDidLoad(mapController: mMapController, venueData: [:])
    } */

    deinit {
        mMapController?.unloadMapData()
        mMapController = nil
    }

    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(a*b)
    }

    @objc(setPoisColor:colors:withResolver:withRejecter:)
    func setPoisColor(lpoiIDs: [String], lColors: [String], resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) {
        let colors = lColors.compactMap { colorString in
            colorFromString(colorString)
        }

        print("======> setPoisColor args")
        print(lpoiIDs, lColors)

        print("======> mMapController")
        print(mMapController)

        let result = mMapController?.setPoisColor(poiIDs: lpoiIDs, colors: colors)
        print("======> RESULT setPoisColor")
        print(result)
    }

    @objc
    private func colorFromString(_ colorString: String) -> UIColor? {
        let trimmedString = colorString.trimmingCharacters(in: .whitespacesAndNewlines)
        
        // Remove the '#' character from the beginning
        let start = trimmedString.index(trimmedString.startIndex, offsetBy: 1)
        let hexString = String(trimmedString[start...])
        
        // Convert the hex string to integer values
        var rgbValue: UInt32 = 0
        Scanner(string: hexString).scanHexInt32(&rgbValue)
        
        // Extract the RGB components
        let red = CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0
        let green = CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0
        let blue = CGFloat(rgbValue & 0x0000FF) / 255.0
        
        // Create and return the UIColor object
        return UIColor(red: red, green: green, blue: blue, alpha: 1.0)
    }


    @objc
    func setPoisSize(_ lpoiIDs: [String], _ sizes: [Double], _ animated: [Bool]) {
        let poiSizes = sizes.map { scale in
            VMEPoiSize(scale: scale)
        }
        mMapController.setPoisSize(poiIDs: lpoiIDs, sizes: poiSizes, animated: animated)
    }

    @objc
    func resetPoisColor(_ lpoiIDs: [String]) -> [Bool] {
        return mMapController.resetPoisColor(poiIDs: lpoiIDs)
    }

    @objc
    func initRouteRequest() {
        let lDestOrder = VMERouteDestinationsOrder.inOrder
        lRouteRequest = VMERouteRequest(requestType: VMERouteRequestType.fastest, destinationsOrder: lDestOrder)
    }

    @objc
    func setOrigin(_ position: VMEPosition) {
        lRouteRequest.setOrigin(position)
    }

    @objc
    func addDestinations(_ positions: [VMEPosition]) {
        lRouteRequest.addDestinations(positions)
    }

    @objc
    func computeRoute() {
        mMapController.computeRoute(lRouteRequest, callback: self)
    }

    @objc
    func createLocationFromLocation(_ location: CLLocation) -> VMELocation? {
        return mMapController?.createLocationFromLocation(location);
    }

    @objc
    func createPositionFromLocation(_ location: CLLocation) -> VMEPosition? {
        return mMapController?.createPositionFromLocation(location);
    }

    @objc
    func placeAllColorSwitchAction(_ activate: NSNumber) {
        let shouldActivate = activate.boolValue
        print("=======>  placeAllColorSwitchAction shouldActivate")
        print(shouldActivate)
        let lpoiIDs = mMapController.queryAllPoiIDs()
        if shouldActivate == true {
            let lRandomColor = Visioglobe.randomColor()
            var lColors = [UIColor]()
            for _ in lpoiIDs {
                lColors.append(lRandomColor!)
            }
            let _ = mMapController.setPoisColor(poiIDs: lpoiIDs, colors: lColors)
        } else {
            let _ = mMapController.resetPoisColor(poiIDs: lpoiIDs)
        }
    }

    @objc
    func unloadMapData() {
        mMapController?.unloadMapData()
    }

    @objc
    func unloadMapView() {
        mMapController?.unloadMapView()
    }

    override func viewWillDisappear(_ animated: Bool) {
        print("=======> VIEW WILL DISAPPEAR!")
        // mMapController?.unloadMapData()
    }

    override func viewWillAppear(_ animated: Bool) {
        print("=======> VIEW WILL APPEAR!")
        // mMapController?.loadMapView(mMapView: mMapView)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        print("=======> VIEW DID APPEARED!")
    }
}

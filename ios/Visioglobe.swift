import Foundation
import UIKit
import VisioMoveEssential

@objc(Visioglobe)
class Visioglobe: UIViewController, VMELifeCycleListener, VMEPoiFilterCallback {
    @IBOutlet @objc var mMapView: VMEMapView!

    var mMapHash: String!

    var mMapPath: String!

    @objc
    var mMapController: VMEMapController!
    
    @objc
    func presentVisioglobe(_ mapHash: String) {
        DispatchQueue.main.async {
            self.mMapHash = mapHash

            print("=======> CALLING VIEW CONTROLLER")
            let visioglobeViewController = Visioglobe(nibName: "Visioglobe", bundle: nil)
            // visioglobeViewController.modalPresentationStyle = .fullScreen
            // visioglobeViewController.modalTransitionStyle = .crossDissolve
            UIApplication.shared.keyWindow?.rootViewController?.present(visioglobeViewController, animated: true, completion: nil)
            print("=======> CALLED VIEW CONTROLLER")
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

        if let mapHash = self.mMapHash {
            mMapController = VMEMapController.initController(builderBlock: { builder in
                builder.mapHash = mapHash
                builder.mapSecretCode = 0
            })
        } else {
            mMapController = VMEMapController.initController(builderBlock: { builder in
                builder.mapHash = "dev-m2ca47a978ce45fd3f3334b7a1078272ec07655bc"
                builder.mapSecretCode = 0
            })
        }

        print("=======> VIEW DID LOAD LOAD MAP DATA")
        mMapController.loadMapData()

        print("=======> VIEW DID LOAD SET LISTENER")
        mMapController.setLifeCycleListener(self)

        /* if (self.responds(to: #selector(getter: UIViewController.edgesForExtendedLayout))) {
            self.edgesForExtendedLayout = UIRectEdge.init(rawValue: 0);
        } */
    }

    @objc
    func initController() -> VMEMapController? {
        /* guard Thread.isMainThread else {
            var mapController: VMEMapController?
            DispatchQueue.main.sync {
                mapController = self.initController()
            }
            return mapController
        }
        print("INIT CONTROLLER")

        if mMapController == nil {
            mMapController = VMEMapController.initController(builderBlock: { builder in
                builder.mapHash = "dev-m2ca47a978ce45fd3f3334b7a1078272ec07655bc"
                builder.mapSecretCode = 0
            })
            return mMapController
        } */

        return mMapController
    }

    @objc
    func loadMapData() {
        print("=======> LOAD MAP DATA FUNCTION")
        // mMapController?.loadMapData()
    }

    @objc
    func setLifeCycleListener() {
        // mMapController?.setLifeCycleListener(self)
    }

    /* @objc func listenerMapDataDidLoad() {
        mapDataDidLoad(mapController: mMapController, venueData: [:])
    } */

    @objc
    func mapDataDidLoad(mapController: VMEMapController, venueData: [String: Any]) {
        print("=======> MAP DATA DID LOAD FUNCTION")
        print("=======>  mMapView data")
        print(mMapView)
        mMapController?.loadMapView(mapView: mMapView)
    }

    deinit {
        mMapController?.unloadMapData()
        mMapController = nil
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
        mMapController?.unloadMapData()
    }

    override func viewWillAppear(_ animated: Bool) {
        print("=======> VIEW WILL APPEAR!")
        // mMapController?.loadMapView(mMapView: mMapView)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        print("=======> VIEW DID APPEARED!")
    }
}

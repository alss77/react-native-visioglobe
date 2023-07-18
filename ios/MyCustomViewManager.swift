//
//  MyCustomViewManager.swift
//  Visioglobe
//
//  Created by Alassane on 17/07/2023.
//  Copyright © 2023 Facebook. All rights reserved.
//

import Foundation
import UIKit
import VisioMoveEssential

@objc(MyCustomViewManager)
class MyCustomViewManager: RCTViewManager {
  override func view() -> UIView! {
    return MyCustomView()
  }

  override static func requiresMainQueueSetup() -> Bool {
    return true
  }
}

class MyCustomView: UIView, VMELifeCycleListener {
    
    var mMapController: VMEMapController!
    @IBOutlet var mMapView: VMEMapView!

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }

    func setupView() {
        let view = loadViewFromNib()
        view.frame = bounds
        view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        addSubview(view)

        print("INIT View")
        self.backgroundColor = UIColor.blue
            
        /* mMapController = VMEMapController.initController(builderBlock: { builder in
            builder.mapHash = "dev-m2ca47a978ce45fd3f3334b7a1078272ec07655bc"
            builder.mapSecretCode = 0
        })
        print("mMapController")
        print(mMapController)
        mMapController.setLifeCycleListener(self)
        mMapController.loadMapData() */
    }

    func loadViewFromNib() -> UIView {
        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: "MyCustomView", bundle: bundle)
        let view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
        return view
    }

    func mapDataDidLoad(mapController: VMEMapController, venueData: [String: Any]) {
        print("=======> MAP DATA DID LOAD FUNCTION")
        print("=======>  mMapView data")
        print(mMapView)
        mMapController?.loadMapView(mapView: mMapView)
    }
}


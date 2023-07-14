/*
 * Copyright (c) Visioglobe SAS. All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.visioglobe.samples.mapviewsample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
// import com.visioglobe.samples.mapviewsample.R
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.VMEMapView
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

/**
 * This fragment is the simplest demo for VisioMove Essential's VMEMapView. It simply loads and
 * displays a map.
 */
class BasicDemoFragment : Fragment() {
    private var mMapController: VMEMapController? = null

    /**
     * The fragment's map view.
     */
    private var mMapView: VMEMapView? = null
    override fun onCreateView(
        pInflater: LayoutInflater,
        pContainer: ViewGroup?,
        pSavedInstanceState: Bundle?
    ): View? {
        if (mMapView == null) {
            /*mMapView =
                pInflater.inflate(R.layout.basic_demo_fragment, pContainer, false) as VMEMapView */
            val builder = VMEMapControllerBuilder()
            try {
                mMapController!!.deleteCachedMap("asset://map_bundle_theme.zip")
            } catch (ignored: Exception) {
            }
            builder.mapPath = "asset://map_bundle_theme.zip"
            builder.mapSecretCode = 0
            mMapController = VMEMapController(requireContext(), builder)
            // Set the life cycle listener
            mMapController!!.setLifeCycleListener(mLifeCycleListener)
            mMapController!!.loadMapView(mMapView!!)
            // Load the map
            mMapController!!.loadMapData()
        }
        return mMapView
    }

    override fun onPause() {
        super.onPause()
        mMapController!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        mMapController!!.onResume()
    }

    private val mLifeCycleListener: VMELifeCycleListener = object : VMELifeCycleListener() {
        override fun mapDidInitializeEngine() {
            val lFilePath = extractFromAssetsAndGetFilePath()
            if (!lFilePath.isEmpty()) {
                mMapController!!.setMapFont(lFilePath)
            } else {
                mMapController!!.setMapFont("shizuru_regular.ttf")
            }
        }

        override fun mapDataDidLoad(mapVenueInfo: JSONObject) {
            super.mapDataDidLoad(mapVenueInfo)
            mMapController!!.loadMapView(mMapView!!)
        }

        override fun mapViewDidLoad() {
            super.mapViewDidLoad()
        }

        override fun mapDidGainFocus() {
            super.mapDidGainFocus()
        }
    }

    private fun extractFromAssetsAndGetFilePath(): String {
        val f = File(requireContext().cacheDir.toString() + "/" + "artifika_regular.ttf")
        if (!f.exists()) {
            try {
                val `is` = requireContext().assets.open("artifika_regular.ttf")
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                val fos = FileOutputStream(f)
                fos.write(buffer)
                fos.close()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return f.path
    }
}

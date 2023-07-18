package com.reactnativevisioglobe;

import com.reactnativevisioglobe.R;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

// replace with your view's import
import com.visioglobe.visiomoveessential.VMEMapController;
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder;
import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import kotlin.jvm.internal.Intrinsics;

public class MyFragment extends Fragment {

  private static final String TAG = "MyFragment";
  private ViewGroup mFragment;

  private VMEMapController mMapController;
  private VMEMapView mMapView;

  private String mMapHash;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    Log.d(TAG, "====> Present Visioglobe");
    Intrinsics.checkNotNullParameter(inflater, "inflater");
    Log.d(TAG, "====> Inflater is set");
    if (this.mMapView == null) {
      this.mFragment = (ViewGroup) inflater.inflate(R.layout.map_view_sample, parent, false);
      VMEMapControllerBuilder builder = new VMEMapControllerBuilder();
      if (this.mFragment == null) {
        Log.d(TAG, "====> mFragment is null");
      }
      this.mMapView = this.mFragment.findViewById(com.visioglobe.visiomoveessential.R.id.map_view);
      if (this.mMapView == null) {
        Log.d(TAG, "====> mMapView is null");
      }
      VMEMapController mController;
      try {
        mController = this.mMapController;
        Intrinsics.checkNotNull(mController);
        // mController.deleteCachedMap("asset://map_bundle_theme.zip");
      } catch (Exception var6) {
      }

      builder.setMapHash("dev-c346e782b88c53bb6c891f439dbcc7e2cde0aaab");
      builder.setMapSecretCode(0);
      Context context = this.getContext();
      Intrinsics.checkNotNullExpressionValue(context, "requireContext()");
      this.mMapController = new VMEMapController(context, builder);
      mController = this.mMapController;
      Intrinsics.checkNotNull(mController);
      Log.d(TAG, "====> Set life cycle listener");
      mController.setLifeCycleListener(this.mLifeCycleListener);
      Log.d(TAG, "====> Set life cycle listener success");
      mController = this.mMapController;
      Intrinsics.checkNotNull(mController);
      Log.d(TAG, "====> Set Map View");
      VMEMapView mapView = this.mMapView;
      Intrinsics.checkNotNull(mapView);
      Log.d(TAG, "====> Load map view");
      mController.loadMapView(mapView);
      mController = this.mMapController;
      Intrinsics.checkNotNull(mController);
      Log.d(TAG, "====> Load map data");
      mController.loadMapData();
    }

    return (View)this.mMapView;
  }

  private final VMELifeCycleListener mLifeCycleListener = (VMELifeCycleListener)(new VMELifeCycleListener() {
    public void mapDidInitializeEngine() {
      String lFilePath = MyFragment.this.extractFromAssetsAndGetFilePath();
      CharSequence var2 = (CharSequence)lFilePath;
      VMEMapController controller;
      if (var2.length() != 0) {
        controller = MyFragment.this.mMapController;
        Intrinsics.checkNotNull(controller);
        controller.setMapFont(lFilePath);
      } else {
        controller = MyFragment.this.mMapController;
        Intrinsics.checkNotNull(controller);
        controller.setMapFont("shizuru_regular.ttf");
      }
    }

    public void mapDataDidLoad(@NotNull JSONObject mapVenueInfo) {
      Log.d(TAG, "====> MAP DATA DID LOAD");
      Intrinsics.checkNotNullParameter(mapVenueInfo, "mapVenueInfo");
      super.mapDataDidLoad(mapVenueInfo);
      VMEMapController var10000 = MyFragment.this.mMapController;
      Intrinsics.checkNotNull(var10000);
      VMEMapView var10001 = MyFragment.this.mMapView;
      Intrinsics.checkNotNull(var10001);
      var10000.loadMapView(var10001);
    }

    public void mapViewDidLoad() {
      super.mapViewDidLoad();
    }

    public void mapDidGainFocus() {
      super.mapDidGainFocus();
    }
  });

  private final String extractFromAssetsAndGetFilePath() {
    StringBuilder var10002 = new StringBuilder();
    Context var10003 = this.getContext();
    Intrinsics.checkNotNullExpressionValue(var10003, "requireContext()");
    File f = new File(var10002.append(var10003.getCacheDir().toString()).append("/").append("artifika_regular.ttf").toString());
    if (!f.exists()) {
      try {
        Context var10000 = this.getContext();
        Intrinsics.checkNotNullExpressionValue(var10000, "requireContext()");
        InputStream var7 = var10000.getAssets().open("artifika_regular.ttf");
        Intrinsics.checkNotNullExpressionValue(var7, "requireContext().assets.…n(\"artifika_regular.ttf\")");
        InputStream is = var7;
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(buffer);
        fos.close();
      } catch (Exception var6) {
        // throw (Throwable)(new RuntimeException((Throwable)var6));
      }
    }

    String var8 = f.getPath();
    Intrinsics.checkNotNullExpressionValue(var8, "f.path");
    return var8;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // do any logic that should happen in an `onCreate` method, e.g:
    // customView.onCreate(savedInstanceState);
  }

  @Override
  public void onPause() {
    super.onPause();
    // do any logic that should happen in an `onPause` method
    // e.g.: customView.onPause();
  }

  @Override
  public void onResume() {
    super.onResume();
    // do any logic that should happen in an `onResume` method
    // e.g.: customView.onResume();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    // do any logic that should happen in an `onDestroy` method
    // e.g.: customView.onDestroy();
  }
}

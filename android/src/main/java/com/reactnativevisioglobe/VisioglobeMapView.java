package com.reactnativevisioglobe;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactMethod;
import com.visioglobe.visiomoveessential.VMEMapController;
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder;
import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
public final class VisioglobeMapView extends Fragment {
  private VMEMapController mMapController;
  private VMEMapView mMapView;

  private String mMapHash;

  private static final String TAG = "VisioglobeMapView";

  public final void setMapHash(String mapHash) {
    mMapHash = mapHash;
  }

  public final String getMapHash() {
    return mMapHash;
  }

  public final void initController(Context context) {
    VMEMapControllerBuilder builder = new VMEMapControllerBuilder();

    // builder.setMapPath("asset://map_bundle_theme.zip");
    builder.setMapHash(mMapHash);
    builder.setMapSecretCode(0);
    Log.d(TAG, "====> Init Controller");
    if (context != null) {
      Log.d(TAG, "====> Context is set");
    } else {
      Log.d(TAG, "====> Context is not set");
    }
    this.mMapController = new VMEMapController(context, builder);
    Log.d(TAG, "====> Controller is set");
  }
  private final VMELifeCycleListener mLifeCycleListener = (VMELifeCycleListener)(new VMELifeCycleListener() {
    public void mapDidInitializeEngine() {
      String lFilePath = VisioglobeMapView.this.extractFromAssetsAndGetFilePath();
      CharSequence var2 = (CharSequence)lFilePath;
      VMEMapController var10000;
      if (var2.length() != 0) {
        var10000 = VisioglobeMapView.this.mMapController;
        Intrinsics.checkNotNull(var10000);
        var10000.setMapFont(lFilePath);
      } else {
        var10000 = VisioglobeMapView.this.mMapController;
        Intrinsics.checkNotNull(var10000);
        var10000.setMapFont("shizuru_regular.ttf");
      }

    }

    public void mapDataDidLoad(@NotNull JSONObject mapVenueInfo) {
      Intrinsics.checkNotNullParameter(mapVenueInfo, "mapVenueInfo");
      super.mapDataDidLoad(mapVenueInfo);
      VMEMapController var10000 = VisioglobeMapView.this.mMapController;
      Intrinsics.checkNotNull(var10000);
      VMEMapView var10001 = VisioglobeMapView.this.mMapView;
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

  @Override
  public View onCreateView(@NotNull LayoutInflater pInflater, @Nullable ViewGroup pContainer, @Nullable Bundle pSavedInstanceState) {
    Log.d(TAG, "====> On Create View");
    Intrinsics.checkNotNullParameter(pInflater, "pInflater");
    if (this.mMapView == null) {
      VMEMapControllerBuilder builder = new VMEMapControllerBuilder();

      VMEMapController var10000;
      try {
        var10000 = this.mMapController;
        Intrinsics.checkNotNull(var10000);
        var10000.deleteCachedMap("asset://map_bundle_theme.zip");
      } catch (Exception var6) {
      }

      // builder.setMapPath("asset://map_bundle_theme.zip");
      builder.setMapHash(mMapHash);
      builder.setMapSecretCode(0);
      Context var10003 = this.requireContext();
      Intrinsics.checkNotNullExpressionValue(var10003, "requireContext()");
      this.mMapController = new VMEMapController(var10003, builder);
      var10000 = this.mMapController;
      Intrinsics.checkNotNull(var10000);
      var10000.setLifeCycleListener(this.mLifeCycleListener);
      var10000 = this.mMapController;
      Intrinsics.checkNotNull(var10000);
      VMEMapView var10001 = this.mMapView;
      Intrinsics.checkNotNull(var10001);
      var10000.loadMapView(var10001);
      var10000 = this.mMapController;
      Intrinsics.checkNotNull(var10000);
      var10000.loadMapData();
    }

    return (View)this.mMapView;
  }

  public void onPause() {
    super.onPause();
    VMEMapController var10000 = this.mMapController;
    Intrinsics.checkNotNull(var10000);
    var10000.onPause();
  }

  public void onResume() {
    super.onResume();
    VMEMapController var10000 = this.mMapController;
    Intrinsics.checkNotNull(var10000);
    var10000.onResume();
  }

  private final String extractFromAssetsAndGetFilePath() {
    StringBuilder var10002 = new StringBuilder();
    Context var10003 = this.requireContext();
    Intrinsics.checkNotNullExpressionValue(var10003, "requireContext()");
    File f = new File(var10002.append(var10003.getCacheDir().toString()).append("/").append("artifika_regular.ttf").toString());
    if (!f.exists()) {
      try {
        Context var10000 = this.requireContext();
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

  // $FF: synthetic method
  public static final void access$setMMapController$p(VisioglobeMapView $this, VMEMapController var1) {
    $this.mMapController = var1;
  }

  // $FF: synthetic method
  public static final void access$setMMapView$p(VisioglobeMapView $this, VMEMapView var1) {
    $this.mMapView = var1;
  }

  // $FF: synthetic method
  public void onDestroyView() {
    super.onDestroyView();
    // this._$_clearFindViewByIdCache();
  }
}

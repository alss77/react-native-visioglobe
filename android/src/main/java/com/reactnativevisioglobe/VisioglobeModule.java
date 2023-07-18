package com.reactnativevisioglobe;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import com.visioglobe.visiomoveessential.VMEMapController;
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder;
import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener;

public final class VisioglobeModule extends ReactContextBaseJavaModule {
  private static final Logger logger = Logger.getLogger(VisioglobeModule.class.getName());
  private String mMapHash;

  private VMEMapController mMapController;
  private VMEMapView mMapView;
  private VisioglobeMapView visioglobeMapView;

  private static final String TAG = "AppActivity";

  private final ReactApplicationContext reactContext;
  private Fragment fragmentClass;

  VisioglobeModule(ReactApplicationContext context) {
      super(context);
      reactContext = context;
      fragmentClass = new Fragment();
      visioglobeMapView = new VisioglobeMapView();
      Log.d(TAG, "Debug log message");
      Log.i(TAG, "Information log message");
      Log.w(TAG, "Warning log message");
      Log.e(TAG, "Error log message");
   }

   @Override
   public String getName() {
      return "Visioglobe";
   }

   @ReactMethod
   public final void setMapHash(String mapHash) {
    // visioglobeMapView.setMapHash(mapHash);
    mMapHash = mapHash;
  }

  @ReactMethod
  public final void getMapHash(@NotNull Promise promise) {
    // visioglobeMapView.getMapHash()
    promise.resolve(mMapHash);
  }

   @ReactMethod
   public final void multiply(int a, int b, @NotNull Promise promise) {
      // Intrinsics.checkNotNullParameter(promise, "promise");
      promise.resolve(a * b);
   }

   @ReactMethod
   public final void initController() {
      //visioglobeMapView.initController(reactContext.getApplicationContext());
     VMEMapControllerBuilder builder = new VMEMapControllerBuilder();

     // builder.setMapPath("asset://map_bundle_theme.zip");
     builder.setMapHash(mMapHash);
     // builder.setMapSecretCode(0);
     Context context = reactContext.getApplicationContext();
     Log.d(TAG, "====> Init Controller");
     if (context != null) {
       Log.d(TAG, "====> Context is set");
       this.mMapController = new VMEMapController(context, builder);
       Log.d(TAG, "====> Controller is set");
     } else {
       Log.d(TAG, "====> Context is not set");
     }
   }

  private final VMELifeCycleListener mLifeCycleListener = (VMELifeCycleListener)(new VMELifeCycleListener() {
    public void mapDidInitializeEngine() {
      Log.d(TAG, "====> mapDidInitializeEngine");
      String lFilePath = VisioglobeModule.this.extractFromAssetsAndGetFilePath();
      if (lFilePath != null) {
        Log.d(TAG, "====> lFilePath is set");
      } else {
        Log.d(TAG, "====> lFilePath is null");
      }
      CharSequence var2 = (CharSequence)lFilePath;
      VMEMapController controller;
      if (var2.length() != 0) {
        Log.d(TAG, "====> var2.length() != 0");
        controller = VisioglobeModule.this.mMapController;
        Intrinsics.checkNotNull(controller);
        controller.setMapFont(lFilePath);
      } else {
        Log.d(TAG, "====> var2.length() == 0");
        controller = VisioglobeModule.this.mMapController;
        Intrinsics.checkNotNull(controller);
        controller.setMapFont("shizuru_regular.ttf");
      }
    }

    public void mapDataDidLoad(@NotNull JSONObject mapVenueInfo) {
      Log.d(TAG, "====> MAP DATA DID LOAD");
      Intrinsics.checkNotNullParameter(mapVenueInfo, "mapVenueInfo");
      super.mapDataDidLoad(mapVenueInfo);
      VMEMapController var10000 = VisioglobeModule.this.mMapController;
      Intrinsics.checkNotNull(var10000);
      VMEMapView var10001 = VisioglobeModule.this.mMapView;
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

  public void mapDidInitializeEngine() {
    String lFilePath = VisioglobeModule.this.extractFromAssetsAndGetFilePath();
    CharSequence var2 = (CharSequence)lFilePath;
    VMEMapController var10000;
    if (var2.length() != 0) {
      var10000 = VisioglobeModule.this.mMapController;
      Intrinsics.checkNotNull(var10000);
      var10000.setMapFont(lFilePath);
    } else {
      var10000 = VisioglobeModule.this.mMapController;
      Intrinsics.checkNotNull(var10000);
      var10000.setMapFont("shizuru_regular.ttf");
    }

  }

  private final String extractFromAssetsAndGetFilePath() {
    StringBuilder var10002 = new StringBuilder();
    Context var10003 = reactContext.getApplicationContext();
    Intrinsics.checkNotNullExpressionValue(var10003, "requireContext()");
    File f = new File(var10002.append(var10003.getCacheDir().toString()).append("/").append("artifika_regular.ttf").toString());
    if (!f.exists()) {
      try {
        Context var10000 = reactContext.getApplicationContext();
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

  @ReactMethod
  public View presentVisioglobe() {
    Log.d(TAG, "====> Present Visioglobe");
    if (this.mMapView == null) {
      VMEMapController controller;
      try {
        controller = this.mMapController;
        Intrinsics.checkNotNull(controller);
        controller.deleteCachedMap(mMapHash);
      } catch (Exception var6) {
      }

      Context var10003 = reactContext.getApplicationContext();
      Intrinsics.checkNotNullExpressionValue(var10003, "requireContext()");
      controller = this.mMapController;
      Intrinsics.checkNotNull(controller);
      Log.d(TAG, "====> Set life cycle listener");
      controller.setLifeCycleListener(this.mLifeCycleListener);
      Log.d(TAG, "====> Set life cycle listener success");
      controller = this.mMapController;
      Intrinsics.checkNotNull(controller);
      Log.d(TAG, "====> Set Map View");
      VMEMapView mapView = this.mMapView;
      Intrinsics.checkNotNull(mapView);
      Log.d(TAG, "====> Load map view");
      controller.loadMapView(mapView);
      controller = this.mMapController;
      Intrinsics.checkNotNull(controller);
      Log.d(TAG, "====> Load map data");
      controller.loadMapData();
    }

    return (View)this.mMapView;
  }

  // $FF: synthetic method
  public static final void access$setMMapController$p(VisioglobeModule $this, VMEMapController var1) {
    $this.mMapController = var1;
  }

  // $FF: synthetic method
  public static final void access$setMMapView$p(VisioglobeModule $this, VMEMapView var1) {
    $this.mMapView = var1;
  }

  // $FF: synthetic method
  public void onDestroyView() {
    // fragmentClass.onDestroyView();
    // this._$_clearFindViewByIdCache();
  }
}

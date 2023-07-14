package com.reactnativevisioglobe;

import android.content.Context;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import com.visioglobe.visiomoveessential.VMEMapController;
import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener;

public final class VisioglobeModule extends ReactContextBaseJavaModule {
  private static final Logger logger = Logger.getLogger(VisioglobeModule.class.getName());
  private String mMapHash;

  private VMEMapController mMapController;
  private VMEMapView mMapView;
   VisioglobeModule(ReactApplicationContext context) {
      super(context);
   }

   @Override
   public String getName() {
      return "Visioglobe";
   }

   @ReactMethod
   public final void setMapHash(String mapHash) {
    mMapHash = mapHash;
  }

  @ReactMethod
  public final void getMapHash(@NotNull Promise promise) {
    promise.resolve(mMapHash);
  }

   @ReactMethod
   public final void multiply(int a, int b, @NotNull Promise promise) {
      // Intrinsics.checkNotNullParameter(promise, "promise");
      promise.resolve(a * b);
   }

  /* private final VMELifeCycleListener mLifeCycleListener = (VMELifeCycleListener)(new VMELifeCycleListener() {
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

    public void mapDataDidLoad(@NotNull JSONObject mapVenueInfo) {
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
  public static final void access$setMMapController$p(VisioglobeModule $this, VMEMapController var1) {
    $this.mMapController = var1;
  }

  // $FF: synthetic method
  public static final void access$setMMapView$p(VisioglobeModule $this, VMEMapView var1) {
    $this.mMapView = var1;
  }

  // $FF: synthetic method
  public void onDestroyView() {
    super.onDestroyView();
    // this._$_clearFindViewByIdCache();
  } */
}

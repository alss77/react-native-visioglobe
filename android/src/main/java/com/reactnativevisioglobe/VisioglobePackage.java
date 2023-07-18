package com.reactnativevisioglobe;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class VisioglobePackage implements ReactPackage {
  /* @NotNull
  public List createNativeModules(@NotNull ReactApplicationContext reactContext) {
    Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    return CollectionsKt.listOf(new VisioglobeModule(reactContext));
  } */


    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
      return Arrays.<ViewManager>asList(
        new MyViewManager(reactContext)
      );
    }

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new VisioglobeModule(reactContext));

        return modules;
    }

  /* @NotNull
  public List createViewManagers(@NotNull ReactApplicationContext reactContext) {
    Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    return CollectionsKt.emptyList();
  } */
}


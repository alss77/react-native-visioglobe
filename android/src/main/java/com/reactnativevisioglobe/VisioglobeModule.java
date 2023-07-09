package com.reactnativevisioglobe;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class VisioglobeModule extends ReactContextBaseJavaModule {

    @Override
    public String getName() {
        return "VisioglobeModule";
    }

   /* @ReactMethod
   public final void multiply(int a, int b, @NotNull Promise promise) {
      Intrinsics.checkNotNullParameter(promise, "promise");
      promise.resolve(a * b);
   } */

   VisioglobeModule(ReactApplicationContext context) {
        super(context);
    }

   /* public VisioglobeModule(@NotNull ReactApplicationContext reactContext) {
      Intrinsics.checkNotNullParameter(reactContext, "reactContext");
      super(reactContext);
   } */
}
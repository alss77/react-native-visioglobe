package com.reactnativevisioglobe;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
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


  public VisioglobeModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

   @Override
   public String getName() {
      return "VisioglobeModule";
   }

  @ReactMethod
  public final void customFunctionToCall(final int tag) {
    Log.d("REF", "====> TEST LOG FROM MODULE");
    Log.d("REF", "The value of tag is: " + tag);
    Log.d("REF", "====> SECOND TEST LOG FROM MODULE");
    final ReactApplicationContext context = reactContext;

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();

    // Use the tag to find the fragment.
    VisioFragment myFragment = (VisioFragment) activity.getSupportFragmentManager().findFragmentById(tag);
    myFragment.customFunctionToCall();
  }

  @ReactMethod
  public final void setPois(final int tag, final String data) {
    Log.d("REF", "====> SET POIS FROM MODULE");
    Log.d("REF", "ARGS: " + data);
    final ReactApplicationContext context = reactContext;

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();

    // Use the tag to find the fragment.
    VisioFragment myFragment = (VisioFragment) activity.getSupportFragmentManager().findFragmentById(tag);
    myFragment.setPois(data);
  }


  @ReactMethod
  public final void resetPoisColor(final int tag) {
    Log.d("REF", "====> RESET POIS FROM MODULE");
    final ReactApplicationContext context = reactContext;

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();

    // Use the tag to find the fragment.
    VisioFragment myFragment = (VisioFragment) activity.getSupportFragmentManager().findFragmentById(tag);
    myFragment.resetPoisColor();
  }

  @ReactMethod
  public final void setPoisColor(final int tag, ReadableArray poiIDs) {
    Log.d("REF", "====> SET POIS COLOR FROM MODULE");
    Log.d("REF", "ARGS: " + poiIDs);

    ArrayList<String> poiIDList = new ArrayList<String>();

    for (int i = 0; i < poiIDs.size(); i++) {
      if (poiIDs.getType(i) == ReadableType.String) {
        poiIDList.add(poiIDs.getString(i));
      }
    }

    Log.d("REF", "ARGS: " + poiIDList);

    final ReactApplicationContext context = reactContext;

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();

    // Use the tag to find the fragment.
    VisioFragment myFragment = (VisioFragment) activity.getSupportFragmentManager().findFragmentById(tag);
    myFragment.setPoisColor(poiIDList);
  }

  @ReactMethod
  public final void computeRoute(final int tag, String origin, ReadableArray destinations, Boolean optimize) {
    Log.d("REF", "====> RESET POIS FROM MODULE");

    ArrayList<String> destinationList = new ArrayList<String>();

    for (int i = 0; i < destinations.size(); i++) {
      if (destinations.getType(i) == ReadableType.String) {
        destinationList.add(destinations.getString(i));
      }
    }

    Log.d("REF", "ARGS: " + destinationList);

    final ReactApplicationContext context = reactContext;

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();

    // Use the tag to find the fragment.
    VisioFragment myFragment = (VisioFragment) activity.getSupportFragmentManager().findFragmentById(tag);
    myFragment.computeRoute(origin, destinationList, optimize);
  }

  @ReactMethod
  public final void getPoiPosition(final int tag, final String poi) {
    Log.d("REF", "====> GET POI POSITION FROM MODULE");
    Log.d("REF", "ARGS: " + poi);
    final ReactApplicationContext context = reactContext;

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();

    // Use the tag to find the fragment.
    VisioFragment myFragment = (VisioFragment) activity.getSupportFragmentManager().findFragmentById(tag);
    myFragment.getPoiPosition(poi);
  }
}

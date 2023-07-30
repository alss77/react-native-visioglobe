package com.reactnativevisioglobe;

import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VisioMapViewManager extends ViewGroupManager<FrameLayout> {

  public static final String REACT_CLASS = "VisioMapViewManager";
  public final int COMMAND_CREATE = 1;
  public final int COMMAND_SET_POIS = 2;
  public final int COMMAND_RESET_POIS_COLOR = 3;
  public final int COMMAND_SET_POIS_COLOR = 4;
  public final int COMMAND_COMPUTE_ROUTE = 5;
  public final int COMMAND_GET_POI_POSITION = 6;

  private int reactNativeViewId;
  private int propWidth;
  private int propHeight;

  private String propMapHash;
  private String propMapPath;
  private int propSecret;


  ReactApplicationContext reactContext;

  public VisioMapViewManager(ReactApplicationContext reactContext) {
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  /**
   * Return a FrameLayout which will later hold the Fragment
   */
  @Override
  public FrameLayout createViewInstance(ThemedReactContext reactContext) {
    return new FrameLayout(reactContext);
  }

  /**
   * Map the "create" command to an integer
   */
  @Nullable
  @Override
  public Map<String, Integer> getCommandsMap() {
    Map<String, Integer> commands = new HashMap<>();
    commands.put("create", COMMAND_CREATE);
    commands.put("setPois", COMMAND_SET_POIS);
    commands.put("resetPoisColor", COMMAND_RESET_POIS_COLOR);
    commands.put("setPoisColor", COMMAND_SET_POIS_COLOR);
    commands.put("computeRoute", COMMAND_COMPUTE_ROUTE);
    commands.put("getPoiPosition", COMMAND_GET_POI_POSITION);
    return commands;
  }

  /**
   * Handle "create" command (called from JS) and call createFragment method
   */
  @Override
  public void receiveCommand(
    @NonNull FrameLayout root,
    String commandId,
    @Nullable ReadableArray args
  ) {
    Log.d("COMMAND", "NEW COMMAND RECEIVED");
    Log.d("COMMAND", commandId);
    Log.d("COMMAND", String.valueOf(args));
    super.receiveCommand(root, commandId, args);
    // int commandIdInt = Integer.parseInt(commandId);

    final ReactApplicationContext context = reactContext;

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();
    VisioFragment myFragment = (VisioFragment) activity.getSupportFragmentManager().findFragmentById(reactNativeViewId);
    switch (commandId) {
      case "1":
        reactNativeViewId = args.getInt(0);
        createFragment(root, reactNativeViewId);
        break;
      case "setPois":
        String data = args.getString(0);
        myFragment.setPois(data);
        break;
      case "setPoisColor":
        ReadableArray poiIDs = args.getArray(0);
        ArrayList<String> poiIDList = new ArrayList<String>();

        for (int i = 0; i < poiIDs.size(); i++) {
          if (poiIDs.getType(i) == ReadableType.String) {
            poiIDList.add(poiIDs.getString(i));
          }
        }
        myFragment.setPoisColor(poiIDList);
        break;
      case "resetPoisColor":
        myFragment.resetPoisColor();
        break;
      case "computeRoute":
        String origin = args.getString(0);
        Boolean optimize = args.getBoolean(2);
        ReadableArray destinations = args.getArray(1);
        ArrayList<String> destinationList = new ArrayList<String>();

        for (int i = 0; i < destinations.size(); i++) {
          if (destinations.getType(i) == ReadableType.String) {
            destinationList.add(destinations.getString(i));
          }
        }
        myFragment.computeRoute(origin, destinationList, optimize);
        break;
      case "getPoiPosition":
        String poi = args.getString(0);
        myFragment.getPoiPosition(poi);
        break;
      default: {}
    }
  }

  @ReactPropGroup(names = {"width", "height"}, customType = "Style")
  public void setStyle(FrameLayout view, int index, Integer value) {
    if (index == 0) {
      propWidth = value;
    }

    if (index == 1) {
      propHeight = value;
    }
  }

  @ReactProp(name = "mapHash")
  public void setMapHash(FrameLayout view, String value) {
    propMapHash = value;
  }

  @ReactProp(name = "mapPath")
  public void setMapPath(FrameLayout view, String value) {
    propMapPath = value;
  }

  @ReactProp(name = "mapSecret")
  public void setMapSecret(FrameLayout view, int value) {
    propSecret = value;
  }

  /**
   * Replace your React Native view with a custom fragment
   */
  public void createFragment(FrameLayout root, int reactNativeViewId) {
    ViewGroup parentView = (ViewGroup) root.findViewById(reactNativeViewId);
    setupLayout(parentView);

    Log.d("VisioMapViewManager", "====> CALLED");
    final VisioFragment myFragment = new VisioFragment(propMapHash, propMapPath, propSecret);
    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();
    activity.getSupportFragmentManager()
      .beginTransaction()
      .replace(reactNativeViewId, myFragment, String.valueOf(reactNativeViewId))
      .commit();
  }

  public void setupLayout(View view) {
    Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
      @Override
      public void doFrame(long frameTimeNanos) {
        manuallyLayoutChildren(view);
        view.getViewTreeObserver().dispatchOnGlobalLayout();
        Choreographer.getInstance().postFrameCallback(this);
      }
    });
  }

  /**
   * Layout all children properly
   */
  public void manuallyLayoutChildren(View view) {
    // propWidth and propHeight coming from react-native props
    int width = propWidth;
    int height = propHeight;

    view.measure(
      View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
      View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));

    view.layout(0, 0, width, height);
  }
}

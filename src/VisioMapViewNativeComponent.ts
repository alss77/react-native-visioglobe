import type { ViewProps, HostComponent } from 'react-native';
import { Platform } from 'react-native';
import './VisioTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import codegenNativeCommands from 'react-native/Libraries/Utilities/codegenNativeCommands';

export interface NativeProps extends ViewProps {
  mapPath?: string;
  mapSecret?: number;
  mapHash?: string;
  // other props go here...
}

type NativeComponentType = HostComponent<NativeProps>;

interface NativeCommands {
  //OK ANDROID & iOS
  setPois: (
    viewRef: React.ElementRef<NativeComponentType>,
    data: string
  ) => void;
  resetPoisColor: (viewRef: React.ElementRef<NativeComponentType>) => void;
  setPoisColor: (
    viewRef: React.ElementRef<NativeComponentType>,
    poiIDs: Array<string>
  ) => void;
  //ComputeROute to update
  computeRoute: (
    viewRef: React.ElementRef<NativeComponentType>,
    origin: string,
    destinations: Array<string>
  ) => void;

  //To Update
  getPoiPosition: (
    viewRef: React.ElementRef<NativeComponentType>,
    poiID: string
  ) => void;

  //To Update
  setSelectorViewVisible: (
    viewRef: React.ElementRef<NativeComponentType>, 
    visible: boolean
    ) => void;

    //OK ANDROID Update iOs
    getVersion: (
      viewRef: React.ElementRef<NativeComponentType>, 
      requestId: string
      ) => Promise<string>;
    
    //getDataSDKVersion: (viewRef: React.ElementRef<NativeComponentType>) => string;
    
    //TODO
    getMinDataSDKVersion: (
      viewRef: React.ElementRef<NativeComponentType>
      ) => string;

    /**
    setBuildingListener: (VMEBuildingListener) => void;
    setCameraListener: (VMECameraListener) => void;
    setLocationTrackingModeListener : (VMELocationTrackingModeListener) => void;
    setPoiListener: (VMEPoiListener) => void;
    setMapListener: (VMEMapListener) => void;
    animateCamera: (VMECameraUpdate, duration: Double, callback: VMEAnimationCallback?) => void;
    getCameraContext: () => VMECameraContext?;
    updateCamera: (VMECameraUpdate)=> void;
    animateScene : (VMESceneUpdate) => void;
    updateScene : (VMESceneUpdate) => void;
    computeRoute : (VMERouteRequest, callback: VMEComputeRouteCallback?) => void;
    createLocationFromLocation(CLLocation) => VMELocation?;
    createPositionFromLocation : (CLLocation) => VMEPosition?;
    getLocationTrackingMode: () => VMELocationTrackingMode;
    setLocationTrackingMode: (VMELocationTrackingMode);
    getLocationTrackingButtonToggleModes: () =>[VMELocationTrackingMode];
    setLocationTrackingButtonToggleModes: ([String]);
    getNavigationHeaderViewVisible : () => Bool;
    setNavigationHeaderViewVisible : (Bool);
    getSelectorViewVisible : () => Bool;
    setSelectorViewVisible : (Bool);
    removePoi : (poiID: String) => Bool;
    removePois : (poiIDs: [String]) => [Bool];
    getCategory : (categoryID: String) => VMECategory?;
    getPoi : (poiID: String) => VMEPoi?;
    getPoiPosition : (poiID: String) => VMEPosition?;
    getPoiBoundingPositions : (poiID: String) => [VMEPosition];
    queryAllCategoryIDs : () => [String];
    queryAllPoiIDs : () => [String];
    queryPois : (with: VMEPoiFilter, callback: VMEPoiFilterCallback?);
    resetPoiColor : (poiID: String) => Bool;
    resetPoisColor : (poiIDs: [String]) => [Bool];
    setPoiSize : (poiID: String, size: VMEPoiSize, animated: Bool) => Bool;
    setPoisSize : (poiIDs: [String], sizes: [VMEPoiSize], animated: [Bool]) => [Bool];
    setPoiPosition : (poiID: String, position: VMEPosition, animated: Bool) => Bool;
    setPoisPosition : (poiIDs: [String], positions: [VMEPosition], animated: [Bool]) => [Bool];
    setPoiColor : (String, color: UIColor) => Bool;
    setPoisColor : (poiIDs: [String], colors: [UIColor]) => [Bool];
    showPoiInfo : (poiID: String);
    setCategories : (data: String) =>[String : Any];

    **/
}

export const Commands: NativeCommands = codegenNativeCommands<NativeCommands>({
  supportedCommands: [
    'setPois',
    'resetPoisColor',
    'computeRoute',
    'setPoisColor',
    'getPoiPosition',
    'setSelectorViewVisible',
    'getVersion',
    //'getDataSDKVersion', 
    'getMinDataSDKVersion',
  ],
});

const VisioMapView = codegenNativeComponent<NativeProps>(
  Platform.OS === 'android' ? 'VisioMapViewManager' : 'VisioMapView'
) as HostComponent<NativeProps>;
export default VisioMapView;

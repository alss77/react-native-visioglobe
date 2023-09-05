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
  setPois: (
    viewRef: React.ElementRef<NativeComponentType>,
    data: string
  ) => void;
  resetPoisColor: (viewRef: React.ElementRef<NativeComponentType>) => void;
  setPoisColor: (
    viewRef: React.ElementRef<NativeComponentType>,
    poiIDs: Array<string>
  ) => void;
  computeRoute: (
    viewRef: React.ElementRef<NativeComponentType>,
    origin: string,
    destinations: Array<string>
  ) => void;
  getPoiPosition: (
    viewRef: React.ElementRef<NativeComponentType>,
    poiID: string
  ) => void;
  setSelectorViewVisible: (
    viewRef: React.ElementRef<NativeComponentType>, 
    visible: boolean
    ) => void;

    getVersion: (
      viewRef: React.ElementRef<NativeComponentType>, requestId: string
      ) => Promise<string>;
    
    //getDataSDKVersion: (viewRef: React.ElementRef<NativeComponentType>) => string;
    
    getMinDataSDKVersion: (
      viewRef: React.ElementRef<NativeComponentType>
      ) => string;
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

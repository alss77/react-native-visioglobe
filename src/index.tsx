import PropTypes from 'prop-types';
import {
  NativeModules,
  requireNativeComponent,
  ViewPropTypes,
} from 'react-native';

// export const MyViewManager = requireNativeComponent('MyViewManager');

const VisioMapView = requireNativeComponent('VisioMapView');

VisioMapView.propTypes = {
  ...ViewPropTypes,
  mapHash: PropTypes.string,
  mapPath: PropTypes.string,
  mapSecret: PropTypes.number,
};

export { VisioMapView };

type VisioglobeType = {
  multiply(a: number, b: number): Promise<number>;
  presentVisioglobe(): Promise<any>;
  placeAllColorSwitchAction(activate: boolean): Promise<any>;
  initController(): Promise<any>;
  loadMapData(): Promise<any>;
  setLifeCycleListener(): Promise<any>;
  setMapHash(mapHash?: string): Promise<any>;
  getMapHash(): Promise<any>;
  getMapController(): Promise<any>;
  unloadMapView(): Promise<any>;
  unloadMapData(): Promise<any>;
  setPoisColor(lpoiIDs: string[], lColors: string[]): Promise<boolean[]>;
  setPoisSize(
    lpoiIDs: string[],
    sizes: number[],
    animated: boolean[]
  ): Promise<boolean[]>;
  resetPoisColor(lpoiIDs: string[]): Promise<boolean[]>;
  initRouteRequest(): Promise<any>;
  setOrigin(position: any): Promise<any>;
  addDestinations(positions: any[]): Promise<any>;
  computeRoute(): Promise<any>;
  createLocationFromLocation(location: any): Promise<any>;
  createPositionFromLocation(location: any): Promise<any>;
  // listenerMapDataDidLoad(data: any): Promise<any>;
};

const { Visioglobe } = NativeModules;

export default Visioglobe as VisioglobeType;

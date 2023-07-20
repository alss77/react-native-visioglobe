import PropTypes from 'prop-types';
import {
  NativeModules,
  ViewPropTypes,
  requireNativeComponent,
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
  initRouteRequest(type : string, lDestOrder:string,accessible: boolean):Promise<any>;
  setRouteOrigin(VMERouteRequest : any, originID : string): void;
  addRouteDestination(VMERouteRequest : any, destination :Array<string>): void;
  computeRoute(VMERouteRequest: any, callback: any): void;
  getPoiPosition (poiID: string): Promise<any>;
  createLocationFromLocation (location : any): Promise<any>;
  createPositionFromPosition (position : any) : Promise<any>;
  getPoi(poiID: string) : Promise<any>;
  addPoi(data: string) : Promise<Array<String>>;
  updatesPois(data : string) : Promise<Array<String>>;
  removePois(data :Array<string>) : Promise<boolean>;
  getVersion() : Promise<string>;
};

const { Visioglobe } = NativeModules;

export default Visioglobe as VisioglobeType;

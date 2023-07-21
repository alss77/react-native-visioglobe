import React, { MutableRefObject } from 'react';
import { ViewStyle } from 'react-native';

export type VMEMapViewRef = {
  customFunctionToCall: () => void;
};

export type VMEMapViewProp =
  | ({
      ref: MutableRefObject<VMEMapViewRef | undefined>;
      style: Pick<
        ViewStyle,
        | 'width'
        | 'height'
        | 'backgroundColor'
        | 'margin'
        | 'marginTop'
        | 'marginBottom'
        | 'marginLeft'
        | 'marginRight'
        | 'position'
        | 'left'
        | 'right'
        | 'bottom'
        | 'top'
        | 'minHeight'
        | 'maxHeight'
        | 'minWidth'
        | 'maxWidth'
        | 'opacity'
        | 'elevation'
        | 'zIndex'
      >;
    } & { mapPath: string; mapSecret: number })
  | { mapHash: string };

declare const VisioMapView: React.FC<VMEMapViewProp>;
export default VisioMapView;

/* type VisioglobeType = {
  initRouteRequest(type : string, lDestOrder:string, accessible: boolean) : Promise<VMERouteRequest>; 
  setRoute Origin(routeRequest : VMERouteRequest, originID : string) : void; 
  addRouteDestination(routeRequest : VMERouteRequest, destination :Array<string>) : void; 
  computeRoute(routeRequest: VMERouteRequest, callback: VMERouteCallback) : void;
  getPoiPosition (poiID: string) : Promise<VMEPosition>; 
  createLocationFromLocation (location : VMELocation) : Promise<VMELocation>; 
  createPositionFromLocation (position : VMEPosition) : Promise<VMELocation>; 
  getPoi(poiID: string) : Promise<VMEPoi>; 
  addPoi(data: JSON) : Promise<Array<String>>; 
  updatesPois(data : JSON) : Promise<Array<String>>; 
  removePois(data : Array<string>) : void; 
  getVersion() : Promise<string>;
};

const { Visioglobe } = NativeModules;

export { Visioglobe }; */

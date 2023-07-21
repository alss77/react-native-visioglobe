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
  initRouteRequest(
    type: string,
    lDestOrder: string,
    accessible: boolean
  ): Promise<any>;
  setRouteOrigin(VMERouteRequest: any, originID: string): void;
  addRouteDestination(VMERouteRequest: any, destination: Array<string>): void;
  computeRoute(VMERouteRequest: any, callback: any): void;
  getPoiPosition(poiID: string): Promise<any>;
  createLocationFromLocation(location: any): Promise<any>;
  createPositionFromPosition(position: any): Promise<any>;
  getPoi(poiID: string): Promise<any>;
  addPoi(data: string): Promise<Array<String>>;
  updatesPois(data: string): Promise<Array<String>>;
  removePois(data: Array<string>): Promise<boolean>;
  getVersion(): Promise<string>;
};

const { Visioglobe } = NativeModules;

export { Visioglobe }; */

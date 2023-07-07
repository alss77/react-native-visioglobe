import { NativeModules } from 'react-native';

type VisioglobeType = {
  // multiply(a: number, b: number): Promise<number>;
  /* presentVisioglobe(mapHash?: string): Promise<any>;
  placeAllColorSwitchAction(activate: boolean): Promise<any>;
  initController(): Promise<any>;
  loadMapData(): Promise<any>;
  setLifeCycleListener(): Promise<any>;
  unloadMapView(): Promise<any>;
  unloadMapData(): Promise<any>; */
  // listenerMapDataDidLoad(data: any): Promise<any>;
};

const { Visioglobe } = NativeModules;

export default Visioglobe as VisioglobeType;

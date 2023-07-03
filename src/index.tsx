import { NativeModules } from 'react-native';

type VisioglobeType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Visioglobe } = NativeModules;

export default Visioglobe as VisioglobeType;

import React, { forwardRef, useRef, useImperativeHandle } from 'react';
import {
  requireNativeComponent,
  NativeModules,
  findNodeHandle,
} from 'react-native';

const MapView = requireNativeComponent('VisioMapView');

export const VisioMapView = forwardRef((props, ref) => {
  const r = useRef();

  const _customFunctionToCall = () => {
    NativeModules.VisioMapViewManager.customFunctionToCall(
      findNodeHandle(r.current)
    );
  };

  useImperativeHandle(ref, () => ({
    customFunctionToCall: _customFunctionToCall,
  }));

  return (
    <MapView
      ref={r}
      style={{ ...props.style }}
      mapHash={props.mapHash}
      mapPath={props.mapPath}
      mapSecret={props.mapSecret}
    />
  );
});

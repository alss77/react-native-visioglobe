import React, { forwardRef, useRef, useImperativeHandle } from 'react';
import {
  requireNativeComponent,
  NativeModules,
  findNodeHandle,
  Platform,
  UIManager,
} from 'react-native';

const MapView = requireNativeComponent(
  Platform.OS === 'android' ? 'VisioMapViewManager' : 'VisioMapView'
);

const MODULE =
  Platform.OS === 'android' ? 'VisioglobeModule' : 'VisioMapViewManager';

const createFragment = (viewId) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    // we are calling the 'create' command
    UIManager.VisioMapViewManager.Commands.create.toString(),
    [viewId]
  );

export const VisioMapView = forwardRef((props, ref) => {
  const r = useRef();

  const _customFunctionToCall = () => {
    NativeModules[MODULE].customFunctionToCall(findNodeHandle(r.current));
  };

  const _setPois = (data) => {
    NativeModules[MODULE].setPois(findNodeHandle(r.current), data);
  };

  const _resetPoisColor = () => {
    NativeModules[MODULE].resetPoisColor(findNodeHandle(r.current));
  };

  const _setPoisColor = (poiIDs) => {
    NativeModules[MODULE].setPoisColor(findNodeHandle(r.current), poiIDs);
  };

  const _computeRoute = (origin, destination, optimize = false) => {
    NativeModules[MODULE].computeRoute(
      findNodeHandle(r.current),
      origin,
      destination,
      optimize
    );
  };

  const _getPoiPosition = (poiID) => {
    NativeModules[MODULE]._getPoiPosition(findNodeHandle(r.current), poiID);
  };

  useImperativeHandle(ref, () => ({
    customFunctionToCall: _customFunctionToCall,
    setPois: _setPois,
    resetPoisColor: _resetPoisColor,
    setPoisColor: _setPoisColor,
    computeRoute: _computeRoute,
    _getPoiPosition: _getPoiPosition,
  }));

  React.useEffect(() => {
    if (Platform.OS === 'android') {
      const viewId = findNodeHandle(r.current);
      console.debug('======> VIEW ID FROM MODULE:', viewId);
      createFragment(viewId);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

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

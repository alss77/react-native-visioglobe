import React, { forwardRef, useRef, useImperativeHandle } from 'react';
import {
  NativeModules,
  findNodeHandle,
  Platform,
  UIManager,
} from 'react-native';
import MapView, { Commands } from './VisioMapViewNativeComponent';

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
    Commands.setPois(r.current, data);
  };

  const _resetPoisColor = () => {
    Commands.resetPoisColor(r.current);
  };

  const _setPoisColor = (poiIDs) => {
    Commands.setPoisColor(r.current, poiIDs);
  };

  const _computeRoute = (origin, destination) => {
    Commands.computeRoute(r.current, origin, destination);
  };

  const _getPoiPosition = (poiID) => {
    Commands.getPoiPosition(r.current, poiID);
  };

  useImperativeHandle(ref, () => ({
    customFunctionToCall: _customFunctionToCall,
    setPois: _setPois,
    resetPoisColor: _resetPoisColor,
    setPoisColor: _setPoisColor,
    computeRoute: _computeRoute,
    getPoiPosition: _getPoiPosition,
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

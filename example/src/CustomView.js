import React, { useEffect } from 'react';
import { UIManager, findNodeHandle, Platform } from 'react-native';

import { VisioMapView } from 'react-native-visioglobe';

const createFragment = (viewId) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    // we are calling the 'create' command
    UIManager.VisioMapView.Commands.create.toString(),
    [viewId]
  );

export const MapView = (props) => {
  useEffect(() => {
    console.debug('VisioMapView:', VisioMapView);
    const viewId = findNodeHandle(props.reference.current);
    console.debug('======> VIEW ID:', viewId);
    if (Platform.OS === 'android') createFragment(viewId);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <VisioMapView {...props} ref={props.reference} />;
};

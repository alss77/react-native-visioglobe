/* eslint-disable @typescript-eslint/no-unused-vars */
import React from 'react';
// import Visioglobe from 'react-native-visioglobe';

export default function App() {
  /* const openMapView = async () => {
    try {
      await Visioglobe.presentVisioglobe(
        'dev-m2ca47a978ce45fd3f3334b7a1078272ec07655bc'
      );
      await Visioglobe.initController();
      await Visioglobe.loadMapData();
      await Visioglobe.setLifeCycleListener();
      /* await Visioglobe.placeAllColorSwitchAction(true);
      await Visioglobe.placeAllColorSwitchAction(false);
      console.debug('Visioglobe setted up');
    } catch (error) {
      console.error('Error retrieving Map Controller:', error);
    }
  }; */

  /* React.useEffect(() => {
    const mapDataDidLoadCallback = () => {
      // Trigger your desired action here
      console.log('Map data did load');
    };

    // Call the native module method and pass the callback function
    Visioglobe.listenerMapDataDidLoad(mapDataDidLoadCallback);

    // Clean up the callback when the component is unmounted
    return () => {
      Visioglobe.listenerMapDataDidLoad(null);
    };
  }); */

  React.useEffect(() => {
    // Visioglobe.multiply(3, 7).then(setResult);
    // console.debug('Visioglobe:', Visioglobe);
  }, []);

  return <></>;
}

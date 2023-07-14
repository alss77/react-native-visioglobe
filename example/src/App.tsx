/* eslint-disable react-native/no-inline-styles */
/* eslint-disable @typescript-eslint/no-unused-vars */
import React from 'react';
// import { View, StyleSheet, TouchableOpacity, Text } from 'react-native';
// import Visioglobe from 'react-native-visioglobe';

export default function App() {
  /* const [open, setOpen] = useState(false);
  const poiIDs = ['poi1', 'poi2', 'poi3'];
  const colors = ['#FF0000', '#00FF00', '#0000FF'];

  const openMapView = async () => {
    try {
      await Visioglobe.presentVisioglobe();
    } catch (error) {
      console.error('Error openMapView:', error);
    }
  };

  const multiply = async () => {
    console.log('=====> Calling multiply');
    try {
      const result = await Visioglobe.multiply(12, 2);
      console.log(result);
    } catch (error) {
      console.error('Error multiply:', error);
    }
  };

  const initController = async () => {
    await Visioglobe.initController()
      .then((result) => {
        console.log('initController success:', result);
      })
      .catch((error) => {
        console.error('Error initController:', error);
      });
  };

  const setPoisColor = async () => {
    await Visioglobe.setPoisColor(poiIDs, colors)
      .then((result) => {
        console.log('Pois color set successfully:', result);
      })
      .catch((error) => {
        console.error('Error setting pois color:', error);
      });
  };

  const setMapHash = async () => {
    console.log('=====> Calling setMapHash');
    await Visioglobe.setMapHash('dev-m2ca47a978ce45fd3f3334b7a1078272ec07655bc')
      .then(() => {
        console.log('setMapHash success');
      })
      .catch((error) => {
        console.error('Error setMapHash:', error);
      });
  };

  const getMapHash = async () => {
    await Visioglobe.getMapHash()
      .then((result) => {
        console.log('getMapHash success:', result);
      })
      .catch((error) => {
        console.error('Error getMapHash:', error);
      });
  };

  const getMapController = async () => {
    await Visioglobe.getMapController()
      .then((result) => {
        console.log('getMapController success:', result);
      })
      .catch((error) => {
        console.error('Error getMapHash:', error);
      });
  };

  const loadMapData = async () => {
    await Visioglobe.loadMapData()
      .then((result) => {
        console.log('getMapHash success:', result);
      })
      .catch((error) => {
        console.error('Error getMapHash:', error);
      });
  };

  const setLifeCycleListener = async () => {
    await Visioglobe.setLifeCycleListener()
      .then((result) => {
        console.log('getMapHash success:', result);
      })
      .catch((error) => {
        console.error('Error getMapHash:', error);
      });
  };

  const closeMapView = async () => {
    try {
      await Visioglobe.unloadMapData();
      setOpen(false);
    } catch (error) {
      console.error('Error closeMapView:', error);
    }
  };

  /* React.useEffect(() => {
    const mapDataDidLoadCallback = () => {
      // Trigger your desired action here
      console.log('Map data did load');
    };

    // Call the native module method and pass the callback function
    // Visioglobe.listenerMapDataDidLoad(mapDataDidLoadCallback);

    // Clean up the callback when the component is unmounted
    return () => {
      Visioglobe.listenerMapDataDidLoad(null);
    };
  }); */

  React.useEffect(() => {
    // Visioglobe.multiply(3, 7).then(setResult);
    // console.debug('Visioglobe:', Visioglobe);
  }, []);

  return (
    <>
      {/*
      <View style={styles.container}>
        <TouchableOpacity style={styles.button} onPress={() => multiply()}>
          <Text style={styles.text}>Multiply</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => setMapHash()}>
          <Text style={styles.text}>Set Map Hash</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => getMapHash()}>
          <Text style={styles.text}>Get Map Hash</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={() => initController()}
        >
          <Text style={styles.text}>Init Controller</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={() => getMapController()}
        >
          <Text style={styles.text}>Get Map Controller</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={() => setLifeCycleListener()}
        >
          <Text style={styles.text}>Set Life Cycle</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => openMapView()}>
          <Text style={styles.text}>Display Map</Text>
        </TouchableOpacity>
        {open ? (
          <TouchableOpacity
            style={styles.closeButton}
            onPress={() => closeMapView()}
          >
            <Text>Close</Text>
          </TouchableOpacity>
        ) : null}
      </View>
        */}
    </>
  );
}

/* const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  button: {
    padding: 20,
    borderRadius: 10,
    marginBottom: 5,
    backgroundColor: 'blue',
  },
  closeButton: {
    position: 'absolute',
    zIndex: 200,
    backgroundColor: 'red',
    top: 100,
    alignSelf: 'center',
  },
  text: {
    color: 'white',
    fontWeight: 'bold',
  },
}); */

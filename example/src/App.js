/* eslint-disable react-native/no-inline-styles */
import React from 'react';
import {
  View,
  StyleSheet,
  TouchableOpacity,
  Text,
  PixelRatio,
  Dimensions,
  Platform,
} from 'react-native';
import VisioMapView from 'react-native-visioglobe';

export default function App() {
  const ref = React.useRef(null);

  const customMethod = () => {
    if (ref.current) {
      ref.current.setPoisColor();
    }
  };

  const setPois = () => {
    // do something
    const greenCatData =
      ' {"catCringe":{"name":"Black cat","icon":"https://upload.wikimedia.org/wikipedia/commons/4/4f/Kitty_emoji.png","description":"Black cat is here","feature":{"image":{"icon":"https://upload.wikimedia.org/wikipedia/commons/4/4f/Kitty_emoji.png","position":[45.74131,4.88216,0.0],"anchorMode":"bottomCenter","color":"","scale":15.0,"altitudeMode":"absolute"}}}} ';
    ref.current.setPois(greenCatData);
  };

  const resetPoisColor = () => {
    // do something
    ref.current.resetPoisColor();
  };

  const setPoisColor = () => {
    // do something
    const data = ['B1-UL0-001', 'B1-UL0-002', 'B1-UL0-003'];
    ref.current.setPoisColor(data);
  };

  const computeRoute = () => {
    // do something
    const origin = 'B2-UL01-ID0002';
    const destination = ['B3-UL01-ID0022', 'B2-UL01-ID0081'];
    ref.current.computeRoute(origin, destination);
  };

  return (
    <>
      <VisioMapView
        style={{
          // converts dpi to px, provide desired height
          height:
            Platform.OS === 'ios'
              ? 400
              : PixelRatio.getPixelSizeForLayoutSize(150),
          // converts dpi to px, provide desired width
          width:
            Platform.OS === 'ios'
              ? Dimensions.get('window').width
              : PixelRatio.getPixelSizeForLayoutSize(
                  Dimensions.get('window').width
                ),
        }}
        // hash="dev-c346e782b88c53bb6c891f439dbcc7e2cde0aaab"
        mapHash="dev-c346e782b88c53bb6c891f439dbcc7e2cde0aaab"
        mapPath="path"
        mapSecret={0}
        ref={ref}
      />
      <View style={styles.container}>
        {/* <TouchableOpacity style={styles.button} onPress={() => customMethod()}>
          <Text style={styles.text}>Custom Method</Text>
        </TouchableOpacity> */}
        <TouchableOpacity style={styles.button} onPress={() => computeRoute()}>
          <Text style={styles.text}>Compute Route</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => setPois()}>
          <Text style={styles.text}>Set Pois</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={() => resetPoisColor()}
        >
          <Text style={styles.text}>Reset Pois Color</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => setPoisColor()}>
          <Text style={styles.text}>Set Pois Color</Text>
        </TouchableOpacity>
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // alignItems: 'center',
    // justifyContent: 'center',
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
});

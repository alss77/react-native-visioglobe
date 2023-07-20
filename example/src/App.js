/* eslint-disable react-native/no-inline-styles */
import React from 'react';
import {
  View,
  StyleSheet,
  TouchableOpacity,
  Text,
  PixelRatio,
  Dimensions,
} from 'react-native';
import { MapView } from './CustomView';
// import { VisioMapView } from 'react-native-visioglobe';

export default function App() {
  const ref = React.useRef(null);

  const customMethod = (message) => {
    // do something
  };

  return (
    <>
      <View style={styles.container}>
        <MapView
          style={{
            // converts dpi to px, provide desired height
            height: PixelRatio.getPixelSizeForLayoutSize(300),
            // converts dpi to px, provide desired width
            width: PixelRatio.getPixelSizeForLayoutSize(
              Dimensions.get('window').width
            ),
          }}
          // hash="dev-c346e782b88c53bb6c891f439dbcc7e2cde0aaab"
          mapHash="dev-c346e782b88c53bb6c891f439dbcc7e2cde0aaab"
          mapPath="path"
          mapSecret="0"
          reference={ref}
        />
        <TouchableOpacity style={styles.button} onPress={() => customMethod()}>
          <Text style={styles.text}>Set Map Hash</Text>
        </TouchableOpacity>
      </View>
    </>
  );
}

const styles = StyleSheet.create({
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
});

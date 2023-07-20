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
  UIManager,
  findNodeHandle,
} from 'react-native';
// import { MapView } from './CustomView';
import VisioMapView from 'react-native-visioglobe';

// import { VisioMapView } from 'react-native-visioglobe';

const createFragment = (viewId) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    // we are calling the 'create' command
    UIManager.VisioMapView.Commands.create.toString(),
    [viewId]
  );

export default function App() {
  const ref = React.useRef(null);

  const customMethod = (message) => {
    console.debug(ref.current);
    ref.current.customFunctionToCall();
    // do something
  };

  React.useEffect(() => {
    console.debug('VisioMapView:', VisioMapView);
    if (Platform.OS === 'android') {
      const viewId = findNodeHandle(ref.current);
      console.debug('======> VIEW ID:', viewId);
      createFragment(viewId);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <View style={styles.container}>
        <VisioMapView
          style={{
            // converts dpi to px, provide desired height
            height:
              Platform.os === 'ios'
                ? 200
                : PixelRatio.getPixelSizeForLayoutSize(300),
            // converts dpi to px, provide desired width
            width:
              Platform.os === 'ios'
                ? 100
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
        <TouchableOpacity style={styles.button} onPress={() => customMethod()}>
          <Text style={styles.text}>Set Map Hash</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => customMethod()}>
          <Text style={styles.text}>Set Map Hash</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => customMethod()}>
          <Text style={styles.text}>Set Map Hash</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => customMethod()}>
          <Text style={styles.text}>Set Map Hash</Text>
        </TouchableOpacity>
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

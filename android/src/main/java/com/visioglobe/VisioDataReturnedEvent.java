package com.visioglobe;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class VisioDataReturnedEvent extends Event<VisioDataReturnedEvent> {
    public static final String EVENT_NAME = "getVersion";
    private WritableMap payload;

    public VisioDataReturnedEvent(@IdRes int viewId, int requestId, @NonNull String versionString) {
      super(viewId);
      payload = Arguments.createMap();
      payload.putInt("requestId", requestId);
      // Put our annotations into the payload.
      payload.putString("result", versionString);
    }


  @Override
    public String getEventName() {
      return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
      rctEventEmitter.receiveEvent(getViewTag(), getEventName(), payload);
    }
}

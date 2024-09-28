package com.minisdk.pubsub.unreal;

import com.minisdk.pubsub.bridge.GameRelay;
import com.minisdk.pubsub.bridge.NativeBridgeCallback;

public class NativeBridge {
    GameRelay gameRelay;
    public NativeBridge(){
        gameRelay = new GameRelay(new NativeBridgeCallback() {
            @Override
            public void onReceive(byte[] data) {

            }

            @Override
            public void onReceiveString(String json) {
                nativeCallback(json);
            }
        });
    }

    public static native void nativeCallback(String json);
    public void send(String json){
        this.gameRelay.send(json);
    }
}

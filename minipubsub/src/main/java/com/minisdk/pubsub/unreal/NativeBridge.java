package com.minisdk.pubsub.unreal;

import com.minisdk.pubsub.bridge.GameRelay;
import com.minisdk.pubsub.bridge.NativeBridgeCallback;

public class NativeBridge {
    GameRelay gameRelay;
    public NativeBridge(){
        gameRelay = new GameRelay(new NativeBridgeCallback() {
            @Override
            public void onReceive(String info, String data) {
                nativeCallback(info, data);
            }
        });
    }

    public static native void nativeCallback(String info, String data);
    public void send(String info, String data){
        this.gameRelay.send(info, data);
    }
}

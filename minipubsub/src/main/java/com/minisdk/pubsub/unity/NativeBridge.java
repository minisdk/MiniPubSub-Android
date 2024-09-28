package com.minisdk.pubsub.unity;

import com.minisdk.pubsub.bridge.GameRelay;
import com.minisdk.pubsub.bridge.NativeBridgeCallback;

public class NativeBridge{
    private GameRelay gameRelay;

    public void initialize(NativeBridgeCallback unityCallback){
        this.gameRelay = new GameRelay(unityCallback);
    }

    public void send(byte[] data){
//        this.gameRelay.send(data);
    }

    public void send(String json){
        this.gameRelay.send(json);
    }
}

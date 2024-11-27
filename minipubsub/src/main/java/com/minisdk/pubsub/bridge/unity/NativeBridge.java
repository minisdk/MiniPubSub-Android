package com.minisdk.pubsub.bridge.unity;

import com.minisdk.pubsub.bridge.GameRelay;
import com.minisdk.pubsub.bridge.NativeBridgeCallback;

public class NativeBridge{
    private GameRelay gameRelay;

    public void initialize(NativeBridgeCallback unityCallback){
        this.gameRelay = new GameRelay(unityCallback);
    }

    public void send(String info, String data){
        this.gameRelay.send(info, data);
    }
}

package com.pj.pubsub.unity;

import com.pj.pubsub.data.NativeBridgeCallback;

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

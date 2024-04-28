package com.pj.pubsub.unity;

public class NativeBridge{
    private GameRelay gameRelay;

    public void initialize(NativeBridgeCallback unityCallback){
        this.gameRelay = new GameRelay(unityCallback);
    }

    public void send(byte[] data){
        this.gameRelay.send(data);
    }
}

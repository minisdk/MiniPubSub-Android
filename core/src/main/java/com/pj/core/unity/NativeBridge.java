package com.pj.core.unity;

public class NativeBridge{
    private Game game;

    public void initialize(NativeBridgeCallback unityCallback){
        this.game = new Game(unityCallback);
    }

    public void send(byte[] data){
        this.game.send(data);
    }
}

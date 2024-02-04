package com.pj.core.unity;

import android.util.Log;

public class NativeBridge{
    private Game game;

    public void initialize(NativeBridgeCallback unityCallback){
        Log.d("NativeBridge: " ,"init!!!");
        this.game = new Game(unityCallback);
        Log.d("NativeBridge: " ,"init end!!!");
    }

    public void send(String data){
        this.game.send(data);
    }
}

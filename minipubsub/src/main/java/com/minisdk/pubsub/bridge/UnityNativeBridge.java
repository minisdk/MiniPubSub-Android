package com.minisdk.pubsub.bridge;

import com.unity3d.player.UnityPlayer;

public class UnityNativeBridge {
    private GameRelay gameRelay;

    public void initialize(NativeBridgeCallback unityCallback){
        this.gameRelay = new GameRelay(unityCallback);
        ContextHolder.INSTANCE.setActivity(UnityPlayer.currentActivity);
    }

    public void send(String info, String data){
        this.gameRelay.send(info, data);
    }
}

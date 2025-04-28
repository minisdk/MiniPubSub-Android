package com.minisdk.pubsub.bridge;

import android.app.Activity;

public class UnityNativeBridge {
    private GameRelay gameRelay;

    public void initialize(Activity activity, NativeBridgeCallback unityCallback){
        ContextManager.INSTANCE.setActivity(activity);
        this.gameRelay = new GameRelay(unityCallback);
    }

    public void send(String info, String data){
        this.gameRelay.send(info, data);
    }

    public String sendSync(String info, String data) { return this.gameRelay.sendSync(info, data); }
}

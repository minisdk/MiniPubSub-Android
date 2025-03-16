package com.minisdk.pubsub.bridge;

import android.app.Activity;

public class UnrealNativeBridge {
    GameRelay gameRelay;
    public UnrealNativeBridge(){
        gameRelay = new GameRelay(new NativeBridgeCallback() {
            @Override
            public void onReceive(String info, String data) {
                nativeCallback(info, data);
            }
        });
    }

    public static void SetActivity(Activity activity) {
        ContextManager.INSTANCE.setActivity(activity);
    }

    public static native void nativeCallback(String info, String data);
    public void send(String info, String data){
        this.gameRelay.send(info, data);
    }
}

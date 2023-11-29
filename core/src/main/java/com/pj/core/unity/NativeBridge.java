package com.pj.core.unity;

import android.util.Log;

public class NativeBridge{

    // private static AndroidBridge instance;

    // public static AndroidBridge Instance(){
    //     if(instance == null){
    //         instance = new AndroidBridge();
    //     }
    //     return instance;
    // }

    private NativeBridgeCallback unityCallback;

    public void initialize(NativeBridgeCallback unityCallback){
        this.unityCallback = unityCallback;

        this.unityCallback.onReceive("android native - init complete");
    }

    public void send(String message){
        Log.d("AndroidBridge", "android - java - message : " + message);
        this.unityCallback.onReceive(message);
    }
}

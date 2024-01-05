package com.pj.core.unity;

import android.util.Log;

import com.pj.core.MessageManager;

public class NativeBridge{
    private UnityReceiver component;

    public void initialize(NativeBridgeCallback unityCallback){
        Log.d("NativeBridge: " ,"init!!!");
        this.component = new UnityReceiver(unityCallback);
        MessageManager.Companion.getMediator().add(component);
        Log.d("NativeBridge: " ,"init end!!!");
    }

    public void send(String data){
        this.component.send(data);
    }
}

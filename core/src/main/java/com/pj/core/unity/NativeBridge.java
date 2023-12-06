package com.pj.core.unity;

import android.util.Log;

import com.pj.core.EventManager;

public class NativeBridge{
    private UnityEventComponent component;

    public void initialize(NativeBridgeCallback unityCallback){
        Log.d("NativeBridge: " ,"init!!!");
        this.component = new UnityEventComponent(unityCallback);
        EventManager.Companion.getMediator().add(component);
        Log.d("NativeBridge: " ,"init end!!!");
    }

    public void send(String data){
        this.component.send(data);
    }
}

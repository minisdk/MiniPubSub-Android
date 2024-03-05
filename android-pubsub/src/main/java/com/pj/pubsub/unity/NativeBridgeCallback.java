package com.pj.pubsub.unity;

public interface NativeBridgeCallback{
    void onReceive(byte[] data);
}
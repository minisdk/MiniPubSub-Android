package com.minisdk.pubsub.data;

public interface NativeBridgeCallback{
    void onReceive(byte[] data);
    void onReceiveString(String json);
}
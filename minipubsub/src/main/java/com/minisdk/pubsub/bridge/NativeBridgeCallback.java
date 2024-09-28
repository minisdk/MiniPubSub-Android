package com.minisdk.pubsub.bridge;

public interface NativeBridgeCallback{
    void onReceive(byte[] data);
    void onReceiveString(String json);
}
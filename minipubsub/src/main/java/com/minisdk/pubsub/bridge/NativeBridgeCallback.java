package com.minisdk.pubsub.bridge;

public interface NativeBridgeCallback{
    void onReceive(String info, String data);
}
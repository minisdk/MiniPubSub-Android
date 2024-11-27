package com.pj.sample;

import com.minisdk.pubsub.module.ModuleHolder;

public class SampleKitLoader {

    public static void load(){
        SampleKit module = new SampleKit();
        ModuleHolder.getInstance().add(module.getName(), module);
    }
}

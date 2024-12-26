package com.pj.sample;

import com.minisdk.pubsub.module.ModuleBase;
import com.minisdk.pubsub.module.ModuleLoader;

public class SampleKitLoader extends ModuleLoader {
    @Override
    protected ModuleBase load() {
        return new SampleKit();
    }
}

package com.minisdk.pubsub.module;

import java.util.HashMap;
import java.util.Map;

// Module initializer.
// unity and unreal MiniPubsub sdk wiil calls it using jni
public class ModuleHolder {
    private static class Holder {
        private static final ModuleHolder INSTANCE = new ModuleHolder();
    }

    public static ModuleHolder getInstance(){
        return Holder.INSTANCE;
    }

    private Map<String, ModuleBase> moduleMap;

    private ModuleHolder(){
        moduleMap = new HashMap<String, ModuleBase>();
    }

    public void add(String name, ModuleBase module){
        moduleMap.put(name, module);
    }
    public void remove(String name){
        moduleMap.remove(name);
    }
}

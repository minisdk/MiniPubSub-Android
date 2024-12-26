package com.minisdk.pubsub.module;

public abstract class ModuleLoader {
    protected abstract ModuleBase load();

    public String loadModule(){
        ModuleBase module = load();
        String moduleName = module.getName();
        ModuleManager.INSTANCE.add(moduleName, module);
        return moduleName;
    }

    public static void unloadModule(String moduleName){
        ModuleManager.INSTANCE.remove(moduleName);
    }
}

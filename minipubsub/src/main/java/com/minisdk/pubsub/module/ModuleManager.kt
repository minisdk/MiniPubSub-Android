package com.minisdk.pubsub.module

internal object ModuleManager {
    val moduleMap : MutableMap<String, ModuleBase> = mutableMapOf()

    fun add(name: String, module: ModuleBase){
        moduleMap[name] = module
    }

    fun remove(name: String){
        moduleMap.remove(name)
    }

}
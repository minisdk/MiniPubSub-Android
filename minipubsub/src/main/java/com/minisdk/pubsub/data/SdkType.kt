package com.minisdk.pubsub.data

import com.minisdk.pubsub.Publisher

enum class SdkType(val defaultID: Int){
    Native      (0),
    Game        (1)
}

class IdCounter{
    private var idSource: Int = SdkType.Native.defaultID
    fun getNext() : Int{
        val id = idSource
        idSource += 2
        return id
    }
}
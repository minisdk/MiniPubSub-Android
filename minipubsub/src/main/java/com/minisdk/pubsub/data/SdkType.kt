package com.minisdk.pubsub.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

enum class SdkType(val value: Int){
    Native      (0),
    Game        (1);

    companion object{
//        fun fromInt(value: Int) = values()[value]
        fun fromInt(value: Int): SdkType = entries.find { it.value == value }!!
    }
}

class SdkTypeAdapter: JsonSerializer<SdkType>, JsonDeserializer<SdkType>{
    override fun serialize(
        src: SdkType?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src!!.value)
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SdkType {
        return SdkType.fromInt(json!!.asInt)
    }
}

class IdCounter{
    private var idSource: Int = SdkType.Native.value
    fun getNext() : Int{
        val id = idSource
        idSource += 2
        return id
    }
}
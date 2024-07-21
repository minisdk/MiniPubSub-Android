package com.pj.pubsub.data

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("Key")
    val key: String,
    @SerializedName("Data")
    val data: Any)

package com.minisdk.pubsub.data

import com.google.gson.annotations.JsonAdapter

data class Topic(
    val key: String,
    @JsonAdapter(SdkTypeAdapter::class)
    val target: SdkType
)

val defaultTopic = Topic("", SdkType.Native)
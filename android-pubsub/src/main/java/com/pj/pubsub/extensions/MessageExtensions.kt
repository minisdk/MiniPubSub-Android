package com.pj.pubsub.extensions

import com.pj.pubsub.proto.NativePubSub
import com.pj.pubsub.proto.message

typealias Message = NativePubSub.Message
fun Message(key: String) : Message {
    return message {
        this.key = key
    }
}

fun Message(key: String, container: NativePubSub.Container) : NativePubSub.Message {
    return message {
        this.key = key
        this.container = container
    }
}
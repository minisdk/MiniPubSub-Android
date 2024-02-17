package com.pj.core.extensions

import com.pj.core.proto.NativeBridge
import com.pj.core.proto.message

typealias Message = NativeBridge.Message
fun Message(key: String) : NativeBridge.Message {
    return message {
        this.key = key
    }
}

fun Message(key: String, container: NativeBridge.Container) : NativeBridge.Message {
    return message {
        this.key = key
        this.container = container
    }
}
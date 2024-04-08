package com.pj.pubsub

import com.pj.pubsub.extensions.Message

interface Channel {

    val message : Message
    fun reply(message: Message)
}
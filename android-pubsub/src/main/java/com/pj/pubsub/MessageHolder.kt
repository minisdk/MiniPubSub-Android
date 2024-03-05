package com.pj.pubsub

import com.pj.pubsub.extensions.Message

interface MessageHolder {

    val message : Message
    fun giveBack(message: Message)
}
package com.pj.core

abstract class PublishingNode : MessageNode {
    override fun notify(message : Message){
        MessageManager.mediator.notify(message, this)
    }
    override fun notify(message: Message, target: MessageNode){
        MessageManager.mediator.notify(message, this, target)
    }
}
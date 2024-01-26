package com.pj.core

internal class MessageMediatorImpl : MessageMediator {

    private val receiverMap : MutableMap<String, MutableList<MessageNode>> by lazy { mutableMapOf() }
    private val receiverSet : MutableSet<MessageNode> by lazy { mutableSetOf() }
    override fun add(receiver: MessageNode  ) {
        val messageTypes = receiver.getReceivingMessageTypes()
        if(messageTypes == null)
        {
            receiverSet.add(receiver);
        }
        else
        {
            for (type in messageTypes){
                val list = receiverMap.getOrPut(type) { mutableListOf() }
                list.add(receiver)
            }
        }
    }

    override fun add(receiver: MessageNode, eventType: String) {
        val list = receiverMap.getOrPut(eventType) { mutableListOf() }
        if(!list.contains(receiver))
            list.add(receiver)
    }


    override fun notify(message: Message, notifier: MessageNode){
        val box = MessagePostman(message, notifier)
        val receivers = receiverMap.getOrDefault(message.type, null)
        receivers?.forEach { receiver -> receiver.onReceive(box) }

        receiverSet.forEach{receiver ->
            if(notifier !== receiver)
                receiver.onReceive(box)
        }
    }

    override fun notify(message: Message, notifier: MessageNode, receiver: MessageNode) {
        val box = MessagePostman(message, notifier)
        receiver.onReceive(box)
    }

    override fun giveBack(message: Message, giveBacked: MessageNode) {
        val box = MessagePostman(message)
        giveBacked.onReceive(box)
    }

}
package com.pj.core

internal class MessageMediatorImpl : MessageMediator {

    private val receiverMap : MutableMap<String, MutableList<MessageReceiver>> by lazy { mutableMapOf() }
    private val receiverList : MutableList<MessageReceiver> by lazy { mutableListOf() }
    private val receiverSet : MutableSet<MessageReceiver> by lazy { mutableSetOf() }
    override fun add(receiver: MessageReceiver) {
        if(receiver is FilterReceiver){ // 이거 FilterReceiver 사용하는거 안좋은거 같음
            val messageTypes = receiver.getReceivingMessages()
            for (type in messageTypes){
                val list = receiverMap.getOrPut(type) { mutableListOf() }
                list.add(receiver)
            }
        }
        receiverSet.add(receiver)
    }

    override fun add(receiver: MessageReceiver, eventType: String) {
        val list = receiverMap.getOrPut(eventType) { mutableListOf() }
        if(!list.contains(receiver))
            list.add(receiver)
    }


    override fun notify(message: Message, notifier: MessageNotifier){
        val box = MessageBox(message, notifier)
        val receivers = receiverMap.getOrDefault(message.type, null)
        receivers?.forEach { receiver -> receiver.onReceive(box) }
    }

    override fun notify(message: Message, notifier: MessageNotifier, receiver: MessageReceiver) {
        val box = MessageBox(message, notifier)
        receiver.onReceive(box)
    }

    override fun giveBack(message: Message, giveBacked: MessageNotifier) {
        val box = MessageBox(message, null)
        val receiver = receiverList?.find {receiver -> receiver === giveBacked }
        receiver?.onReceive(box)
    }

}
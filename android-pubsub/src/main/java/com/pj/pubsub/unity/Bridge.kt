package com.pj.pubsub.unity

import com.pj.pubsub.EnvelopeHolder
import com.pj.pubsub.MessageManager
import com.pj.pubsub.ReceivablePublisher
import com.pj.pubsub.Tag

internal class Bridge : ReceivablePublisher() {

    private var allTag: Tag;
    private var handler: (EnvelopeHolder) -> Unit;

    init {
        allTag = Tag.none
        handler = {_ -> }
        MessageManager.mediator.register(this)
    }

    override fun setTagRule(all: Tag) {
        this.allTag = all
    }

    override fun matchTag(tag: Tag): Boolean {
        return tag.contains(this.allTag)
    }

    override fun onReceive(envelopeHolder: EnvelopeHolder) {
        this.handler.invoke(envelopeHolder)
    }

    fun subscribe(handler: (EnvelopeHolder) -> Unit){
        this.handler = handler
    }

    fun unsubscribe(handler: (EnvelopeHolder) -> Unit){
        this.handler = {_ -> }
    }
}
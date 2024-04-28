package com.pj.pubsub

import com.pj.pubsub.proto.NativePubSub.Envelope

data class EnvelopeHolder(val envelope: Envelope, val tag: Tag)
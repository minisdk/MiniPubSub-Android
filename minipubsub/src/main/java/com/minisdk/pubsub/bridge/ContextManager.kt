package com.minisdk.pubsub.bridge

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ContextManager {
    val applicationContext: Context?
        get() = ContextHolder.INSTANCE.activity.applicationContext
    val activityContext: Activity?
        get() = ContextHolder.INSTANCE.activity
}
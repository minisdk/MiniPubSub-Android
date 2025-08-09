package com.minisdk.pubsub.bridge

import android.app.Activity
import android.content.Context
import java.lang.ref.WeakReference

object ContextManager {

    private lateinit var activityRef: WeakReference<Activity>
    val applicationContext: Context?
        get() = activityRef.get()?.applicationContext
    val activityContext: Activity?
        get() = activityRef.get()

    fun setActivity(activity: Activity){
        activityRef = WeakReference(activity)
    }
}
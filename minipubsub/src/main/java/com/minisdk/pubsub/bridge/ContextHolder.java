package com.minisdk.pubsub.bridge;

import android.app.Activity;

enum ContextHolder {
    INSTANCE;

    private Activity activity;
    void setActivity(Activity activity){
        this.activity = activity;
    }

    public Activity getActivity(){
        return this.activity;
    }
}

package com.way.event.base;

import android.app.Application;

/**
 * Created by pc on 2016/11/6.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //registerActivityLifecycleCallbacks(RemixerCallbacks.getInstance());
    }

}

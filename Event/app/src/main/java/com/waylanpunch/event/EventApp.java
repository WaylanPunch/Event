package com.waylanpunch.event;

import android.app.Application;
import android.content.Context;

import com.waylanpunch.event.utils.FontUtil;

/**
 * Created by pc on 2017/2/17.
 */

public final class EventApp extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        FontUtil.setDefaultFont(this, "MONOSPACE", "fonts/HanYiKaiTi.ttf");
    }

    public static Context getContext() {
        return mContext;
    }
}

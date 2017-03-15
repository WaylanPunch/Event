package com.waylanpunch.event;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.waylanpunch.event.util.FontUtil;

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
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(mContext, EventConstants.LeanCloud_AppID, EventConstants.LeanCloud_AppKey);
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
    }

    public static Context getContext() {
        return mContext;
    }
}

package com.waylanpunch.event.utils;

import android.content.Context;

import com.waylanpunch.event.EventApp;

/**
 * Created by pc on 2017/3/4.
 */

public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

//    /**
//     * 初始化工具类
//     *
//     * @param context 上下文
//     */
//    public static void init(Context context) {
//        Utils.context = context.getApplicationContext();
//    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context == null)
            context = EventApp.getContext();
        return context;
//        throw new NullPointerException("u should init first");
    }
}
package com.way.event.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.way.event.util.FontsOverride;

import java.lang.reflect.Field;

/**
 * Created by pc on 2016/11/6.
 */

public class MyApplication extends Application {
    private final static String TAG = MyApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();

        // change App Font
        //changeAppFont();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/AlexBrush-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/AlexBrush-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/AlexBrush-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/AlexBrush-Regular.ttf");
    }

//    private void changeAppFont() {
//        try {
//            setDefaultFont(this, "DEFAULT", "fonts/AlexBrush-Regular.ttf");
//        } catch (Exception e) {
//            Log.e(TAG, "changeAppFont error", e);
//        }
//    }
//
//    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
//        try {
//            final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
//            replaceFont(staticTypefaceFieldName, regular);
//        } catch (Exception e) {
//            Log.e(TAG, "setDefaultFont error", e);
//        }
//    }
//
//    protected static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
//        try {
//            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
//            staticField.setAccessible(true);
//            staticField.set(null, newTypeface);
//        } catch (NoSuchFieldException e) {
//            Log.e(TAG, "replaceFont error", e);
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            Log.e(TAG, "replaceFont error", e);
//            e.printStackTrace();
//        }
//    }
}

package com.way.event.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.way.event.base.MyApplication;

/**
 * Created by pc on 2017/1/2.
 */

public final class FontsOverride {
    private final static String TAG = MyApplication.class.getName();

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "replaceFont error", e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e(TAG, "replaceFont error", e);
        }
    }

//    /**
//     * Recursively sets a {@link Typeface} to all
//     * {@link TextView}s in a {@link ViewGroup}.
//     */
//    public static final void setAppFont(ViewGroup mContainer, Typeface mFont, boolean reflect) {
//        if (mContainer == null || mFont == null) return;
//
//        final int mCount = mContainer.getChildCount();
//
//        // Loop through all of the children.
//        for (int i = 0; i < mCount; ++i) {
//            final View mChild = mContainer.getChildAt(i);
//            if (mChild instanceof TextView) {
//                // Set the font if it is a TextView.
//                ((TextView) mChild).setTypeface(mFont);
//            } else if (mChild instanceof ViewGroup) {
//                // Recursively attempt another ViewGroup.
//                setAppFont((ViewGroup) mChild, mFont);
//            } else if (reflect) {
//                try {
//                    Method mSetTypeface = mChild.getClass().getMethod("setTypeface", Typeface.class);
//                    mSetTypeface.invoke(mChild, mFont);
//                } catch (Exception e) { /* Do something... */ }
//            }
//        }
//    }
}

package com.waylanpunch.event.util;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by pc on 2017/2/20.
 */

public class BitmapUtil {

    // 定义私有构造方法（防止通过 new SingletonTest()去实例化）
    private BitmapUtil() {
    }

    // 定义一个SingletonTest类型的变量（不初始化，注意这里没有使用final关键字）
    private static BitmapUtil instance;

    // 定义一个静态的方法（调用时再初始化SingletonTest，使用synchronized 避免多线程访问时，可能造成重的复初始化问题）
    public static synchronized BitmapUtil getInstance() {
        if (instance == null)
            instance = new BitmapUtil();
        return instance;
    }

    /**
     * 把View绘制到Bitmap上
     *
     * @param view   需要绘制的View
     * @param width  该View的宽度
     * @param height 该View的高度
     * @return 返回Bitmap对象
     * add by csj 13-11-6
     */
    public Bitmap getViewBitmap(View comBitmap, int width, int height) {
        Bitmap bitmap = null;
        if (comBitmap != null) {
            comBitmap.clearFocus();
            comBitmap.setPressed(false);

            boolean willNotCache = comBitmap.willNotCacheDrawing();
            comBitmap.setWillNotCacheDrawing(false);

            // Reset the drawing cache background color to fully transparent
            // for the duration of this operation
            int color = comBitmap.getDrawingCacheBackgroundColor();
            comBitmap.setDrawingCacheBackgroundColor(0);
            float alpha = comBitmap.getAlpha();
            comBitmap.setAlpha(1.0f);

            if (color != 0) {
                comBitmap.destroyDrawingCache();
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            comBitmap.measure(widthSpec, heightSpec);
            comBitmap.layout(0, 0, width, height);

            comBitmap.buildDrawingCache();
            Bitmap cacheBitmap = comBitmap.getDrawingCache();
            if (cacheBitmap == null) {
                Log.e("view.ProcessImageToBlur", "failed getViewBitmap(" + comBitmap + ")",
                        new RuntimeException());
                return null;
            }
            bitmap = Bitmap.createBitmap(cacheBitmap);
            // Restore the view
            comBitmap.setAlpha(alpha);
            comBitmap.destroyDrawingCache();
            comBitmap.setWillNotCacheDrawing(willNotCache);
            comBitmap.setDrawingCacheBackgroundColor(color);
        }
        return bitmap;
    }

    public void setContentSharedColor(View fromview, final View toview, final int type) {
        Bitmap bitmap = getViewBitmap(fromview, 100, 100);
        // This is the quick and easy integration path.
        // May not be optimal (since you're dipping in and out of threads)
        Palette.from(bitmap).maximumColorCount(16).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // Get the "vibrant" color swatch based on the bitmap
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (vibrant != null) {
                    switch (type) {
                        case 0:
                            // Update the title TextView with the proper text color
                            ((TextView) toview).setTextColor(vibrant.getTitleTextColor());
                            break;
                        case 1:
                        default:
                            // Set the background color of a layout based on the vibrant color
                            toview.setBackgroundColor(vibrant.getRgb());
                            break;
                    }
                }
            }
        });
    }
}

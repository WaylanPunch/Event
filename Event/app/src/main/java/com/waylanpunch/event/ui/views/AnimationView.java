package com.waylanpunch.event.ui.views;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

/**
 * Created by pc on 2017/2/21.
 */

public class AnimationView extends View {
    private final int COLOR_START = Color.parseColor("#C5CAE9");
    private final int COLOR_END = Color.parseColor("#3F51B5");

    public AnimationView(Context context) {
        super(context);

        // Animate background color
        // Note that setting the background color will automatically invalidate the
        // view, so that the animated color, and the bouncing balls, get redisplayed on
        // every frame of the animation.
        ValueAnimator colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", COLOR_START, COLOR_END);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }
}

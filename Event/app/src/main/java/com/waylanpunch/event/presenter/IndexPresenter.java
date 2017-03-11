package com.waylanpunch.event.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;

import com.waylanpunch.event.view.IndexView;

/**
 * Created by pc on 2017/3/11.
 */

public class IndexPresenter extends BasePresenter<IndexView> {

    private final Handler mHideHandler;//= new Handler();
    private final Runnable mHideRunnable;

    public IndexPresenter() {
        mHideHandler = new Handler(Looper.getMainLooper());
        mHideRunnable = new Runnable() {
            @SuppressLint("InlinedApi")
            @Override
            public void run() {
                mView.initSystemBar();
            }
        };
    }

    public void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void onBottonClick() {
        mView.launchActivity();
    }
}

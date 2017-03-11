package com.waylanpunch.event.presenter;

import android.content.Intent;

import com.waylanpunch.event.view.BaseView;

/**
 * Created by pc on 2017/3/11.
 */

public abstract class BasePresenter<T> {

    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}

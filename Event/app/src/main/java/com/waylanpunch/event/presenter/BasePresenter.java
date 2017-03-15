package com.waylanpunch.event.presenter;

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

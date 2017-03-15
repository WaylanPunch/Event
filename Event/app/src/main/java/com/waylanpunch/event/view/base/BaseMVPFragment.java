package com.waylanpunch.event.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.waylanpunch.event.presenter.BasePresenter;

/**
 * Created by pc on 2017/3/11.
 */

public abstract class BaseMVPFragment<V,T extends BasePresenter<V>> extends Fragment {

    public T presenter;

    public BaseMVPFragment(){
        presenter = initPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.attach((V)this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onDetach() {
        presenter.dettach();
        super.onDetach();
    }

    // 实例化presenter
    public abstract T initPresenter();

}
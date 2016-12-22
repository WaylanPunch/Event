package com.way.event.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.way.event.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends LazyLoadFragment {


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void stopLoad() {

    }
}

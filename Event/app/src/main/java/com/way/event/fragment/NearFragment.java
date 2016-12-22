package com.way.event.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.way.event.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearFragment extends LazyLoadFragment {


    public NearFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_near;
    }

    @Override
    public void lazyLoad() {
        FloatingActionButton fab_near_add = (FloatingActionButton) findViewById(R.id.fab_near_add);
        fab_near_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), AddEventActivity.class);
//                startActivity(intent);    //这里用getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void stopLoad() {

    }
}

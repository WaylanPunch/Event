package com.way.event;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.UiSettings;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
    private final static String TAG = FindFragment.class.getName();

    private MapView mMapView;
    private AMap aMap;//定义一个地图对象
    private UiSettings mUiSettings;//定义一个UiSettings对象

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        //初始化地图变量
        if (aMap == null) {
            aMap = mMapView.getMap();
            //aMap.setMapType(AMap.MAP_TYPE_NORMAL);//普通地图模式
            //1
            aMap.setTrafficEnabled(true);//显示实时路况图层

            //2
            mUiSettings = aMap.getUiSettings();//实例化UiSettings类

            //3
            mUiSettings.setZoomControlsEnabled(true);//缩放控件, 可以控制地图的缩放级别，每次点击改变1个级别，此控件默认打开

            //4
            mUiSettings.setCompassEnabled(true);//指南针, 此控件可以指示地图的南北方向，默认的视图状态下不显示

            //5
            aMap.setLocationSource(new LocationSource() {
                @Override
                public void activate(OnLocationChangedListener onLocationChangedListener) {

                }

                @Override
                public void deactivate() {

                }
            });// 设置定位监听
            mUiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
            aMap.setMyLocationEnabled(true);// 可触发定位并显示定位层

            //6
            mUiSettings.setScaleControlsEnabled(true);//显示比例尺控件
        }

    }
}

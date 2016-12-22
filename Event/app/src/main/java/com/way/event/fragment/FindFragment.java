package com.way.event.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.way.event.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends LazyLoadFragment {
    private final static String TAG = FindFragment.class.getName();

    private MapView mMapView;
    private AMap aMap;//定义一个地图对象
    private UiSettings mUiSettings;//定义一个UiSettings对象

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void lazyLoad() {
        Log.i(TAG, "lazyLoad info");
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(mSavedInstanceState);
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

    @Override
    public void stopLoad() {
        Log.i(TAG, "stopLoad info");
    }
}

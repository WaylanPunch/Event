package com.waylanpunch.event.presenter;

import com.waylanpunch.event.view.main.MainView;

/**
 * Created by pc on 2017/3/11.
 */

public class MainPresenter extends BasePresenter<MainView> {


    public MainPresenter() {
    }

    public void onPagerSwipe(int index) {
        mView.setViewPagerShow(index);
    }

    public void onNavigationClick(int index){
        mView.setNavigationShow(index);
    }

    public void onButtonClick(int id){
        mView.startAnotherActivity(id);
    }
}

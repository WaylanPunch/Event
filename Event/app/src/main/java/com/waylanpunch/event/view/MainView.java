package com.waylanpunch.event.view;

/**
 * Created by pc on 2017/3/11.
 */

public interface MainView extends BaseView {
    void setNavigationShow(int index);
    void setViewPagerShow(int index);
    void startAnotherActivity(int id);
    void showMessage(String message);
}

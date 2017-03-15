package com.waylanpunch.event.view.index;

import com.waylanpunch.event.view.base.BaseView;

/**
 * Created by pc on 2017/3/11.
 */

public interface IndexView extends BaseView {
    void initSystemBar();
    void launchActivity();
    void showMessage(String message);
}

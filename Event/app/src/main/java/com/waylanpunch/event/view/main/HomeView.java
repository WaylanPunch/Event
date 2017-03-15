package com.waylanpunch.event.view.main;

import com.waylanpunch.event.model.PostModel;
import com.waylanpunch.event.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/3/11.
 */

public interface HomeView extends BaseView {
    void onSwipeRefreshSuccess(List<PostModel> result);
    void onSwipeRefreshFailed(String message);
    void onSwipeLoadSuccess(List<PostModel> result);
    void onSwipeLoadFailed(String message);
    void showMessage(String message);


}

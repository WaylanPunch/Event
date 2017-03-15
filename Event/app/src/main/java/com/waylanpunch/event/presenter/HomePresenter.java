package com.waylanpunch.event.presenter;

import android.os.Handler;

import com.waylanpunch.event.model.PostModel;
import com.waylanpunch.event.view.main.HomeView;
import com.waylanpunch.event.view.main.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/3/11.
 */

public class HomePresenter extends BasePresenter<HomeView> {


    public HomePresenter() {
    }

    public void swipeRefresh(final int pagesize) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerViewAdapter.addAllData(setList());
//                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//                    }
//                });
                try {
                    mView.onSwipeRefreshSuccess(getPost(pagesize));
                } catch (Exception ex) {
                    mView.onSwipeRefreshFailed(ex.getMessage());
                }
            }
        }, 1000);
    }

    private List<PostModel> getPost(int pagesize) {
        List<PostModel> dataList = new ArrayList<>();
        for (int i = 0; i < pagesize; i++) {
            dataList.add(new PostModel("nickname" + i, "title" + i, "description" + 1));
        }
        return dataList;

    }

    public void swipeLoad(final int pagesize) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerViewAdapter.addAllData(setList());
//                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//                    }
//                });
                try {
                    mView.onSwipeLoadSuccess(getPost(pagesize));
                } catch (Exception ex) {
                    mView.onSwipeLoadFailed(ex.getMessage());
                }
            }
        }, 1000);
    }
}

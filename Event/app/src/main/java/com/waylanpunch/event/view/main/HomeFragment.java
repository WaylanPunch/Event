package com.waylanpunch.event.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waylanpunch.event.R;
import com.waylanpunch.event.adapters.HomeAdapter;
import com.waylanpunch.event.model.PostModel;
import com.waylanpunch.event.presenter.HomePresenter;
import com.waylanpunch.event.util.ToastUtil;
import com.waylanpunch.event.view.base.BaseMVPFragment;
import com.waylanpunch.event.widget.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseMVPFragment<HomeView, HomePresenter> implements HomeView, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private static final String TAG = HomeFragment.class.getName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount = 1;
    private HomeAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    public HomeFragment() {
        super();
        Log.i(TAG, "Required empty public constructor");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
// TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        Log.i(TAG, "newInstance");
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        isViewCreated = true;

        initPullLoadRecyclerView(view);
    }

    private void initPullLoadRecyclerView(View view) {
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置下拉刷新是否可见
        //mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        //mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        //mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorBackground);
        mPullLoadMoreRecyclerView.setLinearLayout();

        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mRecyclerViewAdapter = new HomeAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        //lazyLoad();
        //getData();
    }

//    private void lazyLoad() {
//        Log.i(TAG, "lazyLoad");
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerViewAdapter.addAllData(setList());
//                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//                    }
//                });
//
//            }
//        }, 1000);
//    }


    public void clearData() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }


//    private List<String> setList() {
//        List<String> dataList = new ArrayList<>();
//        int start = 20 * (mCount - 1);
//        for (int i = start; i < 20 * mCount; i++) {
//            dataList.add("Frist" + i);
//        }
//        return dataList;
//
//    }

    @Override
    public void onRefresh() {
        Log.i(TAG, "onRefresh");
//        setRefresh();
//        lazyLoad();
        presenter.swipeRefresh(20);
    }

    @Override
    public void onLoadMore() {
        Log.i(TAG, "onLoadMore");
        //mCount = mCount + 1;
        //lazyLoad();
        presenter.swipeLoad(20);
    }

    private void setRefresh() {
        mRecyclerViewAdapter.clearData();
        mCount = 1;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Bundle bundle) {
        if (mListener != null) {
            mListener.onFragmentInteraction(R.id.navigation_home, bundle);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint");
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            //lazyLoad();
            presenter.swipeRefresh(20);
        } else {
            isUIVisible = false;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
        mListener = null;
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSwipeRefreshSuccess(final List<PostModel> result) {
        mRecyclerViewAdapter.clearData();
        mCount = 1;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdapter.addAllData(result);
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    public void onSwipeRefreshFailed(String message) {
        ToastUtil.showLongToast(message);
    }

    @Override
    public void onSwipeLoadSuccess(final List<PostModel> result) {
        mCount = mCount + 1;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdapter.addAllData(result);
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    public void onSwipeLoadFailed(String message) {
        ToastUtil.showLongToast(message);
    }

    @Override
    public void showMessage(String message) {

    }
}

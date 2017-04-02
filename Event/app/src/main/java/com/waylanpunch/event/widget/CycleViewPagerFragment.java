package com.waylanpunch.event.widget;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.waylanpunch.event.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CycleViewPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CycleViewPagerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Integer> mParam1;
    private ArrayList<String> mParam2;

    private LinearLayout ll_indicator;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<ImageView> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;

    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    public CycleViewPagerFragment() {
        // Required empty public constructor
    }

    public static CycleViewPagerFragment newInstance(ArrayList<Integer> param1, ArrayList<String> param2) {
        CycleViewPagerFragment fragment = new CycleViewPagerFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_PARAM1, param1);
        args.putStringArrayList(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getIntegerArrayList(ARG_PARAM1);
            mParam2 = getArguments().getStringArrayList(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cycle_view_pager, container, false);
        ll_indicator = (LinearLayout) root.findViewById(R.id.ll_indicator);
        mViewPaper = (ViewPager) root.findViewById(R.id.vp);
        title = (TextView) root.findViewById(R.id.title);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < mParam1.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(mParam1.get(i));
            images.add(imageView);
        }

        //显示的小点
        dots = new ArrayList<ImageView>();
        for (int i = 0; i < mParam1.size(); i++) {
            ImageView dot = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20, 1);
            lp.setMargins(2, 2, 2, 2);
            dot.setLayoutParams(lp);
            dot.setBackgroundResource(R.drawable.dot_normal);
            ll_indicator.addView(dot);
            dots.add(dot);
        }

        title.setText(mParam2.get(0));

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(mParam2.get(position));
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    /**
     * 自定义Adapter
     */
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }

    private class ViewPageTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % mParam1.size();
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }
    };
}

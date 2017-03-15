package com.waylanpunch.event.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.waylanpunch.event.R;
import com.waylanpunch.event.presenter.MainPresenter;
import com.waylanpunch.event.EventConstants;
import com.waylanpunch.event.adapters.MainFragmentPagerAdapter;
import com.waylanpunch.event.view.newpost.NewPostActivity;
import com.waylanpunch.event.view.profile.ProfileActivity;
import com.waylanpunch.event.view.base.BaseMVPActivity;
import com.waylanpunch.event.widget.BottomNavigationViewHelper;
import com.waylanpunch.event.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMVPActivity<MainView, MainPresenter> implements MainView, OnFragmentInteractionListener {
    private final static String TAG = MainActivity.class.getName();

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.iv_toolbar_add)
    public ImageView toolbarAdd;
    @BindView(R.id.iv_toolbar_search)
    public ImageView toolbarSearch;
    @BindView(R.id.tv_toolbar_title)
    public TextView toolbarTitle;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    @BindView(R.id.navigation)
    public BottomNavigationView navigation;
    public MenuItem menuItem;

    private Context context;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            Log.i(TAG, "onNavigationItemSelected,ItemId=" + id);
            switch (id) {
                case R.id.navigation_home:
                    presenter.onNavigationClick(0);
                    break;
                case R.id.navigation_find:
                    presenter.onNavigationClick(1);
                    break;
                case R.id.navigation_message:
                    presenter.onNavigationClick(2);
                    break;
                case R.id.navigation_more:
                    presenter.onNavigationClick(3);
                    break;
            }
            return true;
        }

    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick");
            presenter.onButtonClick(view.getId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParams();
        initToolbar();
        initSystemBar();
        initBottomNavigationView();
        initViewPager();
    }

    private void initParams() {
        ButterKnife.bind(this);
        context = MainActivity.this;
    }


    public void initToolbar() {
        setSupportActionBar(toolbar);
        toolbarAdd.setOnClickListener(onClickListener);
        toolbarTitle.setOnClickListener(onClickListener);
        toolbarSearch.setOnClickListener(onClickListener);
    }

    public void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

    public void initBottomNavigationView() {
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void initViewPager() {
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected,position=" + position);
                presenter.onPagerSwipe(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(HomeFragment.newInstance(getString(R.string.title_home), "0"));
        adapter.addFragment(FindFragment.newInstance(getString(R.string.title_find), "1"));
        adapter.addFragment(MessageFragment.newInstance(getString(R.string.title_message), "2"));
        adapter.addFragment(MoreFragment.newInstance(getString(R.string.title_more), "3"));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setNavigationShow(int index) {
        viewPager.setCurrentItem(index);
    }

    @Override
    public void setViewPagerShow(int index) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            navigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = navigation.getMenu().getItem(index);
        menuItem.setChecked(true);

        switch (index) {
            case 0:
                toolbarTitle.setText(getString(R.string.title_home));
                break;
            case 1:
                toolbarTitle.setText(getString(R.string.title_find));
                break;
            case 2:
                toolbarTitle.setText(getString(R.string.title_message));
                break;
            case 3:
                toolbarTitle.setText(getString(R.string.title_more));
                break;
        }
    }

    @Override
    public void startAnotherActivity(int id) {
        switch (id) {
            case R.id.iv_toolbar_add:
                Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_toolbar_title:

                break;
            case R.id.iv_toolbar_search:

                break;
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(int tabId, Bundle bundle) {
        if (tabId == R.id.navigation_find) {
            if (bundle != null) {
                String msg = bundle.getString(EventConstants.Action_Message);
                ToastUtil.showShortToast(msg);
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

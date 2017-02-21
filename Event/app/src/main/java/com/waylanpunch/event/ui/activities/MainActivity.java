package com.waylanpunch.event.ui.activities;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.waylanpunch.event.R;
import com.waylanpunch.event.ui.fragments.FindFragment;
import com.waylanpunch.event.ui.fragments.HomeFragment;
import com.waylanpunch.event.ui.fragments.MessageFragment;
import com.waylanpunch.event.ui.fragments.MoreFragment;
import com.waylanpunch.event.ui.fragments.OnFragmentInteractionListener;
import com.waylanpunch.event.utils.ToastUtil;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private final static String TAG = MainActivity.class.getName();

    private Context context;
    private Toolbar toolbar;
    private ImageView toolbarAdd, toolbarSearch;
    private TextView toolbarTitle;
    private BottomNavigationView navigation;
    private Fragment homeFragment;
    private Fragment findFragment;
    private Fragment messageFragment;
    private Fragment moreFragment;
    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 开启Fragment事务
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbarTitle.setText(getString(R.string.title_home));
                    if (homeFragment == null) {
                        homeFragment = HomeFragment.newInstance(getString(R.string.title_home), "0");
                    }
                    // 使用当前Fragment的布局替代id_content的控件
                    transaction.replace(R.id.fl_container, homeFragment);
                    // transaction.addToBackStack();
                    // 事务提交
                    transaction.commit();
                    return true;
                case R.id.navigation_find:
                    toolbarTitle.setText(getString(R.string.title_find));
                    if (findFragment == null) {
                        findFragment = FindFragment.newInstance(getString(R.string.title_find), "1");
                    }
                    transaction.replace(R.id.fl_container, findFragment);
                    // transaction.addToBackStack();
                    // 事务提交
                    transaction.commit();
                    return true;
                case R.id.navigation_message:
                    toolbarTitle.setText(getString(R.string.title_message));
                    if (messageFragment == null) {
                        messageFragment = MessageFragment.newInstance(getString(R.string.title_message), "2");
                    }
                    transaction.replace(R.id.fl_container, messageFragment);
                    // transaction.addToBackStack();
                    // 事务提交
                    transaction.commit();
                    return true;
                case R.id.navigation_more:
                    toolbarTitle.setText(getString(R.string.title_more));
                    if (moreFragment == null) {
                        moreFragment = MoreFragment.newInstance(getString(R.string.title_more), "3");
                    }
                    transaction.replace(R.id.fl_container, moreFragment);
                    // transaction.addToBackStack();
                    // 事务提交
                    transaction.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParams();
        initToolbar();
        initBottomNavigationView();
        initContent();
    }

    private void initParams() {
        context = MainActivity.this;
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        toolbarAdd = (ImageView) toolbar.findViewById(R.id.iv_toolbar_add);
        toolbarSearch = (ImageView) toolbar.findViewById(R.id.iv_toolbar_search);
        toolbarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showIconToast(context, "Add");
            }
        });
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showIconToast(context, "Search");
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

    private void initBottomNavigationView() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initContent() {
        fragmentManager = getSupportFragmentManager();
        homeFragment = HomeFragment.newInstance("Home", "0");
        // 开启Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container, homeFragment);
        transaction.commit();
    }


    @Override
    public void onFragmentInteraction(int tabId, Bundle bundle) {

    }
}

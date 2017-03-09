package com.waylanpunch.event.ui.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.waylanpunch.event.R;
import com.waylanpunch.event.ui.Constants;
import com.waylanpunch.event.ui.adapters.MainFragmentPagerAdapter;
import com.waylanpunch.event.ui.fragments.FindFragment;
import com.waylanpunch.event.ui.fragments.HomeFragment;
import com.waylanpunch.event.ui.fragments.MessageFragment;
import com.waylanpunch.event.ui.fragments.MoreFragment;
import com.waylanpunch.event.ui.fragments.OnFragmentInteractionListener;
import com.waylanpunch.event.ui.views.BottomNavigationViewHelper;
import com.waylanpunch.event.utils.ToastUtil;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private final static String TAG = MainActivity.class.getName();

    private Context context;
    private Toolbar toolbar;
    private ImageView toolbarAdd, toolbarSearch;
    private TextView toolbarTitle;
    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setToolbarTitle(0);
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_find:
                    setToolbarTitle(1);
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_message:
                    setToolbarTitle(2);
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.navigation_more:
                    setToolbarTitle(3);
                    viewPager.setCurrentItem(3);
                    break;
            }
            return true;
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
        toolbarTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return true;
            }
        });
        toolbarAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return true;
            }
        });
        toolbarSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return true;
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
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initContent() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
                setToolbarTitle(position);
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
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(HomeFragment.newInstance(getString(R.string.title_home), "0"));
        adapter.addFragment(FindFragment.newInstance(getString(R.string.title_find), "1"));
        adapter.addFragment(MessageFragment.newInstance(getString(R.string.title_message), "2"));
        adapter.addFragment(MoreFragment.newInstance(getString(R.string.title_more), "3"));
        viewPager.setAdapter(adapter);
    }

    private void setToolbarTitle(int index){
        switch (index){
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(int tabId, Bundle bundle) {
        if (tabId == R.id.navigation_find) {
            if (bundle != null) {
                String msg = bundle.getString(Constants.Action_Message);
                ToastUtil.showShortToast(msg);
            }
        }
    }

}

package com.way.event.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.way.event.R;
import com.way.event.fragment.FindFragment;
import com.way.event.fragment.MineFragment;
import com.way.event.fragment.NearFragment;

import java.util.List;

public class MainActivity extends BaseActivity {
    private final static String TAG = MainActivity.class.getName();

    private Toolbar toolbar;
    private ViewPager homeViewPager;
    private BottomNavigationView bottomNavView;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        init();

        //初始化Toolbar
        initToolbar();

        //初始化Viewpager
        initViewPager();

        //初始化Bottom Navigation
        initBottomNav();

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        homeViewPager = (ViewPager) findViewById(R.id.home_view_pager);
        bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
    }


    private void initToolbar() {

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void initViewPager() {
        homeViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new NearFragment();
                        break;
                    case 1:
                        fragment = new FindFragment();
                        break;
                    case 2:
                        fragment = new MineFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        homeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
                /**
                 * 该方法只有在有新的页面被选中时才会回调
                 *
                 * 如果 preMenuItem 为 null，说明该方法还没有被回调过
                 * 则ViewPager从创建到现在都处于 position 为 0 的页面
                 * 所以当该方法第一次被回调的时候，直接将 position 为 0 的页面的 selected 状态设为 false 即可
                 *
                 * 如果 preMenuItem 不为 null，说明该方法内的
                 * "prevMenuItem = bottomNavView.getMenu().getItem(position);"
                 * 之前至少被调用过一次
                 * 所以当该方法再次被回调的时候，直接将上一个 prevMenuItem 的 selected 状态设为 false 即可
                 * 在做完上一句的事情后，将当前页面设为 prevMenuItem，以备下次调用
                 *
                 * 我注释写这么详细，是不是要给我搭个赏~ (ಥ_ಥ)
                 */
                if (prevMenuItem == null) {
                    bottomNavView.getMenu().getItem(0).setChecked(false);
                } else {
                    prevMenuItem.setChecked(false);
                }

                bottomNavView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initBottomNav() {
        bottomNavView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        homeViewPager.setCurrentItem(item.getOrder());
                        return true;
                    }
                });
    }

//    private void initFragments() {
//        if (nearFragment == null) {
//            nearFragment = new NearFragment();
//
//        }
//        switchFragment(nearFragment);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_message) {
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

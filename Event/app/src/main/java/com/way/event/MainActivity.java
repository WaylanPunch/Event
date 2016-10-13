package com.way.event;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final static String TAG = MainActivity.class.getName();

    private Toolbar toolbar;


    private FindFragment findFragment;
    private NearFragment nearFragment;
    private MineFragment mineFragment;
    private SettingsFragment settingsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragments();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragments() {
        if (findFragment == null) {
            findFragment = new FindFragment();
        }
        switchFragment(findFragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_find) {
            if (findFragment == null) {
                findFragment = new FindFragment();
            }
            switchFragment(findFragment);
        } else if (id == R.id.nav_near) {
            if (nearFragment == null) {
                nearFragment = new NearFragment();
            }
            switchFragment(nearFragment);
        } else if (id == R.id.nav_mine) {
            if (mineFragment == null) {
                mineFragment = new MineFragment();
            }
            switchFragment(mineFragment);
        } else if (id == R.id.nav_settings) {
            if (settingsFragment == null) {
                settingsFragment = new SettingsFragment();
            }
            switchFragment(settingsFragment);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 切换页面的重载，优化了fragment的切换
     *
     * @param to
     */
    public void switchFragment(Fragment to) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments == null || fragments.size() == 0) {
                transaction.add(R.id.content_main, to).commit();
            } else {
                //Fragment from;
                for (Fragment from : fragments) {
                    if (from.isVisible()) {
                        if (!to.isAdded()) {
                            // 隐藏当前的fragment，add下一个到Activity中
                            transaction.hide(from).add(R.id.content_main, to).commit();
                        } else {
                            // 隐藏当前的fragment，显示下一个
                            transaction.hide(from).show(to).commit();
                        }
                    }
                }
            }
            // 让menu回去
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

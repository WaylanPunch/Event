package com.way.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName();

    private FindFragment findFragment;
    private NearFragment nearFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_find:
                        if (findFragment == null) {
                            findFragment = new FindFragment();
                        }
                        switchFragment(findFragment);
                        break;
                    case R.id.nav_near:
                        if (nearFragment == null) {
                            nearFragment = new NearFragment();
                        }
                        switchFragment(nearFragment);
                        break;
                    case R.id.nav_mine:
                        if (mineFragment == null) {
                            mineFragment = new MineFragment();
                        }
                        switchFragment(mineFragment);
                        break;
                }
                return false;
            }
        });

        initFragments();
    }

    private void initFragments() {
        if (findFragment == null) {
            findFragment = new FindFragment();
        }
        switchFragment(findFragment);
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
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                transaction.add(R.id.content_main2, to).commit();
            } else {
                //Fragment from;
                for (Fragment from : fragments) {
                    if (from.isVisible()) {
                        if (!to.isAdded()) {
                            // 隐藏当前的fragment，add下一个到Activity中
                            transaction.hide(from).add(R.id.content_main2, to).commit();
                        } else {
                            // 隐藏当前的fragment，显示下一个
                            transaction.hide(from).show(to).commit();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

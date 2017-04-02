package com.waylanpunch.event.view.newpost;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.waylanpunch.event.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPostActivity extends BaseSwipeActivity {

    private Context context;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.iv_toolbar_back)
    public ImageView toolbarBack;
    @BindView(R.id.iv_toolbar_send)
    public ImageView toolbarSend;
    @BindView(R.id.tv_toolbar_title)
    public TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        initParams();
        initToolbar();
        initSystemBar();
//        initFloatingActionButton();
    }

    private void initParams() {
        ButterKnife.bind(this);
        context = this;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

//    private void initFloatingActionButton() {
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
}

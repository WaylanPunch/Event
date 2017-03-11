package com.waylanpunch.event.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.waylanpunch.event.R;
import com.waylanpunch.event.presenter.IndexPresenter;
import com.waylanpunch.event.view.IndexView;
import com.waylanpunch.event.view.base.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class IndexActivity extends BaseMvpActivity<IndexView,IndexPresenter> implements IndexView {
    private final static String TAG = IndexActivity.class.getName();

    @BindView(R.id.fullscreen_content_controls)
    public View mControlsView;
    @BindView(R.id.fullscreen_content)
    public View mContentView;
    @BindView(R.id.btn_enterapp)
    public Button mEnterApp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mEnterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            presenter.onBottonClick();
            }
        });
        presenter.delayedHide(100);
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public IndexPresenter initPresenter() {
        return new IndexPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initSystemBar() {
        mControlsView.setVisibility(View.VISIBLE);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    @Override
    public void launchActivity() {
        Intent intent = new Intent(IndexActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {

    }
}

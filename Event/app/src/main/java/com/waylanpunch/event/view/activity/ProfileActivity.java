package com.waylanpunch.event.view.activity;

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
import android.widget.TextView;

import com.waylanpunch.event.R;
import com.waylanpunch.event.util.BitmapUtil;

public class ProfileActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initParams();
        initToolbar();
        initSystemBar();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView txtcontent = (TextView) findViewById(R.id.tv_profile_content);

        initContentBackgroundColor();
    }

    private void initParams() {
        context = ProfileActivity.this;
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

    private void initContentBackgroundColor() {
        View from = findViewById(R.id.app_bar);
        TextView to = (TextView) findViewById(R.id.tv_profile_content);
        BitmapUtil.getInstance().setContentSharedColor(from, to, 0);
//        Bitmap bitmap = BitmapUtil.getInstance().getViewBitmap(findViewById(R.id.app_bar), 100, 100);
//        // This is the quick and easy integration path.
//        // May not be optimal (since you're dipping in and out of threads)
//        Palette.from(bitmap).maximumColorCount(16).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                // Get the "vibrant" color swatch based on the bitmap
//                Palette.Swatch vibrant = palette.getVibrantSwatch();
//                if (vibrant != null) {
//                    // Set the background color of a layout based on the vibrant color
//                    findViewById(R.id.nsv_profile_container).setBackgroundColor(vibrant.getRgb());
//                    // Update the title TextView with the proper text color
//                    ((TextView) findViewById(R.id.tv_profile_content)).setTextColor(vibrant.getTitleTextColor());
//                }
//            }
//        });
    }
}

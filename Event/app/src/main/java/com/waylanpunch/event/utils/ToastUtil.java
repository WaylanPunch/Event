package com.waylanpunch.event.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.waylanpunch.event.R;

/**
 * Created by pc on 2017/2/18.
 */

public class ToastUtil {

    private static Toast toast;

    public static void showIconToast(Context context, String message) {
        // 判断toast是否为空，空就新建否则取消当前Toast
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        View v = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_toast_message);
        tv.setText(message);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}

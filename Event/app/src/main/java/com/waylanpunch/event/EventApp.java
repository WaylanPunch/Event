package com.waylanpunch.event;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.tencent.bugly.crashreport.CrashReport;
import com.waylanpunch.event.util.FontUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pc on 2017/2/17.
 */

public final class EventApp extends MultiDexApplication {
    private final static String TAG = EventApp.class.getName();
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        mContext = this;
        FontUtil.setDefaultFont(this, "MONOSPACE", "fonts/HanYiKaiTi.ttf");
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(mContext, EventConstants.LeanCloud_AppID, EventConstants.LeanCloud_AppKey);
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);


        // 获取当前包名
        String packageName = mContext.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        //CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        //在这里设置strategy的属性，在bugly初始化时传入
        strategy.setAppChannel(EventConstants.Bugly_Channel_Beta);  //设置渠道
        strategy.setAppVersion(getVersion());      //App的版本
        strategy.setAppPackageName(packageName);  //App的包名
        //Bugly会在启动10s后联网同步数据。若您有特别需求，可以修改这个时间。
        strategy.setAppReportDelay(10000);   //改为20s
        //自定义标签，用于标明App的某个“场景”。在发生Crash时会显示该Crash所在的“场景”，以最后设置的标签为准，标签id需大于0。例：当用户进入界面A时，打上9527的标签：
        //CrashReport.setUserSceneTag(mContext, 9527); // 上报后的Crash会显示该标签
        //自定义Map参数可以保存发生Crash时的一些自定义的环境信息。在发生Crash时会随着异常信息一起上报并在页面展示。
        //CrashReport.putUserData(mContext, "userkey", "uservalue");
        //在开发测试阶段，可以在初始化Bugly之前通过以下接口把调试设备设置成“开发设备”。
        //CrashReport.setIsDevelopmentDevice(mContext, true);
        //ADT 17增加了BuildConfig特性，可以通过获取BuildConfig类的DEBUG变量来设置：
        //CrashReport.setIsDevelopmentDevice(mContext, BuildConfig.DEBUG);
        /*
        * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        * 输出详细的Bugly SDK的Log；
        * 每一条Crash都会被立即上报；
        * 自定义日志将会在Logcat中输出。
        * 建议在测试阶段建议设置成true，发布时设置为false。
        *
        * */
        CrashReport.initCrashReport(mContext, EventConstants.Bugly_AppID, true, strategy);
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        Log.i(TAG, "getProcessName,pid=" + pid);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            Log.e(TAG, "getProcessName,", throwable);
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                Log.i(TAG, "getProcessName,", exception);
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            String versionName = info.versionName;
            int versionCode = info.versionCode;
            return versionName + "." + versionCode;
        } catch (Exception e) {
            Log.i(TAG, "getProcessName,", e);
            e.printStackTrace();
            return "1.0.1";
        }
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
}

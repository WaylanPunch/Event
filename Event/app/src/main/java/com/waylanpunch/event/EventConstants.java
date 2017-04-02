package com.waylanpunch.event;

/**
 * Created by pc on 2017/2/23.
 */

public class EventConstants {
    // 定义一个私有构造方法
    private EventConstants() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile EventConstants instance;

    //定义一个共有的静态方法，返回该类型实例
    public static EventConstants getIstance() {
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (EventConstants.class) {
                //未初始化，则初始instance变量
                if (instance == null) {
                    instance = new EventConstants();
                }
            }
        }
        return instance;
    }

    public final static String Action_Key = "Action";
    public final static String Action_Value_1 = "Toast";
    public final static String Action_Value_2 = "PopWindow";
    public final static String Action_Value_3 = "BottomWindow";
    public final static String Action_Message = "ActionMessage";
    public final static String LeanCloud_AppID = "y0crQpaNCKUq9WFaSMLByoKo-gzGzoHsz";
    public final static String LeanCloud_AppKey = "ihdOweBmtk9yrDbuPEbJfyBb";
    public final static String LeanCloud_Restful_API = "https://api.leancloud.cn/1.1/";
    public final static String LeanCloud_Restful_API_ContentType = "application/json";
    public final static String Bugly_AppID = "4f9877d15f";
    public final static String Bugly_AppKey = "eacbe843-a65d-47c3-91bf-3862665af4d2";
    public final static String Bugly_Channel_Beta = "Event_Beta";
    public final static String Bugly_Channel_Canary = "Event_Canary";
    public final static String Bugly_Channel_Dev = "Event_Dev";
    public final static String Bugly_Channel_Stable = "Event_Stable";
}

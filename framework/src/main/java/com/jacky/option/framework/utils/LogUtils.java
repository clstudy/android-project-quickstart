package com.jacky.option.framework.utils;

import android.util.Log;

public class LogUtils {
    private static final String mTag = "banker_log/";

    /**
     * 是否允许输出log
     */
    public static boolean mDebuggable = true;


    public static void v(String msg) {
        if (mDebuggable) {
            Log.v(mTag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (mDebuggable) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (mDebuggable) {
            Log.d(mTag, msg);
        }
    }

    public static void i(String msg) {
        if (mDebuggable) {
            Log.i(mTag, msg);
        }
    }

    public static void w(String msg) {
        if (mDebuggable) {
            Log.w(mTag, msg);
        }
    }

    public static void w(Throwable tr) {
        if (mDebuggable) {
            Log.w(mTag, "", tr);
        }
    }

    public static void e(String msg) {
        if (mDebuggable) {
            Log.e(mTag, msg);
        }
    }

    public static void e(Throwable tr) {
        if (mDebuggable) {
            Log.e(mTag, "", tr);
        }
    }


}

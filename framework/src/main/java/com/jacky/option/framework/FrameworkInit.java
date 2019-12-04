package com.jacky.option.framework;

import android.content.Context;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/04
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class FrameworkInit {


    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;
    }
}

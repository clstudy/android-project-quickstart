package com.jacky.option.common;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 2020/02/10
 *     version: 1.0
 *     desc   : 全局{@link android.app.Application}上下文实例。
 * </pre>
 */
public class AppContextHolder {

    public static Context mContext;

    public static Context getContext() {
        return Objects.requireNonNull(mContext, "please do first AppContextHolder.init(context)");
    }

    public static void init(@NonNull Context context) {
        mContext = context;
    }
}

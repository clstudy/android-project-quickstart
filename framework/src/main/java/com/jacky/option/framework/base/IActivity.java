package com.jacky.option.framework.base;

import android.arch.lifecycle.LifecycleOwner;

import com.jacky.option.framework.base.lifecycleprovider.ActivityLifecycleProvider;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : Activity的生命周期处理
 * </pre>
 */
public interface IActivity extends LifecycleOwner, ActivityLifecycleProvider {


}

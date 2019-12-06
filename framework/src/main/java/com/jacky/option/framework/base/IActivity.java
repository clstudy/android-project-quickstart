package com.jacky.option.framework.base;

import android.arch.lifecycle.LifecycleOwner;

import com.jacky.option.framework.base.lifecycleprovider.ActivityLifecycleProvider;

import io.reactivex.subjects.BehaviorSubject;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : Activity的生命周期处理。LifecycleOwner
 * </pre>
 * <p>
 * {@link LifecycleOwner} handle lifecycle changes without implementing any code inside the Activity or the Fragment.
 * {@link ActivityLifecycleProvider} Provide ActivityLifecycle Subject,
 *
 * @see LifecycleOwner
 * @see BehaviorSubject
 */
public interface IActivity extends LifecycleOwner, ActivityLifecycleProvider {
}

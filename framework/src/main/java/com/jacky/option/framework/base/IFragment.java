package com.jacky.option.framework.base;

import android.arch.lifecycle.LifecycleOwner;

import com.jacky.option.framework.base.lifecycleprovider.ActivityLifecycleProvider;
import com.jacky.option.framework.base.lifecycleprovider.FragmentLifecycleProvider;

import io.reactivex.subjects.BehaviorSubject;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 * <p>
 * {@link LifecycleOwner} handle lifecycle changes without implementing any code inside the Activity or the Fragment.
 * {@link FragmentLifecycleProvider} Provide FragmentLifecycle Subject
 *
 * @see LifecycleOwner
 * @see BehaviorSubject
 */
public interface IFragment extends LifecycleOwner, FragmentLifecycleProvider {
}

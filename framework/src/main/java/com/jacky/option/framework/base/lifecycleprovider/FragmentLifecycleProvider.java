package com.jacky.option.framework.base.lifecycleprovider;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : 绑定Fragment的生命周期
 * </pre>
 */
public interface FragmentLifecycleProvider extends LifecycleProvider<FragmentEvent> {
    BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @NonNull
    @Override
    default Subject<FragmentEvent> provideLifecycleSubject() {
        return lifecycleSubject;
    }

    @Override
    default <T> LifecycleTransformer<T> bindUntilEvent(FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(provideLifecycleSubject(), event);
    }

    @Override
    default <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(provideLifecycleSubject());
    }

}

package com.jacky.option.framework.base.lifecycleprovider;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.subjects.Subject;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 *
 * @param <E> he type of the items emitted by the Observable
 */
public interface LifecycleProvider<E> {
    @NonNull
    Subject<E> provideLifecycleSubject();

    <T> LifecycleTransformer<T> bindUntilEvent(E event);

    <T> LifecycleTransformer<T> bindToLifecycle();
}

package com.jacky.option.framework.base.lifecycleprovider;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.internal.Preconditions;

import io.reactivex.annotations.NonNull;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : Observable 绑定页面生命周期。
 * </pre>
 */
public class LifecycleUtils {

    /**
     * Observable bind lifecycle
     *
     * @param lifecycleable lifecycle
     * @param <T>           he type of the items emitted by the Observable
     * @return
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull LifecycleProvider lifecycleable) {
        Preconditions.checkNotNull(lifecycleable, "lifecycleable == null");
        if (lifecycleable instanceof ActivityLifecycleProvider) {
            return lifecycleable.bindToLifecycle();
        } else if (lifecycleable instanceof FragmentLifecycleProvider) {
            return lifecycleable.bindToLifecycle();
        } else {
            throw new IllegalArgumentException("Lifecycleable not match");
        }
    }
}

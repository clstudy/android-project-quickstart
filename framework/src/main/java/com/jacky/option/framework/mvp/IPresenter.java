package com.jacky.option.framework.mvp;

import android.arch.lifecycle.LifecycleObserver;

/**
 * Presenter层与View层是强耦合。
 * Presenter层获取Model层的数据之后，根据数据状态回调给View层，View层拿到数据状态处理页面逻辑。
 *
 * @param <V> View层
 */
public interface IPresenter<V extends IView> extends LifecycleObserver {

    /**
     * View层调用，并传入View层的引用。为了方便回调View层的方法。
     *
     * @param rootView View层
     */
    void attachView(V rootView);

    /**
     * 清除View层的引用。方便gc回收。
     */
    void detachView();


}

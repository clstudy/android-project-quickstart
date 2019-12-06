package com.jacky.option.framework.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : Presenter层，处理数据与视图的关系。
 * </pre>
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V mRootView;

    /**
     * 如果View层实现了LifecycleOwner，则监听其生命周期。
     *
     * @param rootView View层
     */
    @Override
    public void attachView(V rootView) {
        mRootView = rootView;
        if (mRootView != null && mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void detachView() {
        mRootView = null;
    }

    /**
     * 是否持有View层的引用。
     *
     * @return boolean
     */
    public boolean checkViewAttached() {
        return mRootView != null;
    }

    /**
     * 监听View层被销毁。
     *
     * @param owner View层
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }
}

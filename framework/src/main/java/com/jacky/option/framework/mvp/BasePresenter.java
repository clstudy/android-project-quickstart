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
 *     desc   : Presenter层，处理数据与视图的关系
 * </pre>
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V mRootView;

    @Override
    public void attachView(V rootView) {
        mRootView = rootView;
        if (mRootView != null && mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void detachView() {
        mRootView = null;
    }

    public boolean checkViewAttached() {
        return mRootView != null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }
}

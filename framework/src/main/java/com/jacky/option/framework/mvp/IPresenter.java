package com.jacky.option.framework.mvp;

import android.arch.lifecycle.LifecycleObserver;

public interface IPresenter<V extends IView> extends LifecycleObserver {

    void attachView(V rootView);

    void detachView();


}

package com.jacky.option.framework.mvp;

import android.support.annotation.Nullable;

import com.jacky.option.framework.base.lifecycleprovider.LifecycleProvider;
import com.jacky.option.framework.base.lifecycleprovider.LifecycleUtils;
import com.trello.rxlifecycle2.internal.Preconditions;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : Model层，数据的来源。
 * </pre>
 */
public class BaseModel implements IModel {

    protected WeakReference<IView> mRootView;

    public BaseModel(@Nullable IView view) {
        this.mRootView = new WeakReference<>(view);
    }

    /**
     * 简单的线程切换
     *
     * @param observable emitter
     * @param <T>        he type of the items emitted by the Observable
     * @return
     */
    protected <T> Observable<T> transform(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 有线程切换，加载状态
     * {@link BaseModel}的构造方法不能传空值。
     *
     * @param observable emitter
     * @param <T>        he type of the items emitted by the Observable
     * @return
     */
    protected <T> Observable<T> transformWithLoading(Observable<T> observable) {
        Preconditions.checkNotNull(mRootView, "mRootView == null");
        Preconditions.checkNotNull(mRootView.get(), "mRootView == null");
        Preconditions.checkNotNull(observable, "observable == null");
        return observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.get().showLoading())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.get().hideLoading());

    }

    /**
     * 有线程切换，绑定页面生命周期。
     * 调用此方法，必须该类必须实现了{@link com.jacky.option.framework.base.IActivity}
     * 或{@link com.jacky.option.framework.base.IFragment}。
     * 且{@link BaseModel}的构造方法不能传空值。
     *
     * @param observable emitter
     * @param <T>        he type of the items emitted by the Observable
     * @return
     */
    protected <T> Observable<T> transformLifecycle(Observable<T> observable) {
        Preconditions.checkNotNull(mRootView, "mRootView == null");
        Preconditions.checkNotNull(mRootView.get(), "mRootView == null");
        Preconditions.checkNotNull(observable, "observable == null");
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(LifecycleUtils.bindToLifecycle((LifecycleProvider) mRootView.get()));
    }

    /**
     * 有线程切换，加载状态，绑定页面生命周期
     * 调用此方法，必须该类必须实现了{@link com.jacky.option.framework.base.IActivity}
     * 或{@link com.jacky.option.framework.base.IFragment}。
     * 且{@link BaseModel}的构造方法不能传空值。
     *
     * @param observable emitter
     * @param <T>        he type of the items emitted by the Observable
     * @return
     */
    protected <T> Observable<T> transformWithLifecycleAndLoading(Observable<T> observable) {
        Preconditions.checkNotNull(mRootView, "mRootView == null");
        Preconditions.checkNotNull(mRootView.get(), "mRootView == null");
        Preconditions.checkNotNull(observable, "observable == null");
        return observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.get().showLoading())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.get().hideLoading())
                .compose(LifecycleUtils.bindToLifecycle((LifecycleProvider) mRootView.get()));

    }

    @Override
    public void onDestory() {
        mRootView.clear();
        mRootView = null;
    }

}

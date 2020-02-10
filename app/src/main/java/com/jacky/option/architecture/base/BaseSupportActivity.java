package com.jacky.option.architecture.base;

import com.jacky.option.architecture.utils.Loadinghelper;
import com.jacky.option.common.UIUtils;
import com.jacky.option.framework.base.BaseActivity;
import com.jacky.option.framework.mvp.IPresenter;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : View层，视图层,Activity基类
 * </pre>
 */
public abstract class BaseSupportActivity<P extends IPresenter> extends BaseActivity<P> {

    /**
     * loading,可diy
     */
    @Override
    public void showLoading() {
        Loadinghelper.showLoading(this);
    }

    @Override
    public void hideLoading() {
        Loadinghelper.hideLoading();
    }

    /**
     * 可diy
     *
     * @param netErr 网络错误
     */
    @Override
    public void onRequestHttpError(String netErr) {
        UIUtils.showShortToast(netErr);
    }

    /**
     * 网络发生变化，须先执行{@link BaseActivity#setNeedWatchNetworkChange(boolean)}方法
     * 显示方式，可diy
     *
     * @param connected
     */
    @Override
    protected void onNetworkStatedChanged(boolean connected) {
        UIUtils.showShortToast("网络发生了变化：connected=" + connected);
    }
}

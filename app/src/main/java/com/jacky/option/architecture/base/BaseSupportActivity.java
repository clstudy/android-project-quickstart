package com.jacky.option.architecture.base;

import com.jacky.option.architecture.utils.Loadinghelper;
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

    @Override
    public void showLoading() {
        Loadinghelper.showLoading(this);
    }

    @Override
    public void hideLoading() {
        Loadinghelper.hideLoading();
    }

    @Override
    public void onRequestHttpError(String errorInfo) {

    }


}

package com.jacky.option.architecture.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacky.option.common.UIUtils;
import com.jacky.option.framework.base.BaseFragment;
import com.jacky.option.framework.mvp.IPresenter;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/04
 *     version: 1.0
 *     desc   : View层，视图层,Fragment基类
 * </pre>
 */
public abstract class BaseSupportFragment<P extends IPresenter> extends BaseFragment<P> {

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onRequestHttpError(String netErr) {
        UIUtils.showShortToast(netErr);
    }


    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutRes = onSetLayoutRes();
        if (layoutRes != 0) {
            View view = inflater.inflate(layoutRes, container, false);
            onMyViewCreated(container, savedInstanceState);
            return view;
        }
        return null;
    }

    protected abstract  @LayoutRes
    int onSetLayoutRes();

    protected abstract void onMyViewCreated(ViewGroup container, Bundle savedInstanceState);


}

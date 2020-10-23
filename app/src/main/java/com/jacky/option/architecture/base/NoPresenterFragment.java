package com.jacky.option.architecture.base;

import android.os.Bundle;
import android.view.ViewGroup;

import com.jacky.option.framework.mvp.BasePresenter;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/04
 *     version: 1.0
 *     desc   : 不依赖Presenter的Fragment
 * </pre>
 *
 * <p>
 * 成员变量mPresenter 为null,不要使用该变量
 * </p>
 */
public class NoPresenterFragment extends BaseSupportFragment<BasePresenter> {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int onSetLayoutRes() {
        return 0;
    }

    @Override
    protected void onMyViewCreated(ViewGroup container, Bundle savedInstanceState) {

    }
}

package com.jacky.option.architecture.base;

import com.jacky.option.framework.mvp.BasePresenter;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/04
 *     version: 1.0
 *     desc   : 不依赖Presenter的Fragment
 * </pre>
 */
public class NoPresenterFragment extends BaseSupportFragment<BasePresenter> {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}

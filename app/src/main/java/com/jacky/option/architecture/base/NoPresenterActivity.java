package com.jacky.option.architecture.base;

import com.jacky.option.framework.mvp.BasePresenter;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : 不依赖Presenter的Activity。必须要有BasePresenter泛型，不然会报错，因为BaseSupportActivity依赖的BaseActivity实现了IActivity，
 *              而IActivity使用了java8新的语法。导致如果BaseSupportActivity没有确定的泛型会编译不通过
 * </pre>
 */
public class NoPresenterActivity extends BaseSupportActivity<BasePresenter> {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


}

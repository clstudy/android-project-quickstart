package com.jacky.option.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jacky.option.framework.mvp.IPresenter;
import com.jacky.option.framework.mvp.IView;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : BaseActivity
 * </pre>
 * <p>
 * 抽象创建Presenter方法。
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView, IActivity {
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null && createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

    }
}

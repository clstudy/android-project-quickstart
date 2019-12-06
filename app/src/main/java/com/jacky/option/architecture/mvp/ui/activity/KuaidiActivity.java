package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jacky.option.architecture.base.BaseSupportActivity;


import com.jacky.option.architecture.mvp.contract.KuaidiContract;
import com.jacky.option.architecture.mvp.presenter.KuaidiPresenter;


/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 12/06/2019 17:16
 *     version: 1.0
 *     desc   : Kuaidi
 * </pre>
 */
public class KuaidiActivity extends BaseSupportActivity<KuaidiPresenter> implements KuaidiContract.View {

    @Override
    protected KuaidiPresenter createPresenter() {
        return new KuaidiPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

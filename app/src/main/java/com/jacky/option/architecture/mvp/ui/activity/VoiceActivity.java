package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jacky.option.architecture.base.BaseSupportActivity;


import com.jacky.option.architecture.mvp.contract.VoiceContract;
import com.jacky.option.architecture.mvp.presenter.VoicePresenter;


/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 12/03/2019 22:22
 *     version: 1.0
 *     desc   : Voice
 * </pre>
 */
public class VoiceActivity extends BaseSupportActivity<VoicePresenter> implements VoiceContract.View {

    @Override
    protected VoicePresenter createPresenter() {
        return new VoicePresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

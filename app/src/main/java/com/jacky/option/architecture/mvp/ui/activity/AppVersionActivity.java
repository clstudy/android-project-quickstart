package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jacky.option.architecture.base.BaseSupportActivity;


import com.jacky.option.architecture.net.dto.req.ReqVersion;
import com.jacky.option.architecture.net.dto.resp.RespVersion;
import com.jacky.option.architecture.mvp.contract.AppVersionContract;
import com.jacky.option.architecture.mvp.presenter.AppVersionPresenter;
import com.jacky.option.framework.utils.UIUtils;

import java.util.List;


/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 12/03/2019 22:24
 *     version: 1.0
 *     desc   : AppVersion
 * </pre>
 */
public class AppVersionActivity extends BaseSupportActivity<AppVersionPresenter> implements AppVersionContract.View {

    @Override
    protected AppVersionPresenter createPresenter() {
        return new AppVersionPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /*
           {"sign":"1AB9C77B208AC6824A3CDA21981394F6","clientId":"app","version":133,"ip":"127.0.0.1","timestamp":1575340479545}
        * */
        ReqVersion reqVersion = new ReqVersion();
        reqVersion.setSign("1AB9C77B208AC6824A3CDA21981394F6");
        reqVersion.setClientId("app");
        reqVersion.setTimestamp("1575340479545");
        reqVersion.setVersion(133);

        mPresenter.getVersion(reqVersion);
    }

    @Override
    public void onGetVersion(List<RespVersion> result) {
        UIUtils.showShortToast("result:" + result.get(0).getName());
    }
}

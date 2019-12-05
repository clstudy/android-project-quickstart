package com.jacky.option.architecture.mvp.presenter;

import com.jacky.option.architecture.mvp.model.DataModel;
import com.jacky.option.architecture.net.dto.RespData;
import com.jacky.option.architecture.net.dto.req.ReqVersion;
import com.jacky.option.architecture.net.dto.resp.RespVersion;
import com.jacky.option.framework.mvp.BasePresenter;
import com.jacky.option.architecture.mvp.contract.AppVersionContract;
import com.jacky.option.net.callback.SimpleHttpCallBack;


/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 12/03/2019 22:24
 *     version: 1.0
 *     desc   : AppVersion
 * </pre>
 */
public class AppVersionPresenter extends BasePresenter<AppVersionContract.View> implements AppVersionContract.Prestenter {
    @Override
    public void getVersion(ReqVersion reqVersion) {
        DataModel testModel = new DataModel(mRootView);
        testModel.getVersion(reqVersion, new SimpleHttpCallBack<RespData<RespVersion>>() {
            @Override
            public void onBizError(String error) {

            }

            @Override
            public void onBizSuccess(RespData<RespVersion> data) {
                mRootView.onGetVersion(data.getRecords());
            }

            @Override
            public void onNetWorkError(String netErr) {


            }
        });
    }
}


package com.jacky.option.architecture.mvp.presenter;

import com.jacky.option.architecture.mvp.model.DataModel;
import com.jacky.option.architecture.net.dto.req.ReqKuaidi;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;
import com.jacky.option.framework.mvp.BasePresenter;
import com.jacky.option.architecture.mvp.contract.KuaidiContract;
import com.jacky.option.net.callback.SimpleHttpCallBack;

import java.util.List;


/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 12/06/2019 17:16
 *     version: 1.0
 *     desc   : Kuaidi
 * </pre>
 */
public class KuaidiPresenter extends BasePresenter<KuaidiContract.View> implements KuaidiContract.Prestenter {

    @Override
    public void getKuaidiByGet(String type, long postid) {
        DataModel dataModel = new DataModel(mRootView);
        dataModel.getKuaidiByGet("yuantong", 1111111111L, new SimpleHttpCallBack<List<RespKuaidi>>() {
            @Override
            public void onNetWorkError(String netErr) {
                mRootView.onRequestHttpError(netErr);
            }

            @Override
            public void onBizSuccess(List<RespKuaidi> kuaidiList) {
                mRootView.onReciveKuaidi(kuaidiList);
            }

            @Override
            public void onBizError(String error) {
                mRootView.onReciveKuaidiError(error);
            }
        });
    }

    @Override
    public void getKuaidiByPost(ReqKuaidi kuaidi) {
        ReqKuaidi reaKuaidi = new ReqKuaidi();
        reaKuaidi.setPostid(1111111111L);
        reaKuaidi.setType("yuantong");
        DataModel dataModel = new DataModel(mRootView);
        dataModel.getKuaidiByPost(reaKuaidi, new SimpleHttpCallBack<List<RespKuaidi>>() {
            @Override
            public void onNetWorkError(String netErr) {
                mRootView.onRequestHttpError(netErr);
            }

            @Override
            public void onBizSuccess(List<RespKuaidi> kuaidiList) {
                mRootView.onReciveKuaidi(kuaidiList);
            }

            @Override
            public void onBizError(String error) {
                mRootView.onReciveKuaidiError(error);
            }
        });

    }
}


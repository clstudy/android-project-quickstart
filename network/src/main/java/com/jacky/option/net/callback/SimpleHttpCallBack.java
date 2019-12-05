package com.jacky.option.net.callback;

/**
 * Created by jacky on 2018/5/16.
 * banker developer. <br/>
 * <br/>
 */
public class SimpleHttpCallBack<D> extends IAHttpCallBack<D, String> {

    @Override
    public void onBizSuccess(D data) {

    }

    @Override
    public void onBizError(String error) {

    }

    @Override
    public void onNetWorkError(String netErr) {

    }

}

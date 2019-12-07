package com.jacky.option.net.callback;

/**
 * Created by jacky on 2018/3/22.
 * banker developer. <br/>
 * <br/>
 */
public abstract class IAHttpCallBack<DATA, ERROR> implements IHttpCallBack<DATA, ERROR> {

    @Override
    public void onComplete() {

    }

    @Override
    public void onNetWorkError(String netErr) {

    }

}

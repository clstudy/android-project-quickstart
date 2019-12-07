package com.jacky.option.net.callback;

/**
 * Created by jacky on 2018/5/16.
 * banker developer. <br/>
 * <br/>
 * 业务错误泛型为String的回调
 */
public class SimpleHttpCallBack<DATA> extends IAHttpCallBack<DATA, String> {

    @Override
    public void onBizSuccess(DATA data) {

    }

    @Override
    public void onBizError(String error) {

    }



}

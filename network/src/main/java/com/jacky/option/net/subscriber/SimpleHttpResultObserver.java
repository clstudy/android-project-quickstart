package com.jacky.option.net.subscriber;


import com.jacky.option.net.callback.IHttpCallBack;

import retrofit2.HttpException;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */

public class SimpleHttpResultObserver<DTO> extends IAHttpResultObserver<DTO, DTO, String> {
    private static final String TAG = "SimpleHttpResultObserver";

    public SimpleHttpResultObserver(IHttpCallBack<DTO, String> httpCallBack) {
        super(httpCallBack);
    }

    protected void _onHttpSuccess(DTO response) {
        if (mHttpCallBack != null) {
            mHttpCallBack.onBizSuccess(response);
        }
    }

    protected void _onHandlerHttpException(HttpException e) {
    }

}
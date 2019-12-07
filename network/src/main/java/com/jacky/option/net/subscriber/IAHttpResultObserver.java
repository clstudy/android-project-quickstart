package com.jacky.option.net.subscriber;


import com.jacky.option.net.callback.IHttpCallBack;

import retrofit2.HttpException;


/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 *
 * @param <DTO>     原始数据类型
 * @param <PAYLOAD> 处理原始数据，返回处理完的 业务上的数据类型
 * @param <ERROR>   处理原始数据，返回处理完的 错误信息的数据类型
 */
public abstract class IAHttpResultObserver<DTO, PAYLOAD, ERROR> extends IHttpResultObserver<DTO> {//Subscriber
    private static final String TAG = "IAHttpResultObserver";

    /**
     * 处理原始数据回调
     */
    protected IHttpCallBack<PAYLOAD, ERROR> mHttpCallBack;

    public IAHttpResultObserver(IHttpCallBack<PAYLOAD, ERROR> httpCallBack) {
        mHttpCallBack = httpCallBack;
    }

    @Override
    public void onComplete() {
        if (mHttpCallBack != null) {
            mHttpCallBack.onComplete();
        }
    }

    protected void onNetWorkError(String netError) {
        if (mHttpCallBack != null) {
            mHttpCallBack.onNetWorkError(netError);
        }
    }

    protected void _onHandlerHttpException(HttpException e) {
    }

    protected abstract void _onHttpSuccess(DTO response);


}
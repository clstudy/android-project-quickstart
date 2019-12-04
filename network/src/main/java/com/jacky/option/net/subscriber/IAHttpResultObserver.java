package com.jacky.option.net.subscriber;


import com.jacky.option.net.callback.IHttpCallBack;

import retrofit2.HttpException;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */

/**
 * @param <T> 原始数据类型
 * @param <R> 处理完原始数据的类型
 * @param <E> 错误信息的类型
 */
public abstract class IAHttpResultObserver<T, R, E> extends IHttpResultObserver<T> {//Subscriber
    private static final String TAG = "IAHttpResultObserver";

    protected IHttpCallBack<R, E> mHttpCallBack;

    public IAHttpResultObserver(IHttpCallBack<R, E> httpCallBack) {
        mHttpCallBack = httpCallBack;
    }

    @Override
    public void onComplete() {
        if (mHttpCallBack != null) {
            mHttpCallBack.onComplete();
        }
    }

    protected void _onHandlerHttpException(HttpException e) {
    }

    protected abstract void _onHttpSuccess(T response);

    protected abstract void _onHttpError(String httpError);

}
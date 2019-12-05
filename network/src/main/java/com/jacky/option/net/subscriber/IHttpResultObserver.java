package com.jacky.option.net.subscriber;


import com.jacky.option.net.bean.Constants;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 *
 * @param <T> 原始响应数据类型，http response raw data。
 */
public abstract class IHttpResultObserver<T> implements Observer<T> {//Subscriber
    private static final String TAG = "IAHttpResultObserver";

    protected Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T response) {
        _onHttpSuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        onComplete();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            if (code == 500 || code == 404) {
                onNetWorkError(Constants.SERVER_ERROR);
            }
            _onHandlerHttpException((HttpException) e);
        } else if (e instanceof UnknownHostException) {
            onNetWorkError(Constants.CONNECT_FAILD);
        } else if (e instanceof ConnectException) {
            onNetWorkError(Constants.CONNOT_CONNECT_SERVER_PLEASE_CHECKNET);
        } else if (e instanceof SocketTimeoutException) {
            onNetWorkError(Constants.CONNECT_TIMEOUT_PLEASE_CHECKNET);
        } else {
            onNetWorkError(Constants.UNKNOW_ERROR);
        }
    }

    /**
     * 处理原始数据
     *
     * @param response 原始响应数据类型，http response raw data。
     */
    protected abstract void _onHttpSuccess(T response);

    /**
     * 网络错误，如断网，无法找到server主机。
     *
     * @param netError 错误信息
     */
    protected abstract void onNetWorkError(String netError);

    /**
     * server返回的非200的http status code。
     *
     * @param e HttpException
     */
    protected void _onHandlerHttpException(HttpException e) {
    }

}
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
//        LogUtils.d(TAG, "onError: " + e.getMessage());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            if (code == 500 || code == 404) {
                _onHttpError(Constants.SERVER_ERROR);
            }
            _onHandlerHttpException((HttpException) e);
        } else if (e instanceof UnknownHostException) {
            _onHttpError(Constants.CONNECT_FAILD);
        } else if (e instanceof ConnectException) {
            _onHttpError(Constants.CONNOT_CONNECT_SERVER_PLEASE_CHECKNET);
        } else if (e instanceof SocketTimeoutException) {
            _onHttpError(Constants.CONNECT_TIMEOUT_PLEASE_CHECKNET);
        } else {
            _onHttpError(Constants.UNKNOW_ERROR);
        }
    }

    protected abstract void _onHttpSuccess(T response);

    protected abstract void _onHttpError(String httpError);

    protected void _onHandlerHttpException(HttpException e) {
    }

}
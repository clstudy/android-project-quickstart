package com.jacky.option.architecture.net.observer;

import android.text.TextUtils;

import com.jacky.option.architecture.net.dto.Resp;
import com.jacky.option.net.callback.IHttpCallBack;
import com.jacky.option.net.subscriber.IAHttpResultObserver;

/**
 * 网络请求结果监听器
 * 全局统一处理网络请求，须自行替换成自己业务上的逻辑
 *
 * @param <PAYLOAD> 业务上数据，payload
 */
public class MyHttpResultObserver<PAYLOAD> extends IAHttpResultObserver<Resp<PAYLOAD>, PAYLOAD, String> {

    public MyHttpResultObserver(IHttpCallBack<PAYLOAD, String> httpCallBack) {
        super(httpCallBack);
    }

    @Override
    protected void _onHttpSuccess(Resp<PAYLOAD> response) {
        if (response == null) {
            mHttpCallBack.onBizError("暂无数据");
            return;
        }

        if (TextUtils.equals(response.getStatus(), "200")) {
            // 成功
            mHttpCallBack.onBizSuccess(response.getData());
        } else if (TextUtils.equals(response.getStatus(), "400")) {
            // 参数有误
            mHttpCallBack.onBizError(response.getMessage());
        } else {
            mHttpCallBack.onBizError(response.getMessage());
        }

    }

}

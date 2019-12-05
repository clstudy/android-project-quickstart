package com.jacky.option.architecture.net.observer;

import com.jacky.option.architecture.net.dto.Resp;
import com.jacky.option.net.callback.IHttpCallBack;
import com.jacky.option.net.subscriber.IAHttpResultObserver;

public class MyHttpResultObserver<D> extends IAHttpResultObserver<Resp<D>, D, String> {

    public MyHttpResultObserver(IHttpCallBack<D, String> httpCallBack) {
        super(httpCallBack);
    }

    @Override
    protected void _onHttpSuccess(Resp<D> response) {
        if (response == null) {
            mHttpCallBack.onBizError("暂无数据");
            return;
        }
        mHttpCallBack.onBizSuccess(response.getData());
//        if (response.getCode().equals("200")) {
//            mHttpCallBack.onSuccess(response.getData());
//        } else if (response.getCode().equals("301")) {//token失效
//            mHttpCallBack.onError("301");
//        } else if (response.getCode().equals("501")) {
//            mHttpCallBack.onError("501");
//        } else {
//            mHttpCallBack.onError(response.getText());
//        }
    }

}

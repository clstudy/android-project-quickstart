package com.jacky.option.net.callback;


/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 *
 * @param <DATA>  数据类型
 * @param <ERROR> 错误类型
 */
public interface IHttpCallBack<DATA, ERROR> {

    /**
     * 请求完成
     *
     * @param
     */
    void onComplete();

    /**
     * 请求网络得到model
     *
     * @param data 数据模型
     */
    void onBizSuccess(DATA data);

    /**
     * 业务上的错误信息
     *
     * @param error 异常错误信息
     */
    void onBizError(ERROR error);

    /**
     * 网络错误
     *
     * @param error 网络错误
     */
    void onNetWorkError(String error);

}
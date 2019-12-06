package com.jacky.option.framework.mvp;

/**
 * View层,视图层。View层与Presenter层是强耦合。
 */
public interface IView {

    /**
     * View层,监听loading的回调。
     */
    void showLoading();

    /**
     * View层,监听取消loading的回调。
     */
    void hideLoading();

    /**
     * View层,监听http请求错误的回调。
     */
    void onRequestHttpError(String errorInfo);

}

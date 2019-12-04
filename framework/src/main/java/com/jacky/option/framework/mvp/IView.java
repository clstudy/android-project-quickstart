package com.jacky.option.framework.mvp;

public interface IView {

    void showLoading();

    void hideLoading();

    void onRequestHttpError(String errorInfo);

}

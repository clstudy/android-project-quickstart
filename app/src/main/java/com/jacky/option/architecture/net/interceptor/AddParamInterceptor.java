package com.jacky.option.architecture.net.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 自定义应用拦截器。根据需求可自行增删。
 */
public class AddParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request request;

        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        builder.addQueryParameter("verify", "dd");
        builder.addQueryParameter("deviceid", "vv");
        builder.addQueryParameter("token", "tt");
        builder.addQueryParameter("mobile", "mm");
        builder.addQueryParameter("newyeaType", "android");
        HttpUrl modifiedUrl = builder.build();

        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
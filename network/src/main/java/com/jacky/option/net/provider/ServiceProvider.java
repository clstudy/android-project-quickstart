package com.jacky.option.net.provider;


import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.jacky.option.net.HttpManager;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */

public class ServiceProvider {
    private static final String TAG = "ServiceProvider";

    private OkHttpClient mOkHttpClient;
    private String mBaseUrl;

    private ServiceProvider() {
    }

    /**
     * 调用Retrofit创建一个执行http请求的service
     *
     * @param serviceClass HTTP request service calss
     * @param <S>          HTTP request service
     * @return HTTP request service 代理对象
     */
    public <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    /**
     * @param serviceClass HTTP request service calss
     * @param factory      HTTP response data Converter
     * @param <S>          HTTP request service
     * @return HTTP request service 代理对象
     */
    public <S> S createService(Class<S> serviceClass, @Nullable Converter.Factory factory) {
        String url = null;

        if (serviceClass.isAnnotationPresent(BaseUrl.class)) {
            url = serviceClass.getAnnotation(BaseUrl.class).value();
        }

        if (TextUtils.isEmpty(url)) {
            if (TextUtils.isEmpty(mBaseUrl)) {
                throw new RuntimeException("base url can not be null,please add Annotation '@BaseUrl(baseUrl)' to serviceClass, or call 'HttpManager.getInstance().setUrl(baseUrl)' ");
            }
            url = mBaseUrl;
        }

        if (factory == null) {
            factory = GsonConverterFactory.create(new GsonBuilder().create());
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mOkHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public static final class Builder {
        private ServiceProvider INSTANCE;

        public Builder() {
            INSTANCE = new ServiceProvider();
        }

        public Builder setUrl(String baseUrl) {
            if (baseUrl != null)
                INSTANCE.mBaseUrl = baseUrl;
            return this;
        }

        public ServiceProvider build(OkHttpClient client) {
            if (client == null)
                throw new RuntimeException("http client can not be null");
            INSTANCE.mOkHttpClient = client;
            return INSTANCE;
        }
    }


}
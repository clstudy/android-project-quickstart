package com.jacky.option.net.provider;


import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.GsonBuilder;

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

    public <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public <S> S createService(Class<S> serviceClass, @Nullable Converter.Factory factory) {
        if (TextUtils.isEmpty(mBaseUrl)) {
            try {
                Field baseUrlField = serviceClass.getField("BASE_URL");
                mBaseUrl = (String) baseUrlField.get(serviceClass);
            } catch (Exception e) {
//                Log.d(TAG, "createService: BASE_URL not exist");
            }

        }

        if (factory == null) {
            factory = GsonConverterFactory.create(new GsonBuilder().create());
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mBaseUrl = "";
        return retrofit.create(serviceClass);
    }

    public static final class Builder {
        private ServiceProvider INSTANCE;

        public Builder() {
            INSTANCE = new ServiceProvider();
        }

        public Builder addUrl(String baseUrl) {
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
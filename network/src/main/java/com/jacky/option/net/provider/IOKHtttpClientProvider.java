package com.jacky.option.net.provider;

import android.support.annotation.NonNull;

import java.util.LinkedList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/04
 *     version: 1.0
 *     desc   : OkHttpClient提供者
 * </pre>
 */
public interface IOKHtttpClientProvider {

    /**
     * 提供OkHttpClient
     *
     * @return
     */
    @NonNull
    OkHttpClient providerOkHttpClient();

}

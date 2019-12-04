package com.jacky.option.net.provider;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/04
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public interface IOKHtttpClientProvider {

    OkHttpProvider addInterceptors(Interceptor... customInterceptors);

    OkHttpProvider addInterceptorToLast(Interceptor customInterceptors);

    OkHttpProvider addNetworkInterceptor(Interceptor networkInterceptor);

    OkHttpClient providerOkHttpClient();

}

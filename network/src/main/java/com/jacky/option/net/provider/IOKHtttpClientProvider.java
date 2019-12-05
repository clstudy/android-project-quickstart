package com.jacky.option.net.provider;

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
     * 提供应用拦截器集合List,方便添加日志拦截器。可返回null。
     * 若返回null,{@link com.jacky.option.net.HttpManager#init(boolean, IOKHtttpClientProvider)}的第一个参数debug无效；
     * 需自定义日志拦截器。
     *
     * @return
     */
    LinkedList<Interceptor> providerInterceptorList();

    /**
     * 提供OkHttpClient
     *
     * @return
     */
    OkHttpClient providerOkHttpClient();

}

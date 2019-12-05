package com.jacky.option.net.provider;

import com.jacky.option.NetworkInit;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;


/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */
public class OkHttpProvider implements IOKHtttpClientProvider {
    private static final String TAG = "OkHttpProvider";

    private final static long DEFAULT_TIMEOUT_CONN = 10;
    private final static long DEFAULT_TIMEOUT_IO = 10;

    private LinkedList<Interceptor> mInterceptorList;
    private Interceptor mNetworkInterceptor;
    private long mTimeoutConn;
    private long mTimeoutWrite;
    private long mTimeoutRead;

    public OkHttpProvider() {
        mInterceptorList = new LinkedList<>();
    }

    public OkHttpProvider setTimeout(long conn, long write, long read) {
        this.mTimeoutConn = conn;
        this.mTimeoutWrite = write;
        this.mTimeoutRead = read;
        return this;
    }

    public OkHttpProvider addInterceptors(Interceptor... customInterceptors) {
        Collections.addAll(mInterceptorList, customInterceptors);
        return this;
    }

    public OkHttpProvider addNetworkInterceptor(Interceptor networkInterceptor) {
        this.mNetworkInterceptor = networkInterceptor;
        return this;
    }

    @Override
    public LinkedList<Interceptor> providerInterceptorList() {
        return mInterceptorList;
    }

    /**
     * addInterceptor() 添加应用拦截器
     * ● 不需要担心中间过程的响应,如重定向和重试.
     * ● 总是只调用一次,即使HTTP响应是从缓存中获取.
     * ● 观察应用程序的初衷. 不关心OkHttp注入的头信息如: If-None-Match.
     * ● 允许短路而不调用 Chain.proceed(),即中止调用.
     * ● 允许重试,使 Chain.proceed()调用多次.
     * addNetworkInterceptor() 添加网络拦截器
     * ● 能够操作中间过程的响应,如重定向和重试.
     * ● 当网络短路而返回缓存响应时不被调用.
     * ● 只观察在网络上传输的数据.
     * ● 携带请求来访问连接.
     */
    @Override
    public OkHttpClient providerOkHttpClient() {
        //设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        long conn = mTimeoutConn == 0 ? DEFAULT_TIMEOUT_CONN : mTimeoutConn;
        long write = mTimeoutWrite == 0 ? DEFAULT_TIMEOUT_IO : mTimeoutWrite;
        long read = mTimeoutRead == 0 ? DEFAULT_TIMEOUT_IO : mTimeoutRead;
        httpClientBuilder.connectTimeout(conn, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(write, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(read, TimeUnit.SECONDS);

        //设置缓存
        File httpCacheDirectory = new File(NetworkInit.getContext().getCacheDir(), "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 100 * 1024 * 1024));
        // 自定义拦截器
        for (Interceptor interceptor : mInterceptorList) {
            httpClientBuilder.addInterceptor(interceptor);
        }
        //设置缓存拦截器
        if (mNetworkInterceptor != null) {
            httpClientBuilder.addNetworkInterceptor(mNetworkInterceptor);
        }
        return httpClientBuilder.build();
    }


}

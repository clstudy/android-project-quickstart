package com.jacky.option.net;

import com.jacky.option.net.provider.IOKHtttpClientProvider;
import com.jacky.option.net.provider.OkHttpProvider;
import com.jacky.option.net.provider.ServiceProvider;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */

public class HttpManager {

    private static HttpManager instance;
    private ServiceProvider.Builder mBuilder;

    private HttpManager() {
        mBuilder = new ServiceProvider.Builder();
    }

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * 多次设置，只有第一次设置才有效
     *
     * @param debug            可调试
     * @param cacheInterceptor 全局缓存拦截器
     * @return
     */
    public HttpManager init(boolean debug, Interceptor cacheInterceptor) {
        OkHttpClientHolder.INSTANCE.isDebug(debug);
        OkHttpClientHolder.INSTANCE.addNetworkInterceptor(cacheInterceptor);
        return instance;
    }

    public HttpManager addInterceptors(Interceptor... interceptor) {
        OkHttpClientHolder.INSTANCE.addInterceptors(interceptor);
        return instance;
    }

    public HttpManager setUrl(String baseUrl) {
        mBuilder.addUrl(baseUrl);
        return instance;
    }

    public ServiceProvider getServiceProvider() {
        return mBuilder.build(OkHttpClientHolder.INSTANCE.httpclient());
    }

    public ServiceProvider getDiyServiceProvider(OkHttpClient client) {
        return mBuilder.build(client);
    }

    private static class OkHttpClientHolder {
        private static OkHttpClientHolder INSTANCE = new OkHttpClientHolder();

        private IOKHtttpClientProvider mProvider;
        private OkHttpClient mGlobalOkHttpClient;
        private boolean isDebug;

        private OkHttpClientHolder() {
            mProvider = new OkHttpProvider();
        }

        private void isDebug(boolean debug) {
            this.isDebug = debug;
        }

        public void addInterceptors(Interceptor... customInterceptors) {
            mProvider.addInterceptors(customInterceptors);
        }

        public void addNetworkInterceptor(Interceptor networkInterceptor) {
            mProvider.addNetworkInterceptor(networkInterceptor);
        }

        private OkHttpClient httpclient() {
            if (mGlobalOkHttpClient != null)
                return mGlobalOkHttpClient;

            if (isDebug) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY);
                mProvider.addInterceptorToLast(httpLoggingInterceptor);
            }
            mGlobalOkHttpClient = mProvider.providerOkHttpClient();
            return mGlobalOkHttpClient;
        }

    }
}

package com.jacky.option.net;

import android.app.Application;

import com.jacky.option.net.provider.BaseUrl;
import com.jacky.option.net.provider.IOKHtttpClientProvider;
import com.jacky.option.net.provider.OkHttpProvider;
import com.jacky.option.net.provider.ServiceProvider;

import okhttp3.OkHttpClient;

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
     * HttpManager初始化，在{@link HttpManager#getServiceProvider()}被调用之前才有效，
     * 在{@link HttpManager#getServiceProvider()}调用的时候如果init()未被调用，会生成一个默认的OkHttpClient实例。
     * 建议放在{@link Application#onCreate()}里调用。
     * <br/>
     * 该方法不会影响{@link HttpManager#getDiyServiceProvider(OkHttpClient)}中的OkHttpClient实例。
     *
     * @return HttpManager
     */
    public HttpManager init(IOKHtttpClientProvider okHttpClientProvider) {
        if (okHttpClientProvider != null) {
            OkHttpClientHolder.INSTANCE.setProvider(okHttpClientProvider);
        }
        return instance;
    }

    /**
     * 设置url前缀,可多次调用。
     * 此方法不一定会生效，
     * 因为{@link ServiceProvider}会先查找接口service的{@link BaseUrl}注解的值作url前缀，
     * 如果未有{@link BaseUrl}注解或注解没有值才会调用此处设置的值。
     *
     * @param baseUrl url前缀
     * @return
     */
    public HttpManager setUrl(String baseUrl) {
        mBuilder.setUrl(baseUrl);
        return instance;
    }

    /**
     * 使用默认的ServiceProvider，引用的okhttpclient跟{@link HttpManager#init(IOKHtttpClientProvider)}
     * 的设置有关。
     *
     * @return
     */
    public ServiceProvider getServiceProvider() {
        return mBuilder.build(OkHttpClientHolder.INSTANCE.httpclient());
    }

    /**
     * 使用自定义的ServiceProvider，{@link HttpManager#init(IOKHtttpClientProvider)}设置对其无效
     *
     * @param client
     * @return
     */
    public ServiceProvider getDiyServiceProvider(OkHttpClient client) {
        return mBuilder.build(client);
    }

    /**
     * 缓存全局单例OkHttpClient
     */
    private static class OkHttpClientHolder {
        private static OkHttpClientHolder INSTANCE = new OkHttpClientHolder();

        private IOKHtttpClientProvider mProvider;
        private OkHttpClient mGlobalOkHttpClient;

        private OkHttpClientHolder() {
        }

        private void setProvider(IOKHtttpClientProvider provider) {
            mProvider = provider;
        }

        /**
         * 全局单例OkHttpClient
         *
         * @return OkHttpClient
         */
        private OkHttpClient httpclient() {
            if (mGlobalOkHttpClient != null)
                return mGlobalOkHttpClient;

            if (mProvider == null)
                mProvider = new OkHttpProvider();

            mGlobalOkHttpClient = mProvider.providerOkHttpClient();
            mProvider = null;
            return mGlobalOkHttpClient;
        }

    }
}

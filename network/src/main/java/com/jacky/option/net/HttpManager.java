package com.jacky.option.net;

import android.app.Application;

import com.jacky.option.net.provider.IOKHtttpClientProvider;
import com.jacky.option.net.provider.OkHttpProvider;
import com.jacky.option.net.provider.ServiceProvider;

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
     * HttpManager初始化，在{@link HttpManager#getServiceProvider()}被调用之前才有效，
     * 在{@link HttpManager#getServiceProvider()}调用的时候如果init()未被调用，会生成一个默认的OkHttpClient实例。
     * 建议放在{@link Application#onCreate()}里调用。
     * <br/>
     * 该方法不会影响{@link HttpManager#getDiyServiceProvider(OkHttpClient)}中的OkHttpClient实例。
     *
     * @param debug 可调试
     * @return HttpManager
     */
    public HttpManager init(boolean debug, IOKHtttpClientProvider okHttpClientProvider) {
        OkHttpClientHolder.INSTANCE.isDebug(debug);
        if (okHttpClientProvider != null) {
            OkHttpClientHolder.INSTANCE.setProvider(okHttpClientProvider);
        }
        return instance;
    }

    /**
     * 设置url前缀,可多次调用。要在service调用http方法之前有用，service调用之后会把值清掉。
     * 此方法未调用，会先查找service的成员变量名为"BASE_URL"的成员变量作url前缀。
     *
     * @param baseUrl url前缀
     * @return
     */
    public HttpManager setUrl(String baseUrl) {
        mBuilder.addUrl(baseUrl);
        return instance;
    }

    /**
     * 使用默认的ServiceProvider，引用的okhttpclient跟{@link HttpManager#init(boolean, IOKHtttpClientProvider)}
     * 的设置有关。
     *
     * @return
     */
    public ServiceProvider getServiceProvider() {
        return mBuilder.build(OkHttpClientHolder.INSTANCE.httpclient());
    }

    /**
     * 使用自定义的ServiceProvider，{@link HttpManager#init(boolean, IOKHtttpClientProvider)}设置对其无效
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
        private boolean isDebug;

        private OkHttpClientHolder() {
        }

        public void setProvider(IOKHtttpClientProvider provider) {
            mProvider = provider;
        }

        /**
         * 调试打开log输出
         *
         * @param debug 调试
         */
        private void isDebug(boolean debug) {
            this.isDebug = debug;
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

            if (isDebug && mProvider.providerInterceptorList() != null && !mProvider.providerInterceptorList().isEmpty()) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY);
                mProvider.providerInterceptorList().addLast(httpLoggingInterceptor);
            }

            mGlobalOkHttpClient = mProvider.providerOkHttpClient();
            mProvider = null;
            return mGlobalOkHttpClient;
        }

    }
}

package com.jacky.option.architecture;

import android.app.Application;

import com.jacky.option.NetworkInit;
import com.jacky.option.architecture.net.interceptor.AddParamInterceptor;
import com.jacky.option.common.AppContextHolder;
import com.jacky.option.net.HttpManager;
import com.jacky.option.net.interceptor.CacheControlInterceptor;
import com.jacky.option.net.provider.OkHttpProvider;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class MyApp extends Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        // 初始化网络库
        NetworkInit.init(this);
        // 自定义网络请求,不调用默认使用底层创建的okhttpClient
        HttpManager.getInstance()
                .init(new OkHttpProvider()
                        .isDebug(true) // debug模式，打印网络请求
                        .setTimeout(6, 6, 6) // 超时的相关设置
                        .addInterceptors(new AddParamInterceptor()) // 拦截器
                        .addNetworkInterceptor(new CacheControlInterceptor())); // 应用拦截器

        // 初始化工具库
        AppContextHolder.init(this);
    }

    public static Application getApplication() {
        return sApplication;
    }
}

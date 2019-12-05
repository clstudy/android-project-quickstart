package com.jacky.option.architecture;

import android.app.Application;

import com.jacky.option.NetworkInit;
import com.jacky.option.architecture.net.interceptor.AddParamInterceptor;
import com.jacky.option.framework.FrameworkInit;
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
    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.getInstance()
                .init(true, new OkHttpProvider()
                        .setTimeout(6, 6, 6)
                        .addInterceptors(new AddParamInterceptor())
                        .addNetworkInterceptor(new CacheControlInterceptor()));
        NetworkInit.init(this);
        FrameworkInit.init(this);
    }
}

package com.jacky.option.net.interceptor;



import com.jacky.option.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */

public class CacheControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        int cacheAge = 60 * 20;
        String cacheControlValue = "public, max-age=" + cacheAge;

        if (!NetUtils.isOnline()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            cacheAge = 60 * 60 * 24;
            cacheControlValue = "public, only-if-cached, max-stale=" + cacheAge;
        }

        Response response = chain.proceed(request);
        response = response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", cacheControlValue)
                .build();
        return response;
    }
}
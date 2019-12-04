package com.jacky.option.pic;

import android.content.Context;
import android.support.annotation.DrawableRes;

import com.jacky.option.net.HttpManager;

/**
 * Created by jacky on 2018/3/22.
 * banker developer. <br/>
 * <br/>
 */

public class GlideImageLoader {


    private @DrawableRes
    int error;
    private @DrawableRes
    int placeholder;

    private static GlideImageLoader instance;

    private GlideImageLoader() {
    }

    public static GlideImageLoader getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new GlideImageLoader();
                }
            }
        }
        return instance;
    }

    public void initConfig(@DrawableRes int error, @DrawableRes int placeholder) {
        this.error = error;
        this.placeholder = placeholder;
    }

    public GlideRequest getGlideRequest(Context context, String url) {
        return GlideApp.with(context)
                .load(url)
                .error(error)
                .placeholder(placeholder);
    }


}

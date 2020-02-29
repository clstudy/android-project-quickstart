package com.jacky.option.common.lifecycler;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : ActivityLifecycleCallbacks包装类
 * </pre>
 */
public class ActivityLifecycleWrapper implements Application.ActivityLifecycleCallbacks {

    Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

    /**
     * 静态代理activityLifecycleCallbacks
     *
     * @param activityLifecycleCallbacks activity生命周期
     */
    public ActivityLifecycleWrapper(@NonNull Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        mActivityLifecycleCallbacks = activityLifecycleCallbacks;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        boolean isNotAdd = false;
        if (activity.getIntent() != null)
            isNotAdd = activity.getIntent().getBooleanExtra(ActivitysManager.IS_NOT_ADD_ACTIVITY_LIST, false);

        if (!isNotAdd)
            ActivitysManager.getInstance().addActivity(activity);
        mActivityLifecycleCallbacks.onActivityCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        mActivityLifecycleCallbacks.onActivityStarted(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mActivityLifecycleCallbacks.onActivityResumed(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        mActivityLifecycleCallbacks.onActivityPaused(activity);

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mActivityLifecycleCallbacks.onActivityStopped(activity);

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        mActivityLifecycleCallbacks.onActivitySaveInstanceState(activity, outState);

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivitysManager.getInstance().removeActivity(activity);
        mActivityLifecycleCallbacks.onActivityDestroyed(activity);
    }
}

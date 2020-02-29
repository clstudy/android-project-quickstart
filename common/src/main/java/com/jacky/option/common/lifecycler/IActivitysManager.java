package com.jacky.option.common.lifecycler;

import android.app.Activity;
import android.content.Intent;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public interface IActivitysManager {
    Activity getTopActivity();

    Activity findActivity(Class<?> activityClass);

    boolean activityInstanceIsLive(Activity activity);

    void startActivity(Class activityClass);

    void startActivity(Intent intent);

    void addActivity(Activity activity);

    void removeActivity(Activity activity);

    void killActivity(Class<?> activityClass);

    void killAll();

    void killAll(Class<?>... excludeActivityClasses);

    void appExit();
}

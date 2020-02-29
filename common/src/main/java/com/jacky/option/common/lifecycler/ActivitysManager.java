package com.jacky.option.common.lifecycler;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.util.Preconditions;


import com.jacky.option.common.AppContextHolder;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : 管理Activity
 * </pre>
 */
public class ActivitysManager implements IActivitysManager {

    protected final String TAG = this.getClass().getSimpleName();

    //true 为不需要加入到 Activity 容器进行统一管理,默认为 false
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_not_add_activity_list";

    private ActivitysManager() {
    }

    private static ActivitysManager instance;

    public static ActivitysManager getInstance() {
        if (instance == null) {
            synchronized (ActivitysManager.class) {
                if (instance == null) {
                    instance = new ActivitysManager();
                }
            }
        }
        return instance;
    }

    //管理所有存活的 Activity, 容器中的顺序仅仅是 Activity 的创建顺序, 并不能保证和 Activity 任务栈顺序一致
    private List<Activity> mActivityList;
    //当前在前台的 Activity
    private Activity mCurrentActivity;

    @Override
    public Activity getTopActivity() {
        if (mActivityList == null) {
            return null;
        }
        return mActivityList.size() > 0 ? mActivityList.get(mActivityList.size() - 1) : null;
    }

    @Override
    public Activity findActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            return null;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return activity;
            }
        }
        return null;
    }

    @Override
    public boolean activityInstanceIsLive(Activity activity) {
        if (mActivityList == null) {
            return false;
        }
        return mActivityList.contains(activity);
    }

    @Override
    public void startActivity(Class activityClass) {
        startActivity(new Intent(AppContextHolder.getContext(), activityClass));
    }

    @Override
    public void startActivity(Intent intent) {
        if (getTopActivity() == null) {
            //如果没有前台的activity就使用new_task模式启动activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AppContextHolder.getContext().startActivity(intent);
            return;
        }
        getTopActivity().startActivity(intent);
    }

    private List<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }

    @Override
    public void addActivity(Activity activity) {
        synchronized (ActivitysManager.class) {
            List<Activity> activities = getActivityList();
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        }
    }

    @Override
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            return;
        }
        synchronized (ActivitysManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    @Override
    public void killActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            return;
        }
        synchronized (ActivitysManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();

                if (next.getClass().equals(activityClass)) {
                    iterator.remove();
                    next.finish();
                }
            }
        }
    }

    @Override
    public void killAll() {
        synchronized (ActivitysManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                iterator.remove();
                next.finish();
            }
        }
    }

    @Override
    public void killAll(Class<?>... excludeActivityClasses) {
        List<Class<?>> excludeList = Arrays.asList(excludeActivityClasses);
        synchronized (ActivitysManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();

                if (excludeList.contains(next.getClass()))
                    continue;

                iterator.remove();
                next.finish();
            }
        }
    }

    @Override
    public void appExit() {
        try {
            killAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

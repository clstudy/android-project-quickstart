package com.jacky.option.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 2020/02/10
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class DeviceUtils {

    //获取设备号
    public static String getDeviceID(Context context) {
        String deviceId = "0";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceId;
    }

    /**
     * 获取当前app的versionName
     */
    public static String getCurrentVersion() {
        PackageManager pm = UIUtils.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(UIUtils.getContext().getPackageName(), 0);
            String version = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0.0";
        }
    }

    /**
     * 获取当前app的versionCode
     */
    public static int getCurrentVersionCode() {
        PackageManager pm = UIUtils.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(UIUtils.getContext().getPackageName(), 0);
            int version = packageInfo.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取manifest中的metadata标签
     *
     * @param metaDataName metaData属性名
     * @return
     */
    public static String getAppMetaData(String metaDataName) {
        String msg = "";
        try {
            msg = UIUtils.getContext().getPackageManager().getApplicationInfo(
                    UIUtils.getContext().getPackageName(), PackageManager.GET_META_DATA)
                    .metaData.getString(metaDataName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return msg;
    }
}

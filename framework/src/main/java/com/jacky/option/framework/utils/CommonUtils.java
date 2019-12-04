package com.jacky.option.framework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jacky on 2016/11/28.
 * banker develper
 */

public class CommonUtils {
    private static final String TAG = "CommonUtils";

    /**
     * 产生n位随机数
     *
     * @return
     */
    public static long generateRandomNumber(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }

    //获取设备号
    public static String getDeviceID(Context context) {
        String deviceId = "0";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        LogUtils.d("getDeviceID: =" + deviceId);
        return deviceId;
    }


    /**
     * 天数间隔
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 天数间隔
     */
    public static int differentDays(Date beforeDate, Date afterDate) {
        if (afterDate.getTime() - beforeDate.getTime() < 0) {
            throw new IllegalArgumentException("afterDate must higher than beforeDate");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(beforeDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(afterDate);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {//不同年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//闰年
                    timeDistance += 366;
                } else { //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {//同一年
            return day2 - day1;
        }
    }

    /**
     * 是否为URL地址
     */
    public static boolean isUrl(String url) {
        String strPattern = "^((http[s]?)|(ftp))://(?:(\\s+?)(?::(\\s+?))?@)?([a-zA-Z0-9\\-.]+)"
                + "(?::(\\d+))?((?:/[a-zA-Z0-9\\-._?,'+\\&%$=~*!():@\\\\]*)+)?$";
        return isMatch(strPattern, url);
    }

    /**
     * 是否为电话号码
     */
    public static boolean isPhone(String phonenum) {
        String MOBILE = "^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$";
        String CM = "^1(34[0-8]|(3[5-9]|5[017-9]|8[278])\\d)\\d{7}$";
        String CU = "^1(3[0-2]|5[256]|8[56])\\d{8}$";
        String CT = "^1((33|53|8[0-9])[0-9]|349)\\d{7}$";
        String PHS = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$";
        return isMatch(MOBILE, phonenum) || isMatch(CM, phonenum) || isMatch(CU, phonenum)
                || isMatch(CT, phonenum) || isMatch(PHS, phonenum);
    }

    /**
     * 是否为Email
     */
    public static boolean isEmail(String email) {
        String expression = "([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])";
//        String expression = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return isMatch(expression, email);
    }

    /**
     * 是否为 name
     */
    public static boolean isName(String name) {//firstName Does not match regexp: [a-zA-Z\u4E00-\u9FCC ]+
        String expression = "[a-zA-Z\\u4E00-\\u9FCC ]+";
        return isMatch(expression, name);
    }

    /**
     * 字符串正则校验
     */
    public static boolean isMatch(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }


    public static String toMosaic(String name) {
        if (TextUtils.isEmpty(name)) return "";
        StringBuffer stringBuffer = new StringBuffer();
        if (name.length() > 4) {
            for (int i = 0; i < name.length(); i++) {
                if (i < name.length() - 4) {
                    stringBuffer.append("*");
                } else {
                    stringBuffer.append(name.charAt(i));
                }
            }
        } else {
            stringBuffer.append(name);
        }
        return stringBuffer.toString();
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

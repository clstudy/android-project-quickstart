package com.jacky.option;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by jacky on 2017/7/3.
 * banker developer. <br/>
 * <br/>
 */

public class NetUtils {

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) NetworkInit.getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //should check null because in air plan mode it will be null
        return netInfo != null && netInfo.isConnected();
    }

    public static int getNetWorkType(Context context) {
        ConnectivityManager nw = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = nw.getActiveNetworkInfo();
        if (netinfo == null) {
            return 0;
        } else if (!netinfo.isAvailable()) {
            return 0;
        } else {
            return netinfo.getTypeName().toUpperCase(Locale.ENGLISH).equals("WIFI") ? 1 : 2;
        }
    }

}

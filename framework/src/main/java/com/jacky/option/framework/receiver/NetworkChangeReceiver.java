package com.jacky.option.framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 2019/12/07
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public abstract class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        onNetworkStatedChanged(networkInfo != null && networkInfo.isConnected());
    }

    protected abstract void onNetworkStatedChanged(boolean connected);
}
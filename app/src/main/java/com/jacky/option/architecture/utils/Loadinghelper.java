package com.jacky.option.architecture.utils;

import android.app.Activity;

import com.jacky.option.architecture.R;
import com.jacky.option.architecture.mvp.ui.view.LoadingDialog;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/04
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class Loadinghelper {

    private static LoadingDialog mLoadingDialog;

    public static void showLoading(Activity activity) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) return;
        mLoadingDialog = new LoadingDialog(activity, R.style.dialog);
        mLoadingDialog.show();
    }

    public static void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
        mLoadingDialog = null;
    }

}

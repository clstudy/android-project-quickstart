package com.jacky.option.framework.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jacky.option.framework.FrameworkInit;
import com.trello.rxlifecycle2.internal.Preconditions;


/**
 * User Interface utils
 */
public class UIUtils {
    private static final String TAG = "UIUtils";

    /**
     * {@link FrameworkInit#init(Context)}
     *
     * @return
     */
    public static Context getContext() {
        Preconditions.checkNotNull(FrameworkInit.getContext(), "please do first FrameworkInit.init");
        return FrameworkInit.getContext();
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static String getStringByResId(@StringRes int resId) {
        return getContext().getString(resId);
    }

    /**
     * hides keyboard
     *
     * @param context context
     * @param view    view
     */
    public static void hideKeyboard(Context context, View view) {
        try {
            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
//            LogUtils.d(TAG, "hideKeyboard: " + e.getLocalizedMessage());
        }
    }

    /**
     * shows keyboard
     *
     * @param context context
     * @param view    view
     */
    public static void showKeyboard(Context context, View view) {
        try {
            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.showSoftInput(view, 0);
        } catch (Exception e) {
//            LogUtils.d(TAG, "hideKeyboard: " + e.getLocalizedMessage());
        }
    }


    /**
     * 弹土司
     */
    public static void showShortToast(@NonNull final CharSequence content) {
        Toast mToast = null;
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();

    }

    /**
     * 弹土司
     */
    public static void showShortToast(@StringRes final int stringId) {
        Toast mToast = null;
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), stringId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(stringId);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();

    }

    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = getResource().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float fontScale = getResource().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}

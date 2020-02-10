package com.jacky.option.common;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


/**
 * User Interface utils
 */
public class UIUtils {
    private static final String TAG = "UIUtils";

    /**
     * {@link AppContextHolder#getContext()} ()}
     *
     * @return
     */
    public static Context getContext() {
        return AppContextHolder.getContext();
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
        if (TextUtils.isEmpty(content)) return;
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
        if (stringId == 0) return;
        Toast mToast = null;
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), stringId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(stringId);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();

    }

}

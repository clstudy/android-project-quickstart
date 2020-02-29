package com.jacky.option.architecture.mvp.presenter;

import android.text.TextUtils;

import com.jacky.option.framework.mvp.BasePresenter;
import com.jacky.option.architecture.mvp.contract.OtherContract;


/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 12/08/2019 09:16
 *     version: 1.0
 *     desc   : Other
 * </pre>
 */
public class OtherPresenter extends BasePresenter<OtherContract.View> implements OtherContract.Prestenter {

    @Override
    public void getFromOtherPrestenter(String arg) {
        if (TextUtils.equals("0",arg)) {
            mRootView.fromOtherPrestenter("失败的数据");
        } else if (TextUtils.equals("1",arg)) {
            mRootView.fromOtherPrestenter("成功的数据");
        } else {
            mRootView.fromOtherPrestenter("未知的异常");
        }
    }
}


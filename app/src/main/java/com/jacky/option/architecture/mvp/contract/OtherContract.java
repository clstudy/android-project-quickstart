package com.jacky.option.architecture.mvp.contract;

import com.jacky.option.framework.mvp.IPresenter;
import com.jacky.option.framework.mvp.IView;


/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 12/08/2019 09:16
 *     version: 1.0
 *     desc   : Other
 * </pre>
 */
public interface OtherContract {
    interface View extends IView {
        void fromOtherPrestenter(String data);
    }

    interface Prestenter extends IPresenter<View> {
        void getFromOtherPrestenter(String arg);
    }
}

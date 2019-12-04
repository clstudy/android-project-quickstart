package com.jacky.option.architecture.mvp.contract;

import com.jacky.option.architecture.net.dto.req.ReqVersion;
import com.jacky.option.architecture.net.dto.resp.RespVersion;
import com.jacky.option.framework.mvp.IPresenter;
import com.jacky.option.framework.mvp.IView;

import java.util.List;


/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 12/03/2019 22:24
 *     version: 1.0
 *     desc   : AppVersion
 * </pre>
 */
public interface AppVersionContract {
    interface View extends IView {
        void onGetVersion(List<RespVersion> result);
    }

    interface Prestenter extends IPresenter<View> {
        void getVersion(ReqVersion reqVersion);

    }
}

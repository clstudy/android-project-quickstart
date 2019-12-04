package com.jacky.option.architecture.mvp.model;

import com.jacky.option.architecture.base.BaseSupportModel;
import com.jacky.option.architecture.net.dto.RespData;
import com.jacky.option.architecture.net.observer.MyHttpResultObserver;
import com.jacky.option.architecture.net.TestServices;
import com.jacky.option.architecture.net.dto.req.ReqVersion;
import com.jacky.option.architecture.net.dto.resp.RespVersion;
import com.jacky.option.framework.mvp.IView;
import com.jacky.option.net.callback.SimpleHttpCallBack;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : 生产数据层
 * </pre>
 */
public class DataModel extends BaseSupportModel {

    public DataModel(IView view) {
        super(view);
    }

    public void getVersion(ReqVersion r, SimpleHttpCallBack<RespData<RespVersion>> callBack) {
        transformWithLifecycleAndLoading(getService(TestServices.class).getVersion(r))
                .subscribe(new MyHttpResultObserver<>(callBack));
    }


}

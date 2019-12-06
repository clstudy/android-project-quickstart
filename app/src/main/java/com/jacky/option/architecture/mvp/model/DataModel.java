package com.jacky.option.architecture.mvp.model;

import com.jacky.option.architecture.base.BaseSupportModel;
import com.jacky.option.architecture.net.dto.req.ReaKuaidi;
import com.jacky.option.architecture.net.observer.MyHttpResultObserver;
import com.jacky.option.architecture.net.TestServices;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;
import com.jacky.option.framework.mvp.IView;
import com.jacky.option.net.callback.SimpleHttpCallBack;

import retrofit2.http.Query;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : 生产数据层，Model层基类
 * </pre>
 */
public class DataModel extends BaseSupportModel {

    public DataModel(IView view) {
        super(view);
    }

    public void getKuaidiByPost(ReaKuaidi r, SimpleHttpCallBack<RespKuaidi> callBack) {
        transformWithLifecycleAndLoading(getService(TestServices.class).getKuaidiByPost(r))
                .subscribe(new MyHttpResultObserver<>(callBack));
    }

    public void getKuaidiByGet(String type, long postid, SimpleHttpCallBack<RespKuaidi> callBack) {
        transformWithLifecycleAndLoading(getService(TestServices.class).getKuaidiByGet(type, postid))
                .subscribe(new MyHttpResultObserver<>(callBack));
    }


}

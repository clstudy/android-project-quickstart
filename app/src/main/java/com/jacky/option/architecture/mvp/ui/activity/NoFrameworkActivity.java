package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jacky.option.architecture.R;
import com.jacky.option.architecture.mvp.model.DataModel;
import com.jacky.option.architecture.net.dto.req.ReaKuaidi;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;
import com.jacky.option.net.callback.SimpleHttpCallBack;

/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 2019/12/06
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class NoFrameworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReaKuaidi reaKuaidi = new ReaKuaidi();
        reaKuaidi.setPostid(1111111111L);
        reaKuaidi.setType("yuantong");
        DataModel dataModel  = new DataModel(null);
        dataModel.getKuaidiByPost(reaKuaidi,new SimpleHttpCallBack<>());

        dataModel.getKuaidiByGet("yuantong",1111111111L,new SimpleHttpCallBack<>());

    }
}

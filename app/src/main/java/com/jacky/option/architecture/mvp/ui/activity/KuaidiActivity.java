package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jacky.option.architecture.R;
import com.jacky.option.architecture.base.BaseSupportActivity;
import com.jacky.option.architecture.mvp.contract.KuaidiContract;
import com.jacky.option.architecture.mvp.presenter.KuaidiPresenter;
import com.jacky.option.architecture.net.dto.req.ReqKuaidi;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;

import java.util.List;


/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 12/06/2019 17:16
 *     version: 1.0
 *     desc   : Kuaidi，mvp使用的demo，可自行删掉。
 * </pre>
 */
public class KuaidiActivity extends BaseSupportActivity<KuaidiPresenter> implements KuaidiContract.View, View.OnClickListener {

    private TextView mSuccTextView;
    private TextView mErrTextView;

    @Override
    protected KuaidiPresenter createPresenter() {
        return new KuaidiPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidi);
        // 监听网络变化
        setNeedWatchNetworkChange(true);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        mSuccTextView = findViewById(R.id.textView);
        mErrTextView = findViewById(R.id.textView2);

    }

    @Override
    public void onReciveKuaidi(List<RespKuaidi> kuaidiList) {
        StringBuilder builder = new StringBuilder(mSuccTextView.getText());
        for (int i = 0; i < kuaidiList.size(); i++) {
            RespKuaidi respKuaidi = kuaidiList.get(i);
            builder.append(respKuaidi.getTime() + " " + respKuaidi.getContext() + " " + respKuaidi.getFtime() + " \n");
        }
        mSuccTextView.setText(builder.toString());
    }

    @Override
    public void onReciveKuaidiError(String err) {
        mErrTextView.setText(err + " \n");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mPresenter.getKuaidiByGet("yuantong", 1111111111L);
                break;
            case R.id.button2:
                ReqKuaidi reaKuaidi = new ReqKuaidi();
                reaKuaidi.setPostid(1111111111L);
                reaKuaidi.setType("yuantong");
                mPresenter.getKuaidiByPost(reaKuaidi);
                break;
        }
    }
}

package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jacky.option.architecture.R;
import com.jacky.option.architecture.base.BaseSupportActivity;
import com.jacky.option.architecture.mvp.contract.KuaidiContract;
import com.jacky.option.architecture.mvp.contract.OtherContract;
import com.jacky.option.architecture.mvp.presenter.KuaidiPresenter;
import com.jacky.option.architecture.mvp.presenter.OtherPresenter;
import com.jacky.option.architecture.net.dto.req.ReqKuaidi;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;
import com.jacky.option.architecture.utils.UIUtils;

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
public class KuaidiActivity extends BaseSupportActivity<KuaidiPresenter> implements KuaidiContract.View, OtherContract.View, View.OnClickListener {

    private EditText mOtherdataEt;
    private TextView mSuccTextView;
    private TextView mErrTextView;
    private OtherPresenter mOtherPresenter;

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

        findViewById(R.id.other).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        mOtherdataEt = findViewById(R.id.otherdata);
        mSuccTextView = findViewById(R.id.textView);
        mErrTextView = findViewById(R.id.textView2);

        // 创建其他的presenter
        mOtherPresenter = new OtherPresenter();
        mOtherPresenter.attachView(this);
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
            case R.id.other:
                mOtherPresenter.getFromOtherPrestenter(mOtherdataEt.getText().toString());
                break;
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

    @Override
    public void fromOtherPrestenter(String data) {
        UIUtils.showShortToast(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOtherPresenter != null) {
            mOtherPresenter.detachView();
        }
    }
}

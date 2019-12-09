package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
    private EditText mTypeEt;
    private EditText mPostidEt;
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
        mTypeEt = findViewById(R.id.type);
        mPostidEt = findViewById(R.id.postid);
        mSuccTextView = findViewById(R.id.textView);
        mErrTextView = findViewById(R.id.textView2);

        // 创建其他的presenter
        mOtherPresenter = new OtherPresenter();
        mOtherPresenter.attachView(this);
    }

    @Override
    public void onReciveKuaidi(List<RespKuaidi> kuaidiList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < kuaidiList.size(); i++) {
            RespKuaidi respKuaidi = kuaidiList.get(i);
            builder.append(respKuaidi.getFtime() + " " + respKuaidi.getContext() + " \n");
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
                if (TextUtils.isEmpty(mTypeEt.getText().toString().trim())) {
                    UIUtils.showShortToast("快递名称不为空");
                    return;
                }
                if (TextUtils.isEmpty(mPostidEt.getText().toString().trim())) {
                    UIUtils.showShortToast("快递单号不为空");
                    return;
                }
                mPresenter.getKuaidiByGet(mTypeEt.getText().toString().trim(), mPostidEt.getText().toString().trim());
                break;
            case R.id.button2:
                if (TextUtils.isEmpty(mTypeEt.getText().toString().trim())) {
                    UIUtils.showShortToast("快递名称不为空");
                    return;
                }
                if (TextUtils.isEmpty(mPostidEt.getText().toString().trim())) {
                    UIUtils.showShortToast("快递单号不为空");
                    return;
                }
                ReqKuaidi reaKuaidi = new ReqKuaidi();
                reaKuaidi.setPostid(mPostidEt.getText().toString().trim());
                reaKuaidi.setType(mTypeEt.getText().toString().trim());
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

package com.jacky.option.architecture.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jacky.option.architecture.R;
import com.jacky.option.architecture.mvp.model.DataModel;
import com.jacky.option.architecture.net.dto.req.ReqKuaidi;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;
import com.jacky.option.architecture.utils.UIUtils;
import com.jacky.option.net.callback.SimpleHttpCallBack;

import java.util.List;

/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 2019/12/06
 *     version: 1.0
 *     desc   : demo，可自行删掉。不依赖mvp，单独使用网络库
 * </pre>
 */
public class NoFrameworkActivity extends AppCompatActivity implements View.OnClickListener {

    private DataModel mDataModel;
    private TextView mSuccTextView;
    private TextView mErrTextView;
    private EditText mTypeEt;
    private EditText mPostidEt;

    private SimpleHttpCallBack<List<RespKuaidi>> mListSimpleHttpCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_framework);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        mSuccTextView = findViewById(R.id.textView);
        mErrTextView = findViewById(R.id.textView2);
        mTypeEt = findViewById(R.id.type);
        mPostidEt = findViewById(R.id.postid);

        mDataModel = new DataModel(null);
        mListSimpleHttpCallBack = new SimpleHttpCallBack<List<RespKuaidi>>() {
            @Override
            public void onNetWorkError(String netErr) {
                UIUtils.showShortToast(netErr);
            }

            @Override
            public void onBizSuccess(List<RespKuaidi> kuaidiList) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < kuaidiList.size(); i++) {
                    RespKuaidi respKuaidi = kuaidiList.get(i);
                    builder.append(respKuaidi.getTime() + " " + respKuaidi.getContext() + " " + respKuaidi.getFtime() + " \n");
                }
                mSuccTextView.setText(builder.toString());
            }

            @Override
            public void onBizError(String error) {
                mErrTextView.setText(error + " \n");
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (TextUtils.isEmpty(mTypeEt.getText().toString().trim())) {
                    UIUtils.showShortToast("快递名称不为空");
                    return;
                }
                if (TextUtils.isEmpty(mPostidEt.getText().toString().trim())) {
                    UIUtils.showShortToast("快递单号不为空");
                    return;
                }
                mDataModel.getKuaidiNoMVPByGet(mTypeEt.getText().toString().trim(), mPostidEt.getText().toString().trim(), mListSimpleHttpCallBack);
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
                mDataModel.getKuaidiNoMVPByPost(reaKuaidi, mListSimpleHttpCallBack);
                break;
        }
    }
}

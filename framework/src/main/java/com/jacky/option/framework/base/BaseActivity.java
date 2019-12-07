package com.jacky.option.framework.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jacky.option.framework.mvp.IPresenter;
import com.jacky.option.framework.mvp.IView;
import com.jacky.option.framework.receiver.NetworkChangeReceiver;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : BaseActivity
 * </pre>
 * <p>
 * 抽象创建Presenter方法。
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView, IActivity {
    private NetworkChangeReceiver mNetworkChangeReceiver;
    protected P mPresenter;

    protected abstract P createPresenter();

    public void setNeedWatchNetworkChange(boolean needWatchNetworkChange) {
        if (needWatchNetworkChange) {
            registerNetChangeReceiver();
        } else {
            unregisterNetChangeReceiver();
        }
    }

    protected void onNetworkStatedChanged(boolean connected) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null && createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        unregisterNetChangeReceiver();
    }

    public void registerNetChangeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetworkChangeReceiver = new NetworkChangeReceiver() {

            @Override
            protected void onNetworkStatedChanged(boolean connected) {
                BaseActivity.this.onNetworkStatedChanged(connected);
            }
        };
        registerReceiver(mNetworkChangeReceiver, intentFilter);
    }

    private void unregisterNetChangeReceiver() {
        if (mNetworkChangeReceiver != null) {
            unregisterReceiver(mNetworkChangeReceiver);
            mNetworkChangeReceiver = null;
        }
    }
}

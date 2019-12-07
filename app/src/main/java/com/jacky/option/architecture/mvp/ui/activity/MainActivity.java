package com.jacky.option.architecture.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jacky.option.architecture.R;
import com.jacky.option.architecture.base.NoPresenterActivity;

/**
 * 没有mvp模式，但是为统一继承同一基类，让其继承NoPresenterActivity。
 * 这样就能依然实现父类的 showLoading()和hideLoading()。
 */
public class MainActivity extends NoPresenterActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

//        showLoading();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                startActivity(new Intent(this, KuaidiActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, NoFrameworkActivity.class));
                break;
        }
    }
}

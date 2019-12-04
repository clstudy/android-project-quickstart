package com.jacky.option.architecture.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jacky.option.architecture.R;
import com.jacky.option.architecture.base.NoPresenterActivity;


public class MainActivity extends NoPresenterActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, AppVersionActivity.class));
//        showLoading();
    }


}

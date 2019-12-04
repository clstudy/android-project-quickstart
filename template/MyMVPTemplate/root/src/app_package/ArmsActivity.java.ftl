package ${ativityPackageName};

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jacky.option.architecture.base.BaseSupportActivity;


import ${contractPackageName}.${pageName}Contract;
import ${presenterPackageName}.${pageName}Presenter;



<#import "root://activities/MyMVPTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
public class ${pageName}Activity extends BaseSupportActivity<${pageName}Presenter> implements ${pageName}Contract.View {

    @Override
    protected ${pageName}Presenter createPresenter() {
        return new ${pageName}Presenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

package ${presenterPackageName};

import com.jacky.option.framework.mvp.BasePresenter;
import ${contractPackageName}.${pageName}Contract;

<#import "root://activities/MyMVPTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
public class ${pageName}Presenter extends BasePresenter<${pageName}Contract.View> implements ${pageName}Contract.Prestenter {

}


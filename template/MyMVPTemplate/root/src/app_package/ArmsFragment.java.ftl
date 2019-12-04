package ${fragmentPackageName};

import com.jacky.option.architecture.base.BaseSupportFragment;

import ${contractPackageName}.${pageName}Contract;
import ${presenterPackageName}.${pageName}Presenter;


<#import "root://activities/MyMVPTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
public class ${pageName}Fragment extends BaseSupportFragment<${pageName}Presenter> implements ${pageName}Contract.View{

    public static ${pageName}Fragment newInstance() {
        ${pageName}Fragment fragment = new ${pageName}Fragment();
        return fragment;
    }


}

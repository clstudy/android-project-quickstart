package ${contractPackageName};

import com.jacky.option.framework.mvp.IPresenter;
import com.jacky.option.framework.mvp.IView;

<#import "root://activities/MyMVPTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
public interface ${pageName}Contract {
    interface View extends IView {

    }

    interface Prestenter extends IPresenter<View> {

    }
}

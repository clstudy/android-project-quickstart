package com.jacky.option.framework.mvp;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : Model层，数据层。供Presenter层调用。
 * </pre>
 */
public interface IModel {

    /**
     * 监听Model被销毁。
     */
    void onDestory();

}

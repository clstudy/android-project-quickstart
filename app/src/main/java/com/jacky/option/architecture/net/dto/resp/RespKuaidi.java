package com.jacky.option.architecture.net.dto.resp;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class RespKuaidi {


    /**
     * time : 2019-11-16 16:56:31
     * context : 查无结果
     * ftime : 2019-11-16 16:56:31
     */

    private String time;
    private String context;
    private String ftime;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}

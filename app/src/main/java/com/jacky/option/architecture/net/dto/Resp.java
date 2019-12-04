package com.jacky.option.architecture.net.dto;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class Resp<T> {

    /**
     * code : 0
     * data : {"queryPage":1,"querySize":20,"queryType":"QUERY_RESET","records":[{"apk":"xxty_beta_1.3.4_Build20191203_1102.apk","apkUrl":"/upload/20191203/29fb36accd9d42234f2ec253a99c510d.3.7_build20191203_1049_fortest.apk","count":283,"createTime":1572283145000,"deleted":false,"id":5,"imgLogo":"/upload/20191029/e7bbfd49f9863cc1dbbcef5a706ae646.png","intro":"新增驻村领导扫码签到功能\n优化地区的展示效果\n修复已知的若干bug","name":"信息田园","packageName":"cn.xxxx","size":28.54,"updateTime":1575355614000,"version":"137","versionName":"1.3.7"}],"totalPage":0}
     * message : 操作成功
     * one : {"apk":"xxty_beta_1.3.4_Build20191203_1102.apk","apkUrl":"/upload/20191203/29fb36accd9d42234f2ec253a99c510d.3.7_build20191203_1049_fortest.apk","count":283,"createTime":1572283145000,"deleted":false,"id":5,"imgLogo":"/upload/20191029/e7bbfd49f9863cc1dbbcef5a706ae646.png","intro":"新增驻村领导扫码签到功能\n优化地区的展示效果\n修复已知的若干bug","name":"信息田园","packageName":"cn.xxxx","size":28.54,"updateTime":1575355614000,"version":"137","versionName":"1.3.7"}
     * success : true
     */

    private int code;
    private T data;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}

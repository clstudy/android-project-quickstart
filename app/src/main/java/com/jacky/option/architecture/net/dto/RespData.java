package com.jacky.option.architecture.net.dto;

import java.util.List;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class RespData<R> {


    /**
     * queryPage : 1
     * querySize : 20
     * queryType : QUERY_RESET
     * records : [{"apk":"xxty_beta_1.3.4_Build20191203_1102.apk","apkUrl":"/upload/20191203/29fb36accd9d42234f2ec253a99c510d.3.7_build20191203_1049_fortest.apk","count":283,"createTime":1572283145000,"deleted":false,"id":5,"imgLogo":"/upload/20191029/e7bbfd49f9863cc1dbbcef5a706ae646.png","intro":"新增驻村领导扫码签到功能\n优化地区的展示效果\n修复已知的若干bug","name":"信息田园","packageName":"cn.xxxx","size":28.54,"updateTime":1575355614000,"version":"137","versionName":"1.3.7"}]
     * totalPage : 0
     */

    private int queryPage;
    private int querySize;
    private String queryType;
    private int totalPage;
    private List<R> records;

    public int getQueryPage() {
        return queryPage;
    }

    public void setQueryPage(int queryPage) {
        this.queryPage = queryPage;
    }

    public int getQuerySize() {
        return querySize;
    }

    public void setQuerySize(int querySize) {
        this.querySize = querySize;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<R> getRecords() {
        return records;
    }

    public void setRecords(List<R> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * apk : xxty_beta_1.3.4_Build20191203_1102.apk
         * apkUrl : /upload/20191203/29fb36accd9d42234f2ec253a99c510d.3.7_build20191203_1049_fortest.apk
         * count : 283
         * createTime : 1572283145000
         * deleted : false
         * id : 5
         * imgLogo : /upload/20191029/e7bbfd49f9863cc1dbbcef5a706ae646.png
         * intro : 新增驻村领导扫码签到功能
         优化地区的展示效果
         修复已知的若干bug
         * name : 信息田园
         * packageName : cn.xxxx
         * size : 28.54
         * updateTime : 1575355614000
         * version : 137
         * versionName : 1.3.7
         */

        private String apk;
        private String apkUrl;
        private int count;
        private long createTime;
        private boolean deleted;
        private int id;
        private String imgLogo;
        private String intro;
        private String name;
        private String packageName;
        private double size;
        private long updateTime;
        private String version;
        private String versionName;

        public String getApk() {
            return apk;
        }

        public void setApk(String apk) {
            this.apk = apk;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgLogo() {
            return imgLogo;
        }

        public void setImgLogo(String imgLogo) {
            this.imgLogo = imgLogo;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}

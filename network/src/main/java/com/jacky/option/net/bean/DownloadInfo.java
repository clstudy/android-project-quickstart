package com.jacky.option.net.bean;

import java.io.File;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */

public class DownloadInfo {

    public static final long TOTAL_ERROR = -1;//获取进度失败
    private String url;//下载链接
    private long total;
    private long progress;
    private String fileName;//文件名
    private File savePath;//路径

    public DownloadInfo(String url, File savePath) {
        this.url = url;
        this.savePath = savePath;
    }

    public String getUrl() {
        return url;
    }

    public File getSavePath() {
        return savePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }


}

package com.jacky.option.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by jacky-chen on 2016/6/21.
 * 创建文件路径
 */
public class FileUtils {

    /**
     * 缓存的父目录，默认路径是外存储卡中的"/storage/emulated/0/Android/data/<包名>/cache";
     * 若读取路径失败，否则路径未"/data/data/<包名>/files"。
     *
     * @return 目录
     */
    public static File getParentCacheDir() {
        if (isSDAvailable()) {
            File cacheDir = UIUtils.getContext().getExternalCacheDir();
            // /storage/emulated/0/Android/data/<包名>/cache 外存储卡
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return UIUtils.getContext().getFilesDir();// /data/data/<包名>/files
    }

    /**
     * @param filePath 子路路径
     * @return 返回含有filePath的路径
     */
    public static File getCacheDir(String filePath) {
        StringBuilder path = new StringBuilder();
        path.append(getParentCacheDir().getAbsolutePath());
        path.append(File.separator);
        path.append(filePath);

        File file = new File(path.toString());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();// 创建文件夹
        }
        return file;
    }

    /**
     * @param parentDirName
     * @param dirName
     * @return 外存储卡创建文件
     */
    public static File getExternalCacheDir(String parentDirName, String dirName) {
        if (!isSDAvailable()) return null;

        StringBuilder path = new StringBuilder();
        path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        path.append(File.separator);// '/'
        path.append(parentDirName);// /mnt/sdcard/<ROOT>
        path.append(File.separator);
        path.append(dirName);// /mnt/sdcard/<ROOT>/<folderName>

        File file = new File(path.toString());
        if (file.isDirectory() || !file.exists()) {
            file.mkdirs();// 创建文件夹
        }
        return file;
    }

    /**
     * @param fileName 文件名
     * @return 外存储卡的根目录下的文件
     */
    public static File createExternalRootFile(String fileName) {
        if (!isSDAvailable()) return null;
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        return file;
    }

    /**
     * @return 外存储卡可用
     */
    public static boolean isSDAvailable() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


}

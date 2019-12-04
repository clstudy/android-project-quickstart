package com.jacky.option.framework.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by jacky-chen on 2016/6/21.
 * 创建文件路径
 */
public class FileUtils {

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

    public static File createExternalRootFile(String fileName) {
        if (!isSDAvailable()) return null;
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        return file;
    }

    public static boolean isSDAvailable() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


}

package com.jacky.option.net;

import com.jacky.option.net.bean.DownloadInfo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jacky on 2018/3/21.
 * banker developer. <br/>
 * <br/>
 */
public class DownloadManager {

    private final String TAG = "DownloadManager";

    private static final AtomicReference<DownloadManager> INSTANCE = new AtomicReference<>();
    private HashMap<String, Call> mDownCalls;//用来存放各个下载的请求
    private HashMap<String, String> mSaveFilesNames;//用来存放各个下载的保存的本地全路径
    private OkHttpClient mClient;//OKHttpClient;

    //获得一个单例类
    public static DownloadManager getInstance() {
        for (; ; ) {
            DownloadManager current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new DownloadManager();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private DownloadManager() {
        mDownCalls = new HashMap<>();
        mSaveFilesNames = new HashMap<>();
        mClient = new OkHttpClient.Builder().build();
    }

    private String getDownloadPath(String url) {
        return mSaveFilesNames.get(url);
    }


    /**
     * 删除下载文件
     *
     * @param url 下载链接
     * @return 删除成功
     */
    public boolean deleteDownloadFile(String url) {
        String downloadPath = getDownloadPath(url);
        if (downloadPath == null) return false;
        File file = new File(downloadPath);
        if (!file.exists()) return false;
        return file.delete();
    }

    /**
     * 下载
     *
     * @param url              下载链接
     * @param savePath         保存路径
     * @param downLoadObserver 监听
     */
    public void download(final String url, final File savePath, Subscriber<DownloadInfo> downLoadObserver) {
        if (savePath == null || !savePath.exists()) return;
        Flowable.just(url)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !mDownCalls.containsKey(s);
                    }
                })
                .flatMap(new Function<String, Publisher<DownloadInfo>>() {
                    @Override
                    public Publisher<DownloadInfo> apply(String s) {
                        return Flowable.just(createDownInfo(s, savePath));
                    }
                })
                .map(new Function<DownloadInfo, DownloadInfo>() {

                    @Override
                    public DownloadInfo apply(DownloadInfo downloadInfo) {
                        mSaveFilesNames.put(url, savePath + downloadInfo.getFileName());
//                        LogUtils.d(TAG, "downloadInfo.getFileName()=" + savePath + downloadInfo.getFileName());
                        return getRealFileName(downloadInfo);
                    }
                })
                .flatMap(new Function<DownloadInfo, Flowable<DownloadInfo>>() {
                    @Override
                    public Flowable<DownloadInfo> apply(DownloadInfo downloadInfo) {
                        return Flowable.create(new DownloadSubscribe(downloadInfo), BackpressureStrategy.LATEST);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//在主线程回调
                .subscribeOn(Schedulers.io())//在子线程执行
                .subscribe(downLoadObserver);//添加观察者
    }

    /**
     * 取消下载
     *
     * @param url 下载链接
     */
    public void cancel(String url) {
        Call call = mDownCalls.get(url);
        if (call != null) {
            call.cancel();//取消
        }
        mDownCalls.remove(url);
    }

    private DownloadInfo createDownInfo(String url, File savePath) {
        DownloadInfo downloadInfo = new DownloadInfo(url, savePath);
        long contentLength = getContentLength(url);//获得文件大小
        downloadInfo.setTotal(contentLength);
        String fileName = url.substring(url.lastIndexOf("/"));
        downloadInfo.setFileName(fileName);
        return downloadInfo;
    }

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        String fileName = downloadInfo.getFileName();
        long downloadLength = 0, contentLength = downloadInfo.getTotal();
        if (contentLength == -1) {
            throw new IllegalArgumentException("源链接不可用");
        }

        File file = new File(downloadInfo.getSavePath(), fileName);
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length();
        }
        //之前下载过,需要重新来一个文件
        int i = 1;
        while (downloadLength >= contentLength) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(downloadInfo.getSavePath(), fileNameOther);
            file = newFile;
            downloadLength = newFile.length();
            i++;
        }
        //设置改变过的文件名/大小
        downloadInfo.setProgress(downloadLength);
        downloadInfo.setFileName(file.getName());
        return downloadInfo;
    }

    private class DownloadSubscribe implements FlowableOnSubscribe<DownloadInfo> {
        private DownloadInfo downloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }

        @Override
        public void subscribe(FlowableEmitter<DownloadInfo> e) throws Exception {
            String url = downloadInfo.getUrl();
            long downloadLength = downloadInfo.getProgress();//已经下载好的长度
            long contentLength = downloadInfo.getTotal();//文件的总长度
            //初始进度信息
            e.onNext(downloadInfo);

            Request request = new Request.Builder()
                    //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                    .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                    .url(url)
                    .build();
            Call call = mClient.newCall(request);
            mDownCalls.put(url, call);//把这个添加到call里,方便取消
            Response response = call.execute();

            File file = new File(downloadInfo.getSavePath(), downloadInfo.getFileName());
            InputStream is = null;
            FileOutputStream fileOutputStream = null;
            try {
                is = response.body().byteStream();
                fileOutputStream = new FileOutputStream(file, true);
                byte[] buffer = new byte[2048];//缓冲数组2kB
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    downloadLength += len;
                    downloadInfo.setProgress(downloadLength);
                    e.onNext(downloadInfo);
                }
                fileOutputStream.flush();
                mDownCalls.remove(url);
            } catch (Exception exception) {
                exception.printStackTrace();
                e.onError(exception);
            } finally {
                //关闭IO流
                closeQuietly(is);
                closeQuietly(fileOutputStream);
            }
            e.onComplete();//完成
        }
    }

    public void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable var2) {
            }
        }

    }

    private long getContentLength(String downloadUrl) {
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                response.close();
                long contentLength = response.body().contentLength();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }

}

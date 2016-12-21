package com.rickwan.qiger.utils.common;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * author 万强
 * date 16/9/7 上午9:59
 * desc ${TODO}
 */
public class AppUtils {

    /**
     * 下载新版本
     * @param context
     * @param url
     */
    public void downLoadAPK(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        try {
            String serviceString = Context.DOWNLOAD_SERVICE;
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(serviceString);

            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            File fileFolder = new File(Environment.getExternalStorageDirectory(),
                    "CHProperty");
            if (!fileFolder.exists()) {
                fileFolder.mkdirs();
            }
            request.setDestinationInExternalPublicDir(fileFolder.getAbsolutePath(), "CHProperty.apk");
            downloadManager.enqueue(request);
        } catch (Exception exception) {

            ToastUtils.init(context).show("更新失败");

        }

    }
}

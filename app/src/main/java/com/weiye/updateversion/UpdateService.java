package com.weiye.updateversion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;


import com.weiye.zl.R;

import java.io.File;

/**
 * Created by Administrator on 2016/12/27.
 */
public class UpdateService extends Service {

    private String apkUrl;
    private String filePath;
    private NotificationManager notificationManager;
    private Notification notification;

    @Override
    public void onCreate() {
        Log.e("tag", "UpdateService onCreate()");
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        filePath = Environment.getExternalStorageDirectory() + "/AppUpdate/sharlpark.apk";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("tag", "UpdateService onStartCommand()");
        if (intent == null) {
            notifyUser(getString(R.string.update_download_failed), getString(R.string.update_download_failed), 0);

            stopSelf();

        }
        apkUrl = intent.getStringExtra("apkUrl");
        notifyUser(getString(R.string.update_download_start), getString(R.string.update_download_start), 0);
        startDownload();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload() {
        UpdateManager.getInstance().startDownloads(apkUrl, filePath, new UpdateDownloadListener() {
            @Override
            public void onStarted() {
                Log.e("tag", "onStarted()");

            }

            @Override
            public void onProgressChanged(int progress, String downloadUrl) {
                Log.e("onProgressChanged", progress + "");

                notifyUser(getString(R.string.update_download_progressing), getString(R.string.update_download_progressing), progress);
            }

            @Override
            public void onFinished(float completeSize, String downloadUrl) {
                Log.e("tag", "onFinished()");
                notifyUser(getString(R.string.update_download_finish), getString(R.string.update_download_finish), 100);
                stopSelf();
            }

            @Override
            public void onFailure() {
                Log.e("tag", "onFailure()");
                notifyUser(getString(R.string.update_download_failed), getString(R.string.update_download_failed), 0);
                stopSelf();
            }
        });
    }

    /**
     * 更新notification
     *
     * @param result
     * @param msg
     * @param progress
     */
    private void notifyUser(String result, String msg, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.sharkpark))
                .setContentTitle(getString(R.string.app_name));
        if (progress > 0 && progress <= 100) {

            builder.setProgress(100, progress, false);

        } else {
            builder.setProgress(0, 0, false);
        }
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker(result);
        builder.setContentIntent(progress >= 100 ? getContentIntent() :
                PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
        notification = builder.build();
        notificationManager.notify(0, notification);

    }

    /**
     * 进入apk安装程序
     *
     * @return
     */
    private PendingIntent getContentIntent() {
        PendingIntent pendingIntent = null;
        File apkFile = new File(filePath);
        if (Build.VERSION.SDK_INT >= 24) {
            if (apkFile.exists()) {
                Intent intent = new Intent();
                intent.addFlags(268435456);
                intent.setAction("android.intent.action.VIEW");
                String type = getMIMEType(apkFile);
                intent.setDataAndType(Uri.fromFile(apkFile), type);
                try {
                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    startActivity(intent);
                } catch (Exception var5) {
                    var5.printStackTrace();
                    Toast.makeText(this, "下载完成，请手动安装。", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(Uri.parse("file://" + apkFile.getAbsolutePath()),
                    "application/vnd.android.package-archive");
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            startActivity(intent);
        }
        return pendingIntent;
    }


    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

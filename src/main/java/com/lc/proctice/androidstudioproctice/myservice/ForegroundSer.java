package com.lc.proctice.androidstudioproctice.myservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import com.lc.proctice.androidstudioproctice.DrawBasicPicShapeActitivty;
import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 18/8/15.
 */
public class ForegroundSer extends Service {
    private Notification notification;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //声明一个通知，并对其属性进行设置
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("ForeGround Service")
                .setContentText("ForeGround Service started");
        //声明一个Intent,用于开启一个Activity
        Intent resultIntent = new Intent(getApplicationContext(),DrawBasicPicShapeActitivty.class);
        PendingIntent resultPendingIntent=PendingIntent.getActivity(ForegroundSer.this, 0,
                resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(resultPendingIntent);
        notification = builder.build();
        //把当前服务设定为前台服务，并指定显示的通知
        startForeground(1,notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在服务销毁的时候，使当前服务推出前台，并销毁显示的通知
        stopForeground(false);
    }
}

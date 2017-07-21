package com.lc.proctice.androidstudioproctice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by licheng on 18/8/15.
 */
public class MyService extends Service{
    private static String TAG = "MyService";
    public static final String ACTION = "com.licheng.Protice.MyService";


    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"service IBinder!");
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(TAG,"service onCreate!");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "service onStartCommand!");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.v(TAG,"service onStart!");
        super.onStart(intent, startId);
    }


}

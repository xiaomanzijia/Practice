package com.lc.proctice.androidstudioproctice.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by licheng on 18/8/15.
 */
public class BindService extends Service {
    private static final String TAG = "BindService";
    private int count=0;
    private boolean quit=false;
    private Thread thread;
    private MyBinder binder = new MyBinder();

    /** 创建和和客户端通信的Binder,公开它的方法,便于客户端调用 **/
    public class MyBinder extends Binder{
        public int getCount(){
            return count;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "service is onCreate");
        /** 另起线程 **/
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        });
        thread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "Service is Binded");
        return binder;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "Service is started");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "Service is Unbinded");
        return true;
    }

    @Override
    public void onDestroy() {
        Log.v(TAG,"service is destroyed");
        this.quit = true;
    }
}

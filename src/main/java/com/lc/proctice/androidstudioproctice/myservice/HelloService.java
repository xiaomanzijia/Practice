package com.lc.proctice.androidstudioproctice.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;

/**
 * Created by licheng on 24/7/16.
 */
public class HelloService extends Service {

    private ServiceHandler serviceHandler;
    private Looper looper;



    /** 处理从线程获取到的消息 **/
    private final class ServiceHandler extends Handler{

        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            /** 线程等待5秒 **/
            long endTime = System.currentTimeMillis() + 5*1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                }
            }
            /** 根据startId停止当前服务,这样我们就不必要在处理其他工作的过程中来终止这个服务 **/
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /** Service运行在主线程,为了不阻塞线程,我们需要另起线程,还可以赋予线程优先级 **/
        HandlerThread handlerThread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();

        /** 获取线程的Looper队列用于Handler **/
        looper = handlerThread.getLooper();
        serviceHandler = new ServiceHandler(looper);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /** 发送消息,传入当前服务startId **/
        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);
        /** 如果服务被杀死,从这里返回后服务将被重启 **/
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /** 如果不支持绑定,就返回Null **/
        return null;
    }
}

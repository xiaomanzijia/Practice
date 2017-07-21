package com.lc.proctice.androidstudioproctice.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by licheng on 23/7/16.
 */
public class MessengerService extends Service {

    public static final int MSG_SAY_HELLOW = 1;
    /**
     * 从客户端接收消息的Handler
     */
    class IncomingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_SAY_HELLOW:
                    Toast.makeText(getApplicationContext(),"hellow",Toast.LENGTH_SHORT).show();
                    repalyMessenger = msg.replyTo;
                    /** 开启一个线程模拟,三秒后回传服务器数据给客户端 **/
                    new ReplayThread().start();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    /**
     * 向客户端公布的用于向IncomingHandler发送信息的Messager
     */
    final Messenger messenger = new Messenger(new IncomingHandler());

    /** 用于接收客户端回传来的messenger **/
    private Messenger repalyMessenger = null;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 当绑定到服务时，我们向Messager返回接口，
     * 用于向服务发送消息
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private class ReplayThread extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /** 服务端向客户端发送消息 **/
            Message msg = new Message();
            msg.what = 111;
            try {
                if(null != repalyMessenger)
                repalyMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }
}

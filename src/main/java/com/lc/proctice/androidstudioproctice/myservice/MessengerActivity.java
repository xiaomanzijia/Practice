package com.lc.proctice.androidstudioproctice.myservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 23/7/16.
 */
public class MessengerActivity extends Activity {

    private Button btnGetMessage;

    private boolean mBound;

    /** 用于和服务通信的messager **/
    private Messenger mService = null;

    /** 用于回传的messenger **/
    private Messenger sendMessenger = null;

    /** 用于处理服务端向客户端发送的消息 **/
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 111){
                Toast.makeText(MessengerActivity.this, "服务器给客户端回传的消息", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 与服务建立联接后将会调用本方法，
            // 给出用于和服务交互的对象。
            // 我们将用一个Messenger来与服务进行通信，
            // 因此这里我们获取到一个原始IBinder对象的客户端实例。
            mBound = true;
            mService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };

    /** 客户端向服务端发送消息 **/
    public void sayHello(){
        if(!mBound) return;

        sendMessenger = new Messenger(handler);
        // 创建并向服务发送一个消息，用到了已约定的'what'值
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLOW, 0, 0);
        try {
            msg.replyTo = sendMessenger;
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        /** 绑定服务 **/
        bindService(new Intent(this, MessengerService.class), connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        btnGetMessage = (Button) findViewById(R.id.btnGetMessage);
        btnGetMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayHello();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound){
            unbindService(connection);
            mBound = false;
        }
    }



}

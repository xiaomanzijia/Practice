package com.lc.proctice.androidstudioproctice.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 17/1/16.
 */
public class TestDriverActivity extends Activity {
    private TextView textHttpUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpurlconnection_layout);
        textHttpUrl = (TextView) findViewById(R.id.textHttpResponse);

        //创建并启动工作线程
        Thread workThread= new Thread(new SampleTask(new MyHandler()));
        workThread.start();

    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            String message = msg.getData().getString("message");
            textHttpUrl.setText(message);
        }
    }
}

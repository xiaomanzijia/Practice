package com.lc.proctice.androidstudioproctice.httpclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 17/1/16.
 */
//教程地址 http://www.jianshu.com/p/3141d4e46240?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io
public class HttpURLConnectionActivity extends Activity {
    //在主线程new的Handler 会在主线程后进行处理
    private Handler handler = new Handler();
    private TextView textHttpResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpurlconnection_layout);
        textHttpResponse = (TextView) findViewById(R.id.textHttpResponse);



        //未封装的自定义网络Get请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //同步&异步 同步方式 直接耗时操作阻碍主线程直到数据接收完毕 程序崩溃
//                final String response = NetUtils.get("http://www.diandidaxue.com:8080/apiServer.do?opcode=getBeauty&pageNum=1&numPerPage=5");
//                //向handler发送处理
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i("自定义Get网络请求",response);
//                        //在UI线程更新UI
//                        textHttpResponse.setText(response);
//                    }
//                });
//            }
//        }).start();

        //封装的自定义网络Get请求
        AsynNetUtils.get("http://www.diandidaxue.com:8080/apiServer.do?opcode=getBeauty&pageNum=1&numPerPage=5", new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                textHttpResponse.setText(response);
            }
        });
    }
}

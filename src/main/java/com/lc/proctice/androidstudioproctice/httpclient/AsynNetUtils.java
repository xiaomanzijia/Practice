package com.lc.proctice.androidstudioproctice.httpclient;

import android.os.Handler;

/**
 * Created by licheng on 17/1/16.
 */
//基于NetUtils封装
public class AsynNetUtils {
    //提供一个网络数据回调接口
    public interface Callback{
        void onResponse(String response);
    }

    public static void get(final String url,final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetUtils.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }

    public static void post(final String url,final String data,final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response= NetUtils.post(url,data);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }
}

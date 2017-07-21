package com.lc.proctice.androidstudioproctice.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by licheng on 18/8/15.
 */
public class IntentSer extends IntentService {

    public IntentSer(){
        super("IntentSer");
    }


    private static final String TAG = "IntentSer";
    private String url_path="http://ww2.sinaimg.cn/bmiddle/9dc6852bjw1e8gk397jt9j20c8085dg6.jpg";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service is Created");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service is Destroyed");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentSer(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "HandleIntent is execute");

        try {
            //在设备应用目录创建一个文件
            File file = new File(this.getFilesDir(),"weibo.jpg");
            FileOutputStream fos = new FileOutputStream(file);

            //获取网络图片的输入流
            InputStream inputStream = new URL(url_path).openStream();
            //把网络图片输入流写入到文件输出流
            byte[] date = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(date))!=-1){
                fos.write(date,0,len);
            }
            fos.close();
            inputStream.close();
            Log.i(TAG, "The file download is complete");

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

package com.lc.proctice.androidstudioproctice.asynctask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * Created by licheng on 17/1/16.
 */
public class SampleTask implements Runnable{
    private Handler handler;

    public SampleTask(Handler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            //模拟执行任务过程
            Thread.sleep(5000);
            //任务完成后通知Activity更新UI
            Message msg = preMessage("任务完成");
            //Message将被添加到主线程的MQ
            handler.sendMessage(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Message preMessage(String message){
        //创建一个Message对象 绑定要传递的消息
        Message result = handler.obtainMessage();
        Bundle data = new Bundle();
        data.putString("message",message);
        result.setData(data);
        return result;
    }
}

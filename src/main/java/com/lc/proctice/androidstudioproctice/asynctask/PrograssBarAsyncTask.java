package com.lc.proctice.androidstudioproctice.asynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.asynctask.NetOperator;

import org.w3c.dom.Text;

/**
 * Created by licheng on 17/8/15.
 */
public class PrograssBarAsyncTask extends AsyncTask<Integer,Integer,String> {
    private TextView textView;
    private ProgressBar progressBar;

    public PrograssBarAsyncTask(TextView textView,ProgressBar progressBar) {
        super();
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        textView.setText("异步操作开始执行");
    }

    //这里的Integer相当于AsynTask的第一个参数，String相当于AsyncTask第三个参数
    @Override
    protected String doInBackground(Integer... params) {
        NetOperator netOperator = new NetOperator();
        int i =0;
        for(i = 10;i<=100;i+=10){
            netOperator.operator();
            publishProgress(i);
        }
        return i+params[0].intValue()+"";
    }


    //这里的Integer相当于AsyncTask的第二个参数
    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        progressBar.setProgress(value);
    }

    //这里的result相当于AsyncTask的第三个参数，也是doInBackground的返回值
    @Override
    protected void onPostExecute(String result) {
        textView.setText("异步操作执行结束" + result);
    }


}

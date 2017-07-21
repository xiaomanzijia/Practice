package com.lc.proctice.androidstudioproctice.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 17/8/15.
 */
public class AsyncTaskActivity extends Activity{
    private Button button,btn_get_img;
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView img;
    private GetInternetImg getInternetImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);

        button = (Button)findViewById(R.id.button03);
        progressBar = (ProgressBar)findViewById(R.id.progressBar02);
        textView = (TextView)findViewById(R.id.textView01);
        img = (ImageView) findViewById(R.id.img_show);
        btn_get_img = (Button) findViewById(R.id.btn_get_img);

        getInternetImg = new GetInternetImg(getApplicationContext(),progressBar,textView,img);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrograssBarAsyncTask task = new PrograssBarAsyncTask(textView,progressBar);
                task.execute(1000);
            }
        });

        btn_get_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启异步线程下载图片
                getInternetImg.execute("http://ww2.sinaimg.cn/bmiddle/9dc6852bjw1e8gk397jt9j20c8085dg6.jpg");
            }
        });

    }
}

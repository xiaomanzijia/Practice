package com.lc.proctice.androidstudioproctice;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;



public class MyStrickActivity extends Activity implements MyTrickeyScroollView.MyTrickeyScrollViewListener {
    private MyTrickeyScroollView scrollView;
    private TextView trickey,tv_1,tv_2,tv_3;
    private Button btn_getheights;
    private TextView holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickey);
        initView();
        scrollView.setScrollViewListener(this);

        //监听动态实时布局
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged(scrollView.getScrollY());
                    }
                });

        btn_getheights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trickeyTop = trickey.getTop();
                int trickeyLeft = trickey.getLeft();
                int trickeyRight = trickey.getRight();
                int trickeyBottom = trickey.getBottom();
                int[] location = new int[2];
                trickey.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                Log.d("trickey相对于屏幕的坐标:","横坐标："+x+"纵坐标："+y);
                Log.d("trickey的相对于父亲顶部距离为：",""+trickeyTop);
                Log.d("trickey的相对于父亲左边距离为：",""+trickeyLeft);
                Log.d("trickey的相对于父亲右边距离为：",""+trickeyRight);
                Log.d("trickey的相对于父亲底部距离为：",""+trickeyBottom);

            }
        });
    }

    private void initView() {
        trickey = (TextView) findViewById(R.id.trickey);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        btn_getheights = (Button) findViewById(R.id.btn_getheights);
        scrollView = (MyTrickeyScroollView) findViewById(R.id.scrollView);
        holder = (TextView) findViewById(R.id.holder);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onScrollChanged(int scrollY) {
        Log.d("holder.getTop():",holder.getTop()+"scrollY:"+scrollY+"差值："+(holder.getTop()-scrollY)+"两者最大值："+Math.max(holder.getTop(),scrollY));
        //setTranslationY:距离父控件顶部的距离
        trickey.setTranslationY(Math.max(holder.getTop(),scrollY));
    }



}

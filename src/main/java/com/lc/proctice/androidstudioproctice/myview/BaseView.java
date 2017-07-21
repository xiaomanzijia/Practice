package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by licheng on 17/8/15.
 */
public abstract class BaseView extends View {
    protected Paint paint = new Paint();
    int rx = 30;
    private MyThread thread;
    Random random = new Random();


    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected abstract void drawSub(Canvas canvas);

    protected abstract void logic();

    @Override
    protected final void onDraw(Canvas canvas) {
        if(thread==null){
            thread = new MyThread();
            thread.start();
        }else {
            drawSub(canvas);
        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            while (true){
                logic();
                //刷新view
                postInvalidate();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

package com.lc.proctice.androidstudioproctice.myview.liziyu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by licheng on 22/9/15.
 */
public abstract class BaseView extends View {

    private MyThread mThread;
    protected long sleeptime = 30;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected abstract void init();
    protected abstract void drawSub(Canvas canvas);
    protected abstract void logic();

    @Override
    protected final void onDraw(Canvas canvas) {
        if(mThread==null){
            mThread = new MyThread();
            mThread.start();
        }else {
            drawSub(canvas);
        }
    }

    private boolean runing = true;
    class MyThread extends Thread{
        @Override
        public void run() {
            init();
            long worktime;
            while (runing){
                worktime = System.currentTimeMillis();
                postInvalidate();
                logic();
                worktime = System.currentTimeMillis()-worktime;
                if(worktime<sleeptime){
                    try {
                        mThread.sleep(sleeptime-worktime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

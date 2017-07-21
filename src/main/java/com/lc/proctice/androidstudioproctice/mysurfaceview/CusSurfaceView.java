package com.lc.proctice.androidstudioproctice.mysurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by licheng on 21/7/17.
 */

public class CusSurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    private SurfaceHolder holder;
    private DrawThread thread;

    public CusSurfaceView(Context context) {
        super(context);
        initHolder();
    }

    private void initHolder() {
        holder = this.getHolder();
        holder.addCallback(this);
        thread = new DrawThread(holder);
    }

    public CusSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHolder();
    }

    public CusSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHolder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRun = true;
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRun = false;
    }

    private class DrawThread extends Thread {

        private SurfaceHolder holder;
        private boolean isRun = false;

        public DrawThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            int count = 0;
            Canvas canvas = null;
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffffff"));
            Rect r = new Rect(100, 100, 600, 600);
            while (isRun) {
                try {
                    synchronized (this.holder) {
                        canvas = this.holder.lockCanvas();
                        canvas.drawColor(Color.parseColor("#000000"));
                        canvas.drawRect(r, paint);
                        canvas.drawText("这是第" + (count++) + "秒", 100, 700, paint);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(canvas != null) {
                        this.holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
            super.run();
        }
    }
}

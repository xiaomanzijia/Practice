package com.lc.proctice.androidstudioproctice.mysurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 21/7/17.
 */

public class CircleSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Thread t;
    private volatile boolean flag;
    private Canvas mCanvas;
    float m_circle_r = 10;


    public CircleSurfaceView(Context context) {
        super(context);
        initHolder();
    }

    public CircleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHolder();
    }

    private void initHolder() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true); // 设置焦点
    }

    /**
     * 当SurfaceView创建的时候调用此方法
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        t = new Thread(this);
        flag = true;
        t.start();
    }

    /**
     * 当SurfaceView视图发生改变的时候调用此方法
     *
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 当SurfaceView销毁的时候调用此函数
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
        mHolder.removeCallback(this);
    }

    /**
     * 当屏幕被触摸时调用
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    /**
     * 当用户按键时调用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        surfaceDestroyed(mHolder);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void run() {
        while (flag) {
            try {
                synchronized (mHolder) {
                    Thread.sleep(500);
                    Draw();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (mCanvas != null) {
//                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }

    /**
     * 在画布上绘制一个圆
     */
    private void Draw() {
        mCanvas = mHolder.lockCanvas(); //获得画布对象
        if (mCanvas != null) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(20);
            paint.setStyle(Paint.Style.FILL);
            if (m_circle_r >= (getWidth() / 10)) {
                m_circle_r = 0;
            } else {
                m_circle_r ++;
            }
            Bitmap pic = ((BitmapDrawable)getResources().getDrawable(R.drawable.img1)).getBitmap();
            mCanvas.drawBitmap(pic, 0, 0, paint);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    mCanvas.drawCircle((getWidth() / 5) * i + getWidth() / 10, (getHeight() / 8) * j + getHeight() / 16, m_circle_r, paint);
                }
            }
            mHolder.unlockCanvasAndPost(mCanvas); //完成绘制,将画布显示在屏幕上
        }
    }
}

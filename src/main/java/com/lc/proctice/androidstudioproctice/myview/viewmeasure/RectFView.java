package com.lc.proctice.androidstudioproctice.myview.viewmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by licheng on 26/7/16.
 */
public class RectFView extends View {

    private Paint mPaint = new Paint();
    private int mWidth, mHeight;

    public RectFView(Context context) {
        super(context);
    }

    public RectFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.parseColor("red"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2); //移动画布坐标原点到屏幕中心 接下来画矩形中心位置就会相对该坐标原点画
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);
        canvas.drawArc(rect, 0, 30, true, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}

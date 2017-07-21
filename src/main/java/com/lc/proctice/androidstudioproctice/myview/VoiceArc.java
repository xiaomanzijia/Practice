package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by licheng on 31/1/16.
 */
public class VoiceArc extends View {

    private Paint mPaint;
    private float offset = 10;


    public VoiceArc(Context context) {
        super(context);
    }

    public VoiceArc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VoiceArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(400,400,420,500,mPaint);
        for (int i = 0; i < 50; i ++){
            double random = Math.random();
            float height = (float) (random * 400);
            canvas.drawRect(400+i*40,height,420+i*40,480,mPaint);
        }
        postInvalidateDelayed(300);
    }
}

package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by licheng on 30/1/16.
 */
public class CircleArc extends View {

    private float mCirclrXY;
    private float mRadius;
    private RectF mArcRecF;
    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;


    public CircleArc(Context context) {
        super(context);
    }

    public CircleArc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        float height = getMeasuredHeight();
        float width = getMeasuredWidth();

        Log.i("height",height+" "+width);

        mCirclrXY = width / 2;
        mRadius = mCirclrXY / 2;

        mArcRecF = new RectF((float) (width * 0.1),(float) (width * 0.1),(float) (width * 0.9),(float) (width * 0.9));
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.GRAY);
        //画圆
        canvas.drawCircle(mCirclrXY,mCirclrXY,mRadius,mCirclePaint);

        mArcPaint = new Paint();
        mArcPaint.setStrokeWidth(50);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setColor(Color.DKGRAY);
        //画弧线
        canvas.drawArc(mArcRecF,270,270,false,mArcPaint);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(40);
        //画文字
        canvas.drawText("hello wrold",0,11,mCirclrXY-80,mCirclrXY,mTextPaint);
        super.onDraw(canvas);
    }
}

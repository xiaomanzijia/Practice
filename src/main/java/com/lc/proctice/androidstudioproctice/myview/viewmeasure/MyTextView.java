package com.lc.proctice.androidstudioproctice.myview.viewmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by licheng on 25/1/16.
 */
public class MyTextView extends TextView {
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix matrix;
    private int mTranslate = 0;


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultWidth = 0;
        int resultHeight = 0;
        int specModeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int specSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specSizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        Log.i("MeasureSpec",specSizeWidth+" "+specSizeHeight+" "+MeasureSpec.EXACTLY);

        if(specModeWidth == MeasureSpec.EXACTLY){
            resultWidth = specSizeWidth;
            resultHeight = specSizeHeight;
        }else {
            resultWidth = 300;
            resultHeight = 200;
            if(specModeWidth == MeasureSpec.AT_MOST){
                resultWidth = Math.min(resultWidth,specSizeWidth);
                resultHeight = Math.min(resultHeight,specSizeHeight);
            }
        }
        setMeasuredDimension(resultWidth,resultHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制文字背景：重叠矩形框
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint1.setColor(Color.DKGRAY);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setColor(Color.DKGRAY);
        mPaint2.setStyle(Paint.Style.FILL);
        Log.i("measureWidth",getMeasuredWidth()+" "+getMeasuredHeight());
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
        canvas.save();
        canvas.translate(10,0);
        super.onDraw(canvas);
        canvas.restore();

        //绘制文字颜色渐变
        if(matrix != null){
            mTranslate += getMeasuredWidth() / 10;
            if(mTranslate > 2 * getMeasuredWidth()){
                mTranslate = - getMeasuredWidth();
            }
            matrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(90);
        }
    }

    //组件大小改变时调用此方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = getPaint();
        //线性渐变器
        mLinearGradient = new LinearGradient(0,0,getMeasuredWidth(),0,
                new int[]{Color.DKGRAY,Color.WHITE,Color.DKGRAY},null,
                Shader.TileMode.CLAMP);
        //shader渲染器
        mPaint.setShader(mLinearGradient);
        matrix = new Matrix();
    }
}

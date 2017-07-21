package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by licheng on 26/7/16.
 */
public class PieView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private List<PieData> dataList;

    private Paint mPaint = new Paint();

    private int mWidth, mHeight;

    private float mStartAngle = 0; //起始角度


    public PieView(Context context) {
        super(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(null == dataList){
            return;
        }

        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);

        for (int i = 0; i < dataList.size(); i++) {
            PieData pie = dataList.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rect, currentStartAngle, pie.getAngle(), true, mPaint);
            currentStartAngle += pie.getAngle();
        }
    }

    public void setDataList(List<PieData> dataList) {
        this.dataList = dataList;
        initData(dataList);
        //刷新界面
        invalidate();
    }

    /** 设置起始角度 **/
    public void setmStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    /** 数据初始化 **/
    private void initData(List<PieData> dataList) {
        if(null == dataList || dataList.size() == 0){
            return;
        }

        float sumValue = 0;
        for (int i = 0; i < dataList.size(); i++) {
            PieData pie = dataList.get(i);

            sumValue += pie.getValue();

            int j = i % mColors.length;
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < dataList.size(); i++) {
            PieData pie = dataList.get(i);

            float percentage = pie.getValue() / sumValue;
            float angle = percentage * 360;

            pie.setPercentage(percentage);
            pie.setAngle(angle);
            sumAngle += angle;

            Log.i("PieView", "扇形角度：" + pie.getAngle());

        }
    }
}

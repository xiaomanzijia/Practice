package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 17/8/15.
 */
public class MyAttrView extends BaseView {
    private int lineNum = 1;
    //文字坐标位置
    private int mx = 0;
    private Boolean mxScroll = false;

    public MyAttrView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyAttrView(Context context) {
        super(context);
    }

    public MyAttrView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NumText);
        lineNum = array.getInt(R.styleable.NumText_lineNum,1);
        mxScroll = array.getBoolean(R.styleable.NumText_mxScroll,false);
        array.recycle();
    }

    @Override
    protected void drawSub(Canvas canvas) {
        paint.setColor(Color.WHITE);
        for(int i = 0;i<lineNum;i++){
            int textSize = 30+i;
            paint.setTextSize(textSize);
            canvas.drawText("菊花与刀", mx, textSize+textSize*i, paint);
        }

    }

    @Override
    protected void logic() {
        if(mxScroll) {
            mx += 3;
            if (mx > getWidth()) {
                mx = (int) -paint.measureText("菊花与刀");
            }
        }
    }
}

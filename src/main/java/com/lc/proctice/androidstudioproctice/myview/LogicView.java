package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by licheng on 17/8/15.
 */
public class LogicView extends BaseView{


    public LogicView(Context context) {
        super(context);
    }

    public LogicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawSub(Canvas canvas) {
        paint.setTextSize(30);
        canvas.drawText("Hello World!",rx,30,paint);
        canvas.drawText("Hello!",rx,60,paint);
    }


    @Override
    protected void logic() {
        rx += 3;
        if(rx>getWidth()){
            rx = (int) (0 - paint.measureText("Hello World!"));
        }
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        paint.setARGB(255,r,g,b);
    }
}

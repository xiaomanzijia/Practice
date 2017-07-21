package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.lc.proctice.androidstudioproctice.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by licheng on 13/1/16.
 */
public class TextImageView extends ImageView {
    private final static String TAG = "TextImageView";
    private String text;
    private Paint paint;
    private boolean panEnabled = false;
    private enum ClampMode{UNLIMITED,ORIGININSIDE,TEXTINSIDE};
    private ClampMode clampTextMode;
    private Rect textRect;
    private float fingerX = 200f;
    private float fingerY = 200f;
    // 分别记录上次滑动的坐标
    private float mLastX = 0;
    private float mLastY = 0;

    public TextImageView(Context context) {
        super(context);
    }

    public TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }



    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        if(null != attrs){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textRect = new Rect();
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.TextImageView,0,0);
        Resources resource = context.getResources();
        paint.setColor(array.getColor(R.styleable.TextImageView_textColor, Color.BLACK));
        paint.setTextSize(array.getDimensionPixelSize(R.styleable.TextImageView_textSize,resource.getDimensionPixelSize(R.dimen.textsize_16)));
        panEnabled = array.getBoolean(R.styleable.TextImageView_panEnabled,false);
        clampTextMode = ClampMode.values()[array.getInt(R.styleable.TextImageView_clampTextMode,0)];
        setText(array.getString(R.styleable.TextImageView_text));
        array.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x1 = event.getRawX();
        float y1 = event.getRawY();
        Log.d(TAG,x1+" "+y1);
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE: {
                float deltaX = x1 - mLastX;
                float deltaY = y1 - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
                float translationX = ViewHelper.getTranslationX(this) + deltaX;
                float translationY = ViewHelper.getTranslationX(this) + deltaY;
                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);
                break;
            }
        }
        mLastX = x1;
        mLastY = y1;
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text,fingerX,fingerY,paint);
    }

    private void setText(String text) {
        this.text = text;
        if(null != text){
            paint.getTextBounds(text,0,text.length(),textRect);
        }
        invalidate();
    }

}

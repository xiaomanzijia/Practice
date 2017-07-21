package com.lc.proctice.androidstudioproctice.indexablelistview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;


/**
 * Created by licheng on 29/10/15.
 */
public class MyIndexableListView extends ListView {
    private MyIndexScroller myIndexScroller;
    private boolean mIsFastScrollEnabled = false;
    private GestureDetector mGestureDetector;

    public MyIndexableListView(Context context) {
        super(context);
    }

    public MyIndexableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyIndexableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFastScrollEnabled() {
        return mIsFastScrollEnabled;
    }

    @Override
    public void setFastScrollEnabled(boolean enabled) {
        mIsFastScrollEnabled = enabled;
        if (mIsFastScrollEnabled) {
            if (myIndexScroller == null)
                myIndexScroller = new MyIndexScroller(getContext(),this);
        } else {
            if (myIndexScroller != null) {
//                myIndexScroller.hide();
                myIndexScroller = null;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(myIndexScroller!=null){
            myIndexScroller.draw(canvas);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        myIndexScroller.onSizeChanged(w,h,oldw,oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(myIndexScroller!=null&&myIndexScroller.onTouchEvent(ev)){
            return true;
        }
        if(mGestureDetector==null){
            mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            });
        }
        mGestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }
}

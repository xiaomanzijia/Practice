package com.lc.proctice.androidstudioproctice.myview.mygroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by licheng on 31/1/16.
 */
public class MyVIewGroup extends ViewGroup {

    private int mScreenHeight;
    private Scroller mScroller;
    private int defaultScreen = 0;
    private float mLastMotionX = 0;
    private float mLastMotionY = 0;
    private float mStart = 0;
    private float mEnd = 0;

    public MyVIewGroup(Context context) {
        super(context);
        init(context);
    }

    public MyVIewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyVIewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }




    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        mScreenHeight = getMeasuredHeight();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = (int) (mScreenHeight * count);
        setLayoutParams(mlp);
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            if(childView.getVisibility() != GONE){
                childView.layout(l,i * mScreenHeight,r,(i + 1) * mScreenHeight);
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        Log.i("y",y+"");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//            if(mVelocityTracker == null) {
//                //测滑动速度
//                mVelocityTracker = VelocityTracker.obtain();
//                mVelocityTracker.addMovement(event);
//            }
//                if(mScroller != null){
//                    mScroller.abortAnimation();
//                }
//                mLastMotionX = x;
                mLastMotionY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                Log.i("mLastMotionY",mLastMotionY+"");
                int dy = (int) (mLastMotionY - y);
                Log.i("dy",dy+"");
                //滑动
                scrollBy(0,dy);
                mLastMotionY = y;
                break;
            //实现粘性效果
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = (int) (mEnd - mStart);
                Log.i("scrollY",mEnd+" "+mStart+" "+dScrollY);
                if(dScrollY > 0){
                    if(dScrollY < mScreenHeight / 3) {
                        Log.i("scrollYf",getScrollY()+"");
                        //startX 水平方向滚动的偏移值，以像素为单位。正值表明滚动将向左滚动
                        //startY 垂直方向滚动的偏移值，以像素为单位。正值表明滚动将向上滚动
                        //dx 水平方向滑动的距离，正值会使滚动向左滚动
                        //dy 垂直方向滑动的距离，正值会使滚动向上滚动
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }else {
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight - dScrollY);
                    }
                }else {
                    if(-dScrollY < mScreenHeight / 3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else {
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return  true;
    }


    private void init(Context context){
        mScroller = new Scroller(context);
    }

    //postInvalidate执行后，会去调computeScroll 方法，
    // 而这个方法里再去调postInvalidate，这样就可以不断地去调用scrollTo方法了，
    // 直到mScroller动画结束，当然第一次时，我们需要手动去调用一次postInvalidate才会去调用
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
        }
    }
}

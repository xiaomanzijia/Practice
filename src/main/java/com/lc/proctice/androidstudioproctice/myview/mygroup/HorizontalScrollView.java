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
 * Created by licheng on 1/8/16.
 * 自定义横向滑动viewpager容器
 * 解决 滑动冲突,View测量和布局
 */
public class HorizontalScrollView extends ViewGroup {


    private Scroller mScroller; //实现弹性滑动
    private VelocityTracker mVeloctiyTracker; //速度追踪

    private int mChildWidth;
    private int mChildSize;

    private int childIndex;

    private int mLastX, mLastY;//记录上次滑动的坐标位置
    private int mLastXIntercept, mLastYIntercept; //记录上次滑动的坐标位置onInterceptTouchEvent


    private void init(){
        mScroller = new Scroller(getContext());
        mVeloctiyTracker = VelocityTracker.obtain();
    }

    public HorizontalScrollView(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /** 处理滑动冲突 **/
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean isIntercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();


        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                isIntercept = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    isIntercept = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int detailX = x - mLastXIntercept;
                int detailY = y - mLastYIntercept;

                Log.i("HorizontalScrollView", "detailX-->" + detailX + "  detailY-->" + detailY);

                if(Math.abs(detailX) > Math.abs(detailY)){ //如果横向滑动
                    isIntercept = true;
                }else {
                    isIntercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
            default:
                break;
        }

        Log.i("HorizontalScrollView", "是否拦截--> " + isIntercept);


        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return isIntercept;

    }

    /** 手势处理 **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mVeloctiyTracker.addMovement(event);

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int detailX = x - mLastX;
                scrollBy(-detailX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                mVeloctiyTracker.computeCurrentVelocity(1000);
                int velcotX = (int) mVeloctiyTracker.getXVelocity();
                if(Math.abs(velcotX) > 50){
                    childIndex = velcotX > 0 ? childIndex - 1 : childIndex + 1;
                }else {
                    childIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                Log.i("HorizontalScrollView", "childIndex-->" + childIndex);
                childIndex = Math.max(0, Math.min(childIndex, mChildSize - 1));
                Log.i("HorizontalScrollView", "childIndex-->" + childIndex);
                int distance = childIndex * mChildWidth - scrollX;
                mScroller.startScroll(scrollX, 0, distance, 0, 500);
                invalidate();
                mVeloctiyTracker.clear();
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;

        return true;

    }

    /** 测量 **/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        /** 容器下的view自己测量自己 获取view自己的widthMeasureSpec和heightMeasureSpec **/
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpaceMode = MeasureSpec.getMode(widthMeasureSpec);
        int heihtSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpaceMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0); //设置容器的宽度和高度
        } else if (widthSpaceMode == MeasureSpec.AT_MOST) { //如果宽度是wrap_content,则用childview的测量宽度
            View childView = getChildAt(0);
            int width = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(width, heihtSpaceSize);//设置容器的宽度和高度
        } else if (heightSpaceMode == MeasureSpec.AT_MOST){ //如果高度是wrap_content,则用childview的测量高度
            View chilView = getChildAt(0);
            setMeasuredDimension(widthSpaceSize, chilView.getMeasuredHeight());//设置容器的宽度和高度
        } else {
            View childView = getChildAt(0);
            int width = childView.getMeasuredWidth() * childCount;
            int height = childView.getMeasuredHeight();
            setMeasuredDimension(width, height);//设置容器的宽度和高度
        }

    }

    /** 布局 **/
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        int childCount = getChildCount();
        mChildSize = childCount;

        for (int i = 0; i < childCount; i++) {
            View childview = getChildAt(i);
            if(childview.getVisibility() != GONE){
                int childWidth = childview.getMeasuredWidth();
                mChildWidth = childWidth;
                childview.layout(childLeft, 0, childLeft + childWidth, childview.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }


    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVeloctiyTracker.recycle();
        super.onDetachedFromWindow();
    }
}

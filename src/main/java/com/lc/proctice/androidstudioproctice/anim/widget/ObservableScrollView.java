package com.lc.proctice.androidstudioproctice.anim.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by licheng on 10/6/16.
 * ScrollView滑动处理
 */
public class ObservableScrollView extends ScrollView implements Scrollable{

    private int mPrevScrollY;
    private int mScrollY;

    private ScrollState mScrollState;

    private boolean mFirstScroll,mDraggging;

    private ObservableScrollViewCallbacks callbacks;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        mScrollY = t;

        if (callbacks != null) {
            callbacks.onScrollChanged(t, mFirstScroll, mDraggging);
        }

        if (mFirstScroll) {
            mFirstScroll = false;
        }

        Log.i("ObservableScrollview",mPrevScrollY +" "+ t);

        if(mPrevScrollY < t){
            mScrollState = ScrollState.UP;
        }else {
            mScrollState = ScrollState.DOWN;
        }

        mPrevScrollY = t;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                mFirstScroll = mDraggging = true;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDraggging = false;
                callbacks.onUpOrCancleMotionEvent(mScrollState);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    public void setScrollViewCallbacks(ObservableScrollViewCallbacks listener) {
        callbacks = listener;
    }

    @Override
    public void addScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void removeScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void clearScrollViewCallbacks() {

    }

    @Override
    public void scrollVerticallyTo(int y) {
        scrollTo(0,y);
    }

    @Override
    public int getCurrentScrollY() {
        return mScrollY;
    }

    @Override
    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {

    }
}

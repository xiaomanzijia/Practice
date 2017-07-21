package com.lc.proctice.androidstudioproctice.anim.widget;

/**
 * Created by licheng on 10/6/16.
 * ScrollView滑动监听回调
 */
public interface ObservableScrollViewCallbacks {

    /**
     * 滑动过程监听
     * @param scrollY 当前滑动位置
     * @param firstScoll 是否第一次滑动
     * @param dragging 是否滑动中
     */
    void onScrollChanged(int scrollY, boolean firstScoll, boolean dragging);

    //当按下事件发生时候调用此方法
    void onDownMotionEvent();

    void onUpOrCancleMotionEvent(ScrollState scrollState);

}

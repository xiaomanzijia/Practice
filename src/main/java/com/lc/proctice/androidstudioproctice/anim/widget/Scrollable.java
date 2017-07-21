package com.lc.proctice.androidstudioproctice.anim.widget;

import android.view.ViewGroup;

/**
 * Created by licheng on 10/6/16.
 * 自定义ScrollView滑动监听
 */
public interface Scrollable {

    void addScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void setScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void removeScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void clearScrollViewCallbacks();

    void scrollVerticallyTo(int y);

    //获取Y轴滑动距离
    int getCurrentScrollY();

    void setTouchInterceptionViewGroup(ViewGroup viewGroup);

}

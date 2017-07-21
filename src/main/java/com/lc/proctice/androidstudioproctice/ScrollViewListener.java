package com.lc.proctice.androidstudioproctice;

import java.util.Observable;

/**
 * Created by licheng on 22/7/15.
 */
public interface ScrollViewListener {
    void onScrollChanged(ObservableScrollView scrollView,int x,int y,int oldx,int oldy);
}

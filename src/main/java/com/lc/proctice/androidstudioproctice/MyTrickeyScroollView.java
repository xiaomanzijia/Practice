package com.lc.proctice.androidstudioproctice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by licheng on 20/9/15.
 */
public class MyTrickeyScroollView extends ScrollView{
    private MyTrickeyScrollViewListener mScrollViewListener;

    public MyTrickeyScroollView(Context context) {
        super(context);
    }

    public MyTrickeyScroollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(MyTrickeyScrollViewListener scrollViewListener){
        this.mScrollViewListener = scrollViewListener;
    }

    //此方法不属于ScrollView，属于View，我们需要一个接口将它共享出去
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mScrollViewListener!=null){
            mScrollViewListener.onScrollChanged(t);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                break;
        }
        return super.onTouchEvent(ev);
    }

    //计算垂直方向滚动条的滑块的偏移
    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public interface MyTrickeyScrollViewListener{
        void onScrollChanged(int scrollY);
    }
}

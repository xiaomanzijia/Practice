package com.lc.proctice.androidstudioproctice;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * Created by licheng on 23/7/15.
 */
public class MeituanScrollView extends ScrollView{
    //监听器
    private OnScrollListener scrollViewListener;



    //ScrollView停止滑动后上下滑动的距离
    private int lastScrollViewY;

    public MeituanScrollView(Context context) {
        super(context);
    }

    public MeituanScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeituanScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //当前ScrollView上下滑动的距离
            int currentY = MeituanScrollView.this.getScrollY();
            //
            if(currentY!=lastScrollViewY){
                lastScrollViewY = currentY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }

            if(scrollViewListener!=null){
                handler.sendMessageDelayed(handler.obtainMessage(),5);
            }
            super.handleMessage(msg);
        }
    };

    public void setScrollViewListener(OnScrollListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(scrollViewListener!=null){
            scrollViewListener.onScroll(lastScrollViewY=this.getScrollY());
        }
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(),5);
                break;
        }
        return super.onTouchEvent(ev);
    }


   public interface OnScrollListener{
       public void onScroll(int currentY);
    }
}

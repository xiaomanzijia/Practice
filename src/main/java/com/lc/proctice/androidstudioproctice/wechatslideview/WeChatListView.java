package com.lc.proctice.androidstudioproctice.wechatslideview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by licheng on 4/11/15.
 */
public class WeChatListView extends ListView {
    public static String TAG = "WeChatListView";
    private SlideViewWeChat mItem;

    public WeChatListView(Context context) {
        super(context);
    }

    public WeChatListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeChatListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                //根据坐标点获取当前item位置
                int position = pointToPosition(x,y);
                Log.d(TAG,"position:"+position);
                if(position!=INVALID_POSITION){
                    WeChatSlideViewActivity.WeChatMessageItem data = (WeChatSlideViewActivity.WeChatMessageItem) getItemAtPosition(position);
                    mItem = data.slideView;
                }
                break;
            default:
                break;
        }

        //给item的slideview设置触摸事件
        if(mItem!=null){

        }

        return super.onTouchEvent(ev);
    }
}

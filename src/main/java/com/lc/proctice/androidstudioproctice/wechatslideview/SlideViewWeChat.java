package com.lc.proctice.androidstudioproctice.wechatslideview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 4/11/15.
 */
public class SlideViewWeChat extends LinearLayout {
    private static final String TAG = "SlideView";

    private Context mContext;
    private LinearLayout mViewContent;
    private Scroller mScroller;
    private int mHolderWidth = 120;


    public SlideViewWeChat(Context context) {
        super(context);
        initView();
    }

    public SlideViewWeChat(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setContentView(View view){
        mViewContent.addView(view);
    }

    private void initView(){
        mContext = getContext();
        mScroller = new Scroller(mContext);
        setOrientation(HORIZONTAL);
        View.inflate(mContext, R.layout.slide_view_merge, this);
        mViewContent = (LinearLayout) findViewById(R.id.view_content);
        mHolderWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mHolderWidth,getResources().getDisplayMetrics());
    }
}

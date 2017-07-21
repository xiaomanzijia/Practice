package com.lc.proctice.androidstudioproctice.anim;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollView;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollViewCallbacks;
import com.lc.proctice.androidstudioproctice.anim.widget.ScrollState;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by licheng on 15/6/16.
 */
public class FlexibleSpaceActionBarScrollviewActiivty extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private View mFlexibleSpaceView;
    private View mToolbarView;
    private TextView mTitleView;
    private int mFlexibleSpaceHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexiblespacetoolbarscrollview);

        mFlexibleSpaceView = findViewById(R.id.flexible_space);
        mTitleView = (TextView) findViewById(R.id.title);

        mTitleView.setText(getTitle());
        setTitle(null);
        mToolbarView = findViewById(R.id.toolbar);

        final ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.scroll);
        scrollView.setScrollViewCallbacks(this);

        mFlexibleSpaceHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_height);//216
        int flexibleSpaceAndToolbarHeight = mFlexibleSpaceHeight + getActionBarSize(); //216+168=384

        Log.i("mFlexibleSpaceHeight:",mFlexibleSpaceHeight+" ActionBarSize:"+getActionBarSize());


        findViewById(R.id.body).setPadding(0, flexibleSpaceAndToolbarHeight ,0 ,0);
        //初始化加载时给滑动可变区域设置一个高度 384
        mFlexibleSpaceView.getLayoutParams().height = flexibleSpaceAndToolbarHeight;


        findViewById(R.id.body).setPadding(0, flexibleSpaceAndToolbarHeight ,0 ,0);
        mFlexibleSpaceView.getLayoutParams().height = flexibleSpaceAndToolbarHeight;
        ScrollUtils.addOnGlobalLayoutListener(mTitleView, new Runnable() {
            @Override
            public void run() {
                updateFlexibleSpaceText(scrollView.getCurrentScrollY());
            }
        });

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScoll, boolean dragging) {
        updateFlexibleSpaceText(scrollY);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancleMotionEvent(ScrollState scrollState) {

    }

    protected int getActionBarSize(){
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        TypedArray typedArray = this.obtainStyledAttributes(typedValue.data,textSizeAttr);
        int actionBarSize = typedArray.getDimensionPixelSize(0,-1);
        typedArray.recycle();
        return actionBarSize;
    }

    private void updateFlexibleSpaceText(final int scrollY) {
        Log.i("scrollY滑动距离:",scrollY+" mFlexibleSpaceHeight高度:"+mFlexibleSpaceHeight );
        //设置滑动区域的滑动距离
        ViewHelper.setTranslationY(mFlexibleSpaceView, -scrollY);

        //设置滑动区域的滑动距离介于0-216
        int adjustedScrollY = (int) ScrollUtils.getFloat(scrollY, 0, mFlexibleSpaceHeight);

        Log.i("scrollY滑动距离:","adjustedScrollY" + adjustedScrollY);

        //计算文字缩放的倍数值
        float maxScale = (float) (mFlexibleSpaceHeight - mToolbarView.getHeight()) / mToolbarView.getHeight();
        float scale = maxScale * ((float) mFlexibleSpaceHeight - adjustedScrollY) / mFlexibleSpaceHeight;


        //设置文字放大倍数
        ViewHelper.setPivotX(mTitleView, 0);
        ViewHelper.setPivotY(mTitleView, 0);
        ViewHelper.setScaleX(mTitleView, 1 + scale);
        ViewHelper.setScaleY(mTitleView, 1 + scale);

        //设置文字移动位置
        int maxTitleTranslationY = mToolbarView.getHeight() + mFlexibleSpaceHeight - (int) (mTitleView.getHeight() * (1 + scale));
        int titleTranslationY = (int) (maxTitleTranslationY * ((float) mFlexibleSpaceHeight - adjustedScrollY) / mFlexibleSpaceHeight);
        ViewHelper.setTranslationY(mTitleView, titleTranslationY);
    }
}

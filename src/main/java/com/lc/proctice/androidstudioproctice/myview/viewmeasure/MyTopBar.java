package com.lc.proctice.androidstudioproctice.myview.viewmeasure;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 26/1/16.
 */
public class MyTopBar extends ViewGroup {

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String LeftText;
    private int mRightTextColor;
    private Drawable mRightBackGround;
    private String RightText;
    private String mTitleText;
    private int mTitleColor;
    private int mTitleTextSize;

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private RelativeLayout.LayoutParams mLeftParams;
    private RelativeLayout.LayoutParams mRightParams;
    private RelativeLayout.LayoutParams mTitleParams;


    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTopBar);
        mLeftTextColor = array.getColor(R.styleable.MyTopBar_leftTextColor,0);
        mLeftBackground = array.getDrawable(R.styleable.MyTopBar_leftBackground);
        LeftText = array.getString(R.styleable.MyTopBar_leftText);
        mRightTextColor = array.getColor(R.styleable.MyTopBar_rightColor,0);
        mRightBackGround = array.getDrawable(R.styleable.MyTopBar_rightBackground);
        RightText = array.getString(R.styleable.MyTopBar_rightText);
        mTitleText = array.getString(R.styleable.MyTopBar_titleCenter);
        mTitleColor = array.getColor(R.styleable.MyTopBar_titleCenterTextColor,0);
        mTitleTextSize = (int) array.getDimension(R.styleable.MyTopBar_titleCenterTextSize,10);
        array.recycle();

        Log.i("TopBar",LeftText+" "+mTitleText+" "+RightText);

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setText(LeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLeftButton.setBackground(mLeftBackground);
        }

        mRightButton.setText(RightText);
        mRightButton.setTextColor(mRightTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRightButton.setBackground(mRightBackGround);
        }

        mTitleView.setText(mTitleText);
        mTitleView.setTextColor(mTitleColor);
        mTitleView.setTextSize(mTitleTextSize);

        mLeftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(mLeftButton,mLeftParams);

        mRightParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(mRightButton,mRightParams);

        mTitleParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(mTitleView,mTitleParams);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View v = getChildAt(0);
        v.layout(l,t,r,b);
    }

}

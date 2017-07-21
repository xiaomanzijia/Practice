package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licheng on 2/8/16.
 */
public class FlowLayout extends ViewGroup {

    private int screenWidth;


    private void init(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
    }

    public FlowLayout(Context context) {
        super(context);
        init(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();

        int measureWidth = 0;//测量的行宽

        int measureHeight = 0;//测量累计的行高

        int lineWidth = 0;//行宽

        int lineHeight = 0;//行高



        /** 获取父控件给子控件的测量参数大小和模式 **/
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        if(childCount == 0){
            setMeasuredDimension(0,0);
        }else {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);//获取到child View对象

                /** 容器下的view自己测量自己 获取view自己的widthMeasureSpec和heightMeasureSpec **/
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                /** 获取子View的margin值从而得到子View的真实宽度 **/
                MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
                int width = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                int height = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;


                /** 如果之前累加的宽度加上当前View的宽度超过屏幕宽度，则另起一行 **/
                if(lineWidth + width > screenWidth){

                    measureWidth = Math.max(lineWidth, width);

                    measureHeight += lineHeight;

                    lineWidth = width; //重置行宽

                    lineHeight = height; //重置行高

                }else {
                    lineWidth += width;
                    lineHeight = Math.max(lineHeight, height); //取View的最大高度
                }

                if(i == childCount - 1){
                    measureWidth = Math.max(width, lineWidth);
                    measureHeight += lineHeight;
                }

            }


            Log.i("FlowLayout", "测量宽-->" + measureWidth + "  测量高-->" + measureHeight);


            setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize
                    : measureWidth, (heightMode == MeasureSpec.EXACTLY) ? heightSize
                    : measureHeight);

//            /** 处理wrap_content下显示子View的宽高 **/
//            if(widthMode == MeasureSpec.AT_MOST){
//                setMeasuredDimension(measureWidth, heightSize);
//            }else if(heightMode == MeasureSpec.AT_MOST){
//                setMeasuredDimension(widthSize, measureHeight);
//            }else {
//                setMeasuredDimension(measureWidth, measureHeight);
//            }
        }
    }


    private List<List<View>> mAllChildViews = new ArrayList<>();

    private List<Integer> mHeights = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllChildViews.clear();
        mHeights.clear();

        int lineWidth = 0;//行款

        int lineHeight = 0;//行高

        int width = getWidth();//获取父控件宽度

        int childCount = getChildCount();

        List<View> lineViews = new ArrayList<>();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            //如果当前行宽加上当前View宽度大于屏幕宽度，则换行
            if(lineWidth + childWidth > width){
                mAllChildViews.add(lineViews);
                mHeights.add(lineHeight);
                lineWidth = 0;
                lineViews = new ArrayList<>();
            }else { //不换行状态
                lineWidth += childWidth ;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            lineViews.add(child);
        }

        //记录最后一行
        mHeights.add(lineHeight);
        mAllChildViews.add(lineViews);



        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllChildViews.size();
        for (int i = 0; i < lineNums; i++)
        {
            // 每一行的所有的views
            lineViews = mAllChildViews.get(i);
            // 当前行的最大高度
            lineHeight = mHeights.get(i);

            Log.e("FlowLayout", "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e("FlowLayout", "第" + i + "行， ：" + lineHeight);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++)
            {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc =lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                Log.e("FlowLayout", child + " , l = " + lc + " , t = " + t + " , r ="
                        + rc + " , b = " + bc);

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;


//        List<View> childViewList = new ArrayList<>();
//        List<Integer> childHeightList = new ArrayList<>(); //存储子View的高度
//
//        int childMaxHeight = Integer.MIN_VALUE;
//        int lineChildWidth = 0;
//        int childLeft = 0;
//        int childCount = getChildCount();
//        int lineHeight = 0;
//
//        for (int i = 0; i < childCount; i++) {
//
//            View child = getChildAt(i);
//
//            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
//            int childWidth = params.leftMargin + child.getMeasuredWidth() + params.rightMargin;
//            int childHeight = params.topMargin + child.getMeasuredHeight() + params.bottomMargin;
//
//            childViewList.add(child);
//            childHeightList.add(childHeight);
//
//
//
//
//            /** 如果当前view的宽度累加大于屏幕宽度 **/
//            if(lineChildWidth > screenWidth){
//
//
//                /** 取该行View中最大高度 **/
//                for (int j = 0; j < childHeightList.size(); j++) {
//                    if(childHeightList.get(j) > childMaxHeight){
//                        childMaxHeight = childHeightList.get(j);
//                    }
//                }
//
//                /** 换行 **/
//                lineHeight += childMaxHeight;
//
//                childLeft = 0;
//
//                /** 处理子view集合 **/
//
//
//                if(child.getVisibility() != GONE){
//
//                    child.layout(0, lineHeight, childLeft + childWidth, lineHeight + childHeight);
//
//                }
//
//                lineChildWidth = 0;
//
//                childViewList.clear();
//
//
//                childHeightList.clear();
//            }else {
//                View view = getChildAt(i);
//                /** 取每一行View的最大高度 **/
//                for (int j = 0; j < childHeightList.size(); j++) {
//                    if(childHeightList.get(j) > childMaxHeight){
//                        childMaxHeight = childHeightList.get(j);
//                    }
//                }
//
//                lineHeight += childMaxHeight;
//
//                Log.i("FlowLayout", "view" + (i + 1) + "  left-->" + lineChildWidth + "  top-->" + lineHeight + "  right-->" + (lineChildWidth + childWidth) +"  bottom-->" + (lineHeight + childHeight));
//                view.layout(lineChildWidth, lineHeight, lineChildWidth + childWidth, lineHeight + childHeight);
//            }
//
//            lineChildWidth += childWidth;
//
        }
    }

}

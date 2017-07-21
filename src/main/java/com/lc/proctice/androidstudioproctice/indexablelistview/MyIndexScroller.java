package com.lc.proctice.androidstudioproctice.indexablelistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

/**
 * Created by licheng on 29/10/15.
 */
public class MyIndexScroller {
    private Context mContext;
    private float screenWidth;
    private float screenHeight;
    private float mDensity;
    private float mScaledDensity;
    private float mIndexBarWidth;//索引条宽度
    private float mIndexBarMargin;
    private int sectionCurrent;
    private int mAlphaRate = 1;
    private ListView listview;
    private SectionIndexer mIndexer = null;
    private String[] mSections = null;
    private RectF rectF=null;



    public MyIndexScroller(Context context,ListView lv) {
        this.mContext = context;
        listview = lv;
        setAdapter(listview.getAdapter());
        mDensity = mContext.getResources().getDisplayMetrics().density;
        mScaledDensity = mContext.getResources().getDisplayMetrics().scaledDensity;
        mIndexBarWidth = 20*mDensity;
        mIndexBarMargin = 5*mDensity;

    }

    public void  draw(Canvas canvas){
        //画索引条背景
        Paint indexbarPaint = new Paint();
        indexbarPaint.setColor(Color.YELLOW);
        indexbarPaint.setAntiAlias(true);
        indexbarPaint.setAlpha(64*mAlphaRate);
        canvas.drawRoundRect(rectF,5*mDensity,5*mDensity,indexbarPaint);

        //画索引字母
        Paint letterPaint = new Paint();
        letterPaint.setColor(Color.WHITE);
        letterPaint.setAntiAlias(true);
        letterPaint.setAlpha(255*mAlphaRate);
        letterPaint.setTextSize(12*mScaledDensity);

        float sectionHeight = (rectF.height()-2*mIndexBarMargin)/mSections.length;

        if(mSections!=null&&mSections.length>0){
            for(int i = 0;i<mSections.length;i++){
                canvas.drawText(mSections[i],rectF.left+mIndexBarMargin,rectF.top+sectionHeight*i+mIndexBarMargin,letterPaint);
            }
        }
    }


    public boolean onTouchEvent(MotionEvent ev){
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                sectionCurrent=getSectionByPoint(ev.getY());
                listview.setSelection(mIndexer.getPositionForSection(sectionCurrent));
                break;
            case MotionEvent.ACTION_MOVE:
                sectionCurrent=getSectionByPoint(ev.getY());
                listview.setSelection(mIndexer.getPositionForSection(sectionCurrent));
                break;
        }
        return false;
    }


    public void setAdapter(Adapter adapter) {
        if (adapter instanceof SectionIndexer) {
            mIndexer = (SectionIndexer) adapter;
            mSections = (String[]) mIndexer.getSections();
        }
    }

    public void onSizeChanged(int w,int h,int oldw,int oldh){
        screenWidth = w;
        screenHeight = h;
        rectF = new RectF(screenWidth-mIndexBarWidth-mIndexBarMargin,mIndexBarMargin,screenWidth-mIndexBarMargin,screenHeight-mIndexBarMargin);
    }

    //根据触摸点获取字母索引
    public int getSectionByPoint(float y){
        if(y<rectF.top+mIndexBarMargin){
            return 0;
        }
        if(mSections==null||mSections.length==0){
            return 0;
        }
        if(y>rectF.height()+rectF.top-mIndexBarMargin){
            return mSections.length-1;
        }
        return (int)((y-rectF.top-mIndexBarMargin)/((rectF.height()-2*mIndexBarMargin)/mSections.length));
    }
}

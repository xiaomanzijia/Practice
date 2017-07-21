package com.lc.proctice.androidstudioproctice.myview.liziyu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;

/**
 * Created by licheng on 22/9/15.
 */
public class MusicView extends BaseView {
    private MusicItem mMusicItem;
    private ArrayList<MusicItem> list = new ArrayList<MusicItem>();
    private ArrayList<MusicPoint> pointlist = new ArrayList<MusicPoint>();
    private int itemNums;
    private int itemWidth;
    private MusicPoint mMusicPoint;

    //点的个数
    private int pointNum;

    //画对象的类型：1:方形 2:点
    private int DRAWTYPE;



    public MusicView(Context context) {
        super(context);
    }

    public MusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LiZiYu);
        itemNums = array.getInteger(R.styleable.LiZiYu_itemNum,20);
        itemWidth = array.getInteger(R.styleable.LiZiYu_itemWidth,20);
        pointNum = array.getInteger(R.styleable.LiZiYu_pointNum,50);
        DRAWTYPE = array.getInteger(R.styleable.LiZiYu_drawType,1);
    }

    @Override
    protected void init() {
        switch (DRAWTYPE){
            case 1:
                for(int i = 0;i < itemNums;i++) {
                    list.add(new MusicItem(i*getWidth()/itemNums, itemWidth,getHeight()));
                }
                break;
            case 2:
//                for(int i = 0;i < pointNum;i++){
//                    pointlist.add(new MusicPoint(i*getWidth()/pointNum,getHeight()));
//                }
                mMusicPoint = new MusicPoint(getWidth(),getHeight());
                break;
        }
        //父亲常量
        sleeptime = 150;
    }

    @Override
    protected void drawSub(Canvas canvas) {
        switch (DRAWTYPE){
            case 1:
                for(MusicItem item:list){
                    item.draw(canvas);
                }
                break;
            case 2:
//                for(MusicPoint point:pointlist){
//                    point.draw(canvas);
//                }
                mMusicPoint.draw(canvas);
                break;
        }
    }

    @Override
    protected void logic() {
        switch (DRAWTYPE){
            case 1:
                for(MusicItem item:list){
                    item.move();
                }
                break;
            case 2:
//                for(MusicPoint point:pointlist){
//                    point.move();
//                }
                mMusicPoint.move();
                break;
        }
    }
}

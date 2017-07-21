package com.lc.proctice.androidstudioproctice.myview.liziyu;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by licheng on 22/9/15.
 */
public class MusicPoint implements IAnimation {
    private int x;
    private int pointWidth = 20;
    private int screenHeight;
    private Paint paint = new Paint();
    private Random random = new Random();

    //万花筒
    private int maxX;
    private int maxY;
    //用来保存已经画过的点
    private ArrayList<DrawedPoints> list = new ArrayList<DrawedPoints>();


    public MusicPoint(int x,int screenHeight) {
        this.screenHeight = screenHeight;
        this.x = x;
        paint.setStyle(Paint.Style.STROKE);//封闭图形属性
        paint.setStrokeWidth(pointWidth);
        this.maxX = x;
        this.maxY = screenHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        //画满屏幕的点
//        for(int i = pointWidth;i<screenHeight;i+=pointWidth*2) {
//            canvas.drawPoint(x, i, paint);//画一个点
//        }
        //万花筒效果
        int xx = random.nextInt(maxX);
        int yy = random.nextInt(maxY);
        list.add(new DrawedPoints(xx, yy));
        for(DrawedPoints points:list){
            canvas.drawPoint(points.getX(),points.getY(),paint);
        }
    }

    @Override
    public void move() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        paint.setARGB(255, r, g, b);
    }
}

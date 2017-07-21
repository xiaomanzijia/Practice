package com.lc.proctice.androidstudioproctice.myview.liziyu;

import android.graphics.Canvas;
import android.graphics.Paint;


import java.util.Random;

/**
 * Created by licheng on 22/9/15.
 */
public class MusicItem implements IAnimation{

    private Paint mPaint = new Paint();
    private Random random = new Random();
    private int height;
    private int maxHeight;
    private int x;
    private int itemWidth=20;

    private int maxRectNum;
    private int rectNum;
    private int distance = 3;


    public MusicItem(int height) {
        this.maxHeight = height;
        mPaint.setColor(0xff0000ff);
    }

    public MusicItem(int x, int itemWidth,int height) {
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                mPaint.setARGB(255,r,g,b);
                this.maxHeight = height;
                this.x = x;
                this.itemWidth = itemWidth;
                maxRectNum = maxHeight/(itemWidth+distance);
    }

    @Override
    public void draw(Canvas canvas) {
                int num = maxRectNum-rectNum;
                for(int i = 0;i<rectNum;i++){
                    canvas.drawRect(x,(num+i)*(itemWidth+distance),x+itemWidth,(num+i+1)*(itemWidth+distance)-distance,mPaint);
                }
    }

    @Override
    public void move() {
        height = random.nextInt(maxHeight);
        rectNum = random.nextInt(maxRectNum);
    }
}

package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by licheng on 20/7/15.
 */
public class DrawBasicPicShapeActitivty extends Activity {
    private final String TAG = "DrawBasicPicShapeActitivty";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //位图
        Bitmap bitmap = Bitmap.createBitmap(getWindowManager().getDefaultDisplay().getWidth(),getWindowManager().getDefaultDisplay().getHeight(),
                Bitmap.Config.ARGB_8888);

        //画布
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();//画笔

        paint.setColor(Color.WHITE);

        paint.setStyle(Paint.Style.STROKE);//封闭图形属性
        paint.setStrokeWidth(100);
        canvas.drawPoint(199, 201, paint);//画一个点


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawLine(50, 50, 100, 100, paint);//画一条线


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(400, 400, 200, paint);//园

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(10, 10, 400, 200, paint);//矩形

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        Path path = new Path();
        path.moveTo(20,20);
        path.lineTo(30,30);
        path.lineTo(40,50);
        path.lineTo(50,60);
        canvas.drawPath(path,paint);//绘制路径


        //将绘制的位图设置为ImageView背景
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(p);
        imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));

        setContentView(imageView);


    }
}

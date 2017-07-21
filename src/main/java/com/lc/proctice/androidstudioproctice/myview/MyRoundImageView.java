package com.lc.proctice.androidstudioproctice.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 27/4/16.
 */
public class MyRoundImageView extends ImageView{

    private Context mContext;
    private int imageBorderWidth = 0;
    private int imageBorderOutsideColor = 0;
    private int imageBorderInsideColor = 0;
    private int defaultColor = 0xFFFFFFFF;

    // RoundImageView控件默认的长、宽
    private int defaultWidth = 0;
    private int defaultHeight = 0;

    public MyRoundImageView(Context context) {
        super(context);
        mContext = context;
    }

    public MyRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setAttrbutes(attrs);
    }


    public MyRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setAttrbutes(attrs);
    }

    private void setAttrbutes(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.round_image_view);
        imageBorderWidth = array.getDimensionPixelSize(R.styleable.round_image_view_border_width,0);
        imageBorderInsideColor = array.getColor(R.styleable.round_image_view_border_incolor,defaultColor);
        imageBorderOutsideColor = array.getColor(R.styleable.round_image_view_border_outcolor,defaultColor);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if(drawable == null) return;
        if(getWidth() == 0 || getHeight() == 0) return;
        this.measure(0,0);
        if (drawable.getClass() == NinePatchDrawable.class)
            return;
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        if(defaultWidth == 0) defaultWidth = getWidth();
        if(defaultHeight == 0) defaultHeight = getHeight();
        int outRadius = defaultHeight > defaultWidth ? defaultHeight : defaultWidth;
        drawCirlce(canvas, outRadius / 2 - imageBorderWidth, imageBorderInsideColor);
        drawCirlce(canvas, outRadius / 2, imageBorderOutsideColor);
        Bitmap roundBitmap = getRoundBitmap(bitmap,outRadius);
        canvas.drawBitmap(roundBitmap,defaultWidth / 4, defaultHeight / 4,null);
    }

    private void drawCirlce(Canvas canvas, int radius, int color){
        Paint paint = new Paint();
		/* 去锯齿 */
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
		/* 设置paint的　style　为STROKE：空心 */
        paint.setStyle(Paint.Style.STROKE);
		/* 设置paint的外框宽度 */
        paint.setStrokeWidth(imageBorderWidth);
        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
    }

    private Bitmap getRoundBitmap(Bitmap bitmap, int radius){
        Bitmap newBitmap = null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newBitmapWidth = height > width ? width : height;
        if(height > width){
            newBitmap = bitmap.createBitmap(bitmap, 0, (height - width) / 2, newBitmapWidth,newBitmapWidth);
        }else if(height < width){
            newBitmap = bitmap.createBitmap(bitmap, (width - height) / 2, 0, newBitmapWidth,newBitmapWidth);
        }else {
            newBitmap = bitmap;
        }

        Bitmap output = Bitmap.createBitmap(newBitmap.getWidth(),newBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, newBitmap.getWidth(),newBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(newBitmap.getWidth() / 2, newBitmap.getHeight() / 2, newBitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(newBitmap, rect, rect, paint);
        return output;
    }
}

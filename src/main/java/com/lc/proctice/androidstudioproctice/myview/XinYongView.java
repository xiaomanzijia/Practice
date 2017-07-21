package viewdemo;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by licheng on 4/12/16.
 * 芝麻信用
 */
public class XinYongView extends View {

    private int viewWidth,viewHeight;

    private Paint outerPaint;//最外层弧线
    private Paint littleCirclePaint; //小白点
    private Paint linesPaint;//刻度线
    private Paint textPaint;
    private Paint centerTextPaint;
    private Paint indicatorPaint;//


    private float outerCircleR;//最外层圆弧半径
    private float innerCircleR;//内层圆弧半径

    private float sweepAngle = 210;
    private float startAngle = 165;

    private int xyViewWidth,xyViewHeight;

    private int radius;

    private int outterArcWidth = dipToPx(14), innerArcWidth = dipToPx(4);

    private int arcSpace = dipToPx(12);

    private int currentIndicatorValue = 0;//当前进度条的值

    private int currentScores = 0;




    public XinYongView(Context context) {
        super(context);
        initPaints();
    }

    public XinYongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public XinYongView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();

    }


    //初始化画笔
    private void initPaints() {
        //外圈白色弧线
        outerPaint = new Paint();
        outerPaint.setColor(Color.parseColor("#EDEDED"));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setAlpha(60);

        littleCirclePaint = new Paint();
        littleCirclePaint.setColor(Color.WHITE);
        littleCirclePaint.setStyle(Paint.Style.FILL);
        littleCirclePaint.setStrokeWidth(dipToPx(2));

        linesPaint = new Paint();
        linesPaint.setColor(Color.WHITE);
        linesPaint.setStrokeWidth(dipToPx(1));

        centerTextPaint = new Paint();
        centerTextPaint.setColor(Color.WHITE);
        centerTextPaint.setTextSize(50);
        centerTextPaint.setStyle(Paint.Style.FILL);

        indicatorPaint = new Paint();
        indicatorPaint.setColor(Color.WHITE);
        indicatorPaint.setStyle(Paint.Style.FILL);
        indicatorPaint.setStrokeWidth(dipToPx(6));

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStrokeWidth(dipToPx(6));

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewHeight = h;
        viewWidth = w;
        Log.i("当前View信息", "View宽度" + viewWidth + "  View高度" + viewHeight );
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        if(modeWidth == MeasureSpec.EXACTLY){
            xyViewWidth = sizeWidth;
        }else {
            xyViewWidth = dipToPx(300);
        }

        if(modeHeight == MeasureSpec.EXACTLY){
            xyViewHeight = sizeHeight;
        }else {
            xyViewHeight = dipToPx(400);
        }

        setMeasuredDimension(xyViewWidth, xyViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(xyViewWidth / 2, xyViewHeight / 2);
        radius = getMeasuredWidth() / 4;

        drawOuterArc(canvas);
        drawInnerArc(canvas);
        drawScale(canvas);
        drawCenterText(canvas, currentScores);
        drawIndicator(canvas, currentIndicatorValue);
//        drawLittleCircle(canvas);
    }

    public void setCurrentScores(int currentScores) {
        this.currentScores = currentScores;
        invalidate();
    }

    public void setCurrentIndicatorValue(int currentIndicatorValue) {
        this.currentIndicatorValue = currentIndicatorValue;
//        invalidate();


//        setCurrentScores(currentIndicatorValue / 7 * 20);
//        postInvalidate();
    }

    public void setCurrentIndicatorValueAni(int currentIndicatorValue){
        float duration = currentIndicatorValue / sweepAngle * 100 + 1000;
        ObjectAnimator anim = ObjectAnimator.ofInt(this, "currentIndicatorValue", currentIndicatorValue);
        anim.setDuration((long) Math.min(duration, 2000));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                int color = calculateColor(value);
                setBackgroundColor(color);
                setCurrentScores(value / 7 * 20);
            }
        });
        anim.start();
    }

    private int calculateColor(int value){
        ArgbEvaluator evealuator = new ArgbEvaluator();
        float fraction = 0;
        int color = 0;
        if(value <= sweepAngle/2){
            fraction = (float)value/(sweepAngle/2);
            color = (int) evealuator.evaluate(fraction,0xFFFF6347,0xFFFF8C00); //由红到橙
        }else {
            fraction = ( (float)value-sweepAngle/2 ) / (sweepAngle/2);
            color = (int) evealuator.evaluate(fraction,0xFFFF8C00,0xFF00CED1); //由橙到蓝
        }
        return color;
    }

    //绘制进度条小圆点
    private void drawIndicator(Canvas canvas, int currentIndicatorValue) {
        canvas.save();
        for (int i = 0; i <= currentIndicatorValue; i++) {
                float idcX = (float) ((radius + arcSpace) * Math.cos(Math.toRadians(startAngle + i)));
                float idcY = (float) ((radius + arcSpace) * Math.sin(Math.toRadians(startAngle + i)));
                canvas.drawCircle(idcX, idcY, dipToPx(4), indicatorPaint);
        }
        canvas.restore();
    }

    private void drawCenterText(Canvas canvas, int currentScores) {
        canvas.save();

        canvas.drawText(currentScores+"", -centerTextPaint.measureText(currentScores+"")/2, 0,centerTextPaint);

        String text = "信用极好";
        if(currentScores <= 100){
            text = "信用较差";
        }else if(currentScores <= 200){
            text = "信用中等";
        }else if(currentScores <= 300){
            text = "信用良好";
        }else if(currentScores <= 400){
            text = "信用优秀";
        }else {
            text = "信用极好";
        }

        Rect r = new Rect();
        centerTextPaint.getTextBounds(text, 0, text.length(), r);
        canvas.drawText(text, - r.width() / 2, r.height() + 20, centerTextPaint);
        canvas.restore();
    }

    String[] texts = {"你", "我", "他", "是", "哈", "牛"};
    //绘制刻度线 旋转画布
    private void drawScale(Canvas canvas) {
        canvas.save();
        float angle = sweepAngle / 30;
        canvas.rotate(startAngle - 90);
        for (int i = 0; i < 31; i++) {
            if(i  % 5 == 0){
                linesPaint.setStrokeWidth(dipToPx(2));
                drawText(canvas, String.valueOf(i * 20));
            } else if(i == 3 || i == 8 || i == 13 || i == 18 || i == 23 || i ==28){
                linesPaint.setStrokeWidth(dipToPx(1));
                drawText(canvas, texts[i / 5]);
            } else {
                linesPaint.setStrokeWidth(dipToPx(1));
            }
            canvas.drawLine(0, radius - innerArcWidth - dipToPx(2), 0, radius + innerArcWidth + dipToPx(2), linesPaint);
            canvas.rotate(angle);
        }
        canvas.restore();
    }

    //绘制文字
    private void drawText(Canvas canvas, String text) {
        textPaint.setStyle(Paint.Style.FILL);
        float width = textPaint.measureText(text);
        canvas.drawText(text, - width / 2, radius - 2 * innerArcWidth - dipToPx(6), textPaint);
    }


    //绘制内圈弧线
    private void drawInnerArc(Canvas canvas) {
        RectF rect = new RectF(-radius, -radius, radius,radius);
        outerPaint.setStrokeWidth(outterArcWidth);
        canvas.drawArc(rect, startAngle, sweepAngle, false, outerPaint);
    }

    //绘制外层弧线
    private void drawOuterArc(Canvas canvas) {
        RectF rect = new RectF(-radius - arcSpace, -radius - arcSpace, radius + arcSpace,radius + arcSpace);
        outerPaint.setStrokeWidth(innerArcWidth);
        canvas.drawArc(rect, startAngle, sweepAngle, false, outerPaint);
    }

    //绘制小圆点
    private void drawLittleCircle(Canvas canvas) {
        outerCircleR = (viewWidth - viewWidth * 0.2f - dipToPx(10)) / 2;
        innerCircleR = outerCircleR - dipToPx(20);

        for (int i = -31; i < 211; i++) {
            float aa = (float) (i / 180.0);

            float bb = (float) (Math.sin(aa * Math.PI) * dipToPx(20));
            float cc = (float) (Math.cos(aa * Math.PI) * dipToPx(20));
            bb = Math.abs(bb);
            cc = Math.abs(cc);

            float sinHeight = (float) (outerCircleR * Math.sin((aa * Math.PI)));
            float singWidth = (float) (outerCircleR * Math.cos((aa * Math.PI)));
            sinHeight = Math.abs(sinHeight);
            singWidth = Math.abs(singWidth);



            if(i < 0){
                if(i % 30 == 0){
//                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth + cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight - bb, outerPaint);
                    linesPaint.setStrokeWidth(dipToPx(4));
                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth + cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight - bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight, linesPaint);
                    canvas.drawCircle(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight, dipToPx(10), littleCirclePaint);
                }else if(i % 10 == 0){
                    linesPaint.setStrokeWidth(dipToPx(2));
                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth + cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight - bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight, linesPaint);
                }
            }else if(i < 90){
               if(i % 30 == 0){
//                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth + cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight + bb, outerPaint);
                    linesPaint.setStrokeWidth(dipToPx(4));
                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth + cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight + bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight, linesPaint);
                    canvas.drawCircle(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight, dipToPx(10), littleCirclePaint);
               }else if(i % 10 == 0){
                   linesPaint.setStrokeWidth(dipToPx(2));
                   canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth + cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight + bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR - singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight, linesPaint);
               }
            }else if(i < 180){
                if(i % 30 == 0){
//                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth - cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight + bb, outerPaint);
                    linesPaint.setStrokeWidth(dipToPx(4));
                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth - cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight + bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight, linesPaint);
                    canvas.drawCircle(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight, dipToPx(10), littleCirclePaint);
                }else if(i % 10 == 0){
                    linesPaint.setStrokeWidth(dipToPx(2));
                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth - cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight + bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR - sinHeight, linesPaint);
                }
            } else if (i < 211) {
                if (i % 30 == 0) {
//                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth - cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight - bb, outerPaint);
                    linesPaint.setStrokeWidth(dipToPx(4));
                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth - cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight - bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight, linesPaint);
                    canvas.drawCircle(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight, dipToPx(10), littleCirclePaint);
                }else if(i % 10 == 0){
                    linesPaint.setStrokeWidth(dipToPx(2));
                    canvas.drawLine(viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth - cc, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight - bb, viewWidth * 0.1f + dipToPx(10) / 2 + outerCircleR + singWidth, viewHeight * 0.2f + dipToPx(10) / 2 + outerCircleR + sinHeight, linesPaint);
                }
            }

        }

    }




    private int dipToPx(float dip)
    {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }


}

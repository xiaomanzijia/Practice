package com.lc.proctice.androidstudioproctice.anim.propertyanim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.myview.BandCardEditText;

/**
 * Created by licheng on 25/7/16.
 */
public class PropertyAnimActivity extends Activity {

    private Button btnStartAnim;
    private TextView txtAnimator;
    private ImageView imageView;

    private BandCardEditText editText;

    private int screenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyanim);
        txtAnimator = (TextView) findViewById(R.id.txtAnimator);
        btnStartAnim = (Button) findViewById(R.id.btnStartAnim);
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (BandCardEditText) findViewById(R.id.editBank);

        editText.setBankCardListener(new BandCardEditText.BankCardListener() {
            @Override
            public void success(String name) {
                txtAnimator.setText(" 所属银行:  "+name);
            }

            @Override
            public void failure() {
                txtAnimator.setText("所属银行: " + "null");
            }
        });

        screenHeight = getScreenHeight();

        btnStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** 图片360度翻转 **/
//                final ObjectAnimator ani = ObjectAnimator
//                        .ofFloat(imageView, "licheng", 1.0F, 0.0F)
//                        .setDuration(500);
//                ani.start();
//                ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        float val = (float) ani.getAnimatedValue();
//                        imageView.setAlpha(val);
//                        imageView.setScaleX(val);
//                        imageView.setScaleY(val);
//                    }
//                });

//                propertyValuesHolder(imageView);

//                verticalRun(imageView);

//                changeColor(imageView);

//                verticalRunAndChangeColor(imageView);

//                    paowuxian(imageView);

//                togetherRun(imageView);

                playWithAfter(imageView);

//                Animation ani = AnimationUtils.loadAnimation(PropertyAnimActivity.this, R.anim.myanim);
//                ani.setDuration(3000);
//                imageView.startAnimation(ani);


                /** 获取属性文件 开始动画 **/
//                Animator set = AnimatorInflater.loadAnimator(PropertyAnimActivity.this, R.animator.property_animator);
//                set.setTarget(imageView);
//                set.start();

            }
        });

    }


    private void propertyValuesHolder(View view){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ)
                .setDuration(500)
                .start();
    }


    /** 自由落体 **/
    private void verticalRun(final View view){
        final ValueAnimator ani = ValueAnimator.ofFloat(0, screenHeight, screenHeight - imageView.getHeight());
        ani.setTarget(view);
        ani.setDuration(3000).start();
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((Float) ani.getAnimatedValue());
            }
        });
    }

    /** 背景颜色渐变 **/
    private void changeColor(View view){
        ValueAnimator ani = ObjectAnimator.ofInt(view, "backgroundColor", 0xFFFF8080, 0xFF8080FF);
        ani.setDuration(3000);
        ani.setEvaluator(new ArgbEvaluator());
        ani.setRepeatCount(ValueAnimator.INFINITE);
        ani.setRepeatMode(ValueAnimator.REVERSE);
        ani.start();
    }

    /** 自由落体+背景色渐变 **/
    private void verticalRunAndChangeColor(final View view){
        final ValueAnimator ani1 = ValueAnimator.ofFloat(0, screenHeight, screenHeight - imageView.getHeight());
        ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((Float) ani1.getAnimatedValue());
            }
        });

        ValueAnimator ani2 = ObjectAnimator.ofInt(view, "backgroundColor", 0xFFFF8080, 0xFF8080FF);
        ani2.setEvaluator(new ArgbEvaluator());
        ani2.setRepeatCount(ValueAnimator.INFINITE);
        ani2.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet set  = new AnimatorSet();
        set.setDuration(5000);
        set.playTogether(ani1, ani2);
        set.start();
    }

    /** 抛物线 **/
    private void paowuxian(final View view){
        final ValueAnimator ani = new ValueAnimator();
        ani.setDuration(5000);
        ani.setObjectValues(new PointF(0, 0)); //动画过程中的值
        ani.setInterpolator(new LinearInterpolator()); //设置线性差值器
        ani.setEvaluator(new TypeEvaluator() { //获得差值倍数
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                Log.i("ProperAnimActivity", "fraction: " + fraction);
                PointF pointF = new PointF();
                pointF.x = 200 * fraction * 3;
                pointF.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return pointF;
            }
        });
        ani.start();
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) ani.getAnimatedValue();
                view.setX(pointF.x);
                view.setY(pointF.y);

            }
        });
        /** 添加动画结束监听 **/
        ani.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("PropertyAniActivity", "ani end");
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if(null != viewGroup){
                    viewGroup.removeView(view);
                }
            }
        });
    }

    /** 以下代码展示AnimatorSet用法 用于串联动画 **/
    private void togetherRun(View view) {
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2.0f);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 2.0f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.setInterpolator(new LinearInterpolator());
        set.playTogether(ani1, ani2);
        set.start();
    }

    private void playWithAfter(View view){
        float cx = view.getX();
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2.0f);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 2.0f);
        ObjectAnimator ani3 = ObjectAnimator.ofFloat(view, "x", cx, 0f);
        ObjectAnimator ani4 = ObjectAnimator.ofFloat(view, "x", cx);

        AnimatorSet set = new AnimatorSet();
        set.play(ani2).after(ani1);
        set.play(ani3).after(ani2);
        set.play(ani4).after(ani3);
        set.setDuration(3000);
        set.start();
     }




    /** 获取屏幕高度 **/
    private int getScreenHeight() {
        WindowManager manager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        return manager.getDefaultDisplay().getHeight();
    }
}

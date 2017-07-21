package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

/**
 * Created by licheng on 22/7/15.
 */
public class ViewFlippperActivity extends Activity implements android.view.GestureDetector.OnGestureListener {
    private ViewFlipper viewFlipper;
    private int[] images = {R.drawable.viewpager_img1,R.drawable.viewpager_img2,R.drawable.viewpager_img3,R.drawable.viewpager_img4,R.drawable.viewpager_img5};
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflipper);
        initView();
        //注册手势

    }

    private void initView() {
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        gestureDetector = new GestureDetector(this);
        //创建ImageView，填充viewFlipper
        for(int i =0;i<images.length;i++) {
            ImageView imageView = new ImageView(ViewFlippperActivity.this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        if(viewFlipper.isAutoStart()&&!viewFlipper.isFlipping()){
            viewFlipper.startFlipping();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //点击事件一触发,停止自动滚动
        viewFlipper.stopFlipping();
        viewFlipper.setAutoStart(false);
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //向右滑动
        if(e2.getX()-e1.getX()>120){
            Animation InAnimation = AnimationUtils.loadAnimation(ViewFlippperActivity.this,R.anim.push_left_in);
            Animation OutAnimation = AnimationUtils.loadAnimation(ViewFlippperActivity.this,R.anim.push_right_out);

            viewFlipper.setInAnimation(InAnimation);
            viewFlipper.setOutAnimation(OutAnimation);
            viewFlipper.showPrevious();
            return true;
        }
        //向左滑动
        if(e2.getX()-e1.getX()<-120){
            Animation InAnimation = AnimationUtils.loadAnimation(ViewFlippperActivity.this,R.anim.push_right_in);
            Animation OutAnimation = AnimationUtils.loadAnimation(ViewFlippperActivity.this,R.anim.push_left_out);

            viewFlipper.setInAnimation(InAnimation);
            viewFlipper.setOutAnimation(OutAnimation);
            viewFlipper.showPrevious();
            return true;
        }
        return true;
    }
}

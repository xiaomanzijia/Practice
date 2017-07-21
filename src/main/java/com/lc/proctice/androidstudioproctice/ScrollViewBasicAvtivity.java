package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by licheng on 22/7/15.
 */
public class ScrollViewBasicAvtivity extends Activity implements ScrollViewListener{

    private ObservableScrollView scrollView1;
    private ObservableScrollView scrollView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        initView();
        scrollView1.setScrollViewListener(this);
        scrollView2.setScrollViewListener(this);
        scrollView1.setOnTouchListener(new MyTouchListener());
    }

    private void initView() {
        scrollView1 = (ObservableScrollView) findViewById(R.id.view1);
        scrollView2 = (ObservableScrollView) findViewById(R.id.view2);
    }

    private class MyTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                    //scrollview顶端滑出去的高度
                    int scrollY = v.getScrollY();
                    //scrollview的可见高度
                    int height = v.getHeight();
                    //scrollview内容的高度
                    int scrollviewMeasureHeight = scrollView1.getChildAt(0).getMeasuredHeight();
                    if(scrollY==0){
                        System.out.println("滑动到了顶端 view.getScrollY()="+scrollY);
                    }
                    if((scrollY+height)==scrollviewMeasureHeight){
                        System.out.println("滑动到了底部 scrollY="+scrollY);
                        System.out.println("滑动到了底部 height="+height);
                        System.out.println("滑动到了底部 scrollViewMeasuredHeight="+scrollviewMeasureHeight);
                    }
            }
            return false;
        }
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView==scrollView1){
            scrollView2.scrollTo(x,y);
        }else if(scrollView == scrollView2){
            scrollView1.scrollTo(x,y);
        }
    }
}

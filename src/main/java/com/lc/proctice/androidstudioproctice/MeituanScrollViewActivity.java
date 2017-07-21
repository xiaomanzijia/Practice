package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;


public class MeituanScrollViewActivity extends Activity implements MeituanScrollView.OnScrollListener{
    private MeituanScrollView scrollView;
    private LinearLayout mBuyLayout;
    private WindowManager windowManager;

    //手机屏幕宽度
    private int screenWidth;

    //悬浮框View
    private static View suspendView;

    //悬浮框的参数
    private static WindowManager.LayoutParams suspendViewParams;

    //购买布局的高度
    private int buyLayoutHeight;

    //myScrollView与其父布局的顶距离
    private int myScrollViewTop;

    //购买布局与其父布局的顶部距离
    private int buyLayoutTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meituantrick);

        scrollView  = (MeituanScrollView) findViewById(R.id.scrollView);
        mBuyLayout = (LinearLayout) findViewById(R.id.buy);

        scrollView.setScrollViewListener(this);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            buyLayoutHeight = mBuyLayout.getHeight();
            buyLayoutTop = mBuyLayout.getTop();

            myScrollViewTop = scrollView.getTop();
        }
    }

    @Override
    public void onScroll(int currentY) {
        if(currentY>=buyLayoutTop){
            if(suspendView == null){
                showSuspend();
            }
        }else  if (currentY < buyLayoutTop+buyLayoutHeight){
            if(suspendView!=null){
                hideSuspend();
            }
        }
    }

    private void hideSuspend() {
        if(suspendView == null){
            suspendView = LayoutInflater.from(this).inflate(R.layout.buy_layout, null);
            if(suspendViewParams == null){
                suspendViewParams = new WindowManager.LayoutParams();
                suspendViewParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                suspendViewParams.format = PixelFormat.RGBA_8888;
                suspendViewParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                suspendViewParams.gravity = Gravity.TOP;
                suspendViewParams.width = screenWidth;
                suspendViewParams.height = buyLayoutHeight;
                suspendViewParams.x = 0;
                suspendViewParams.y = myScrollViewTop;
            }
        }
        windowManager.addView(suspendView,suspendViewParams);
    }

    private void showSuspend() {
        if(suspendView != null){
            windowManager.removeView(suspendView);
            suspendView = null;
        }
    }
}

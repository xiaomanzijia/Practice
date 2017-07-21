package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;

public class TouchActivity extends Activity {
    private String TAG = "TouchActivity";
    final int RIGHT = 0;
    final int LEFT = 1;
    final int DOWN = 2;
    final int UP = 4;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gestureDetector = new GestureDetector(TouchActivity.this,onGestureListener);
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener(){
        //抛掷

        /**
         *
         * @param e1 手指第一次上屏幕的起点
         * @param e2 抬起手指离开屏幕的终点
         * @param velocityX X轴的每秒速度
         * @param velocityY Y轴的每秒速度
         * @return
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();

            if (x > 0) {
                doResult(RIGHT);
            } else if (x < 0) {
                doResult(LEFT);
            }
            if(y>0)
                doResult(DOWN);
            else if(y<0){
                doResult(UP);
            }
            return true;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void doResult(int action) {

        switch (action) {
            case RIGHT:
                System.out.println("go right");
                break;

            case LEFT:
                System.out.println("go left");
                break;

            case DOWN:
                System.out.println("go down");
                break;

            case UP:
                System.out.println("go up");
                break;

        }
    }

    //触摸事件
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action){
//            //按下
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG,"Action was DOWN");
//                return true;
//            //移动
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG,"Action was MOVE");
//                return true;
//            //抬起
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG,"Action was UP");
//                return true;
//            case MotionEvent.ACTION_CANCEL:
//                Log.d(TAG,"Action was CANCLE");
//                return true;
//            case MotionEvent.ACTION_OUTSIDE:
//                Log.d(TAG,"Action was OUTSIDE");
//                return true;
//            default:
//                return super.onTouchEvent(event);
//        }
//    }
}

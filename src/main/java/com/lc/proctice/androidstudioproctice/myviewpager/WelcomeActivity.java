package com.lc.proctice.androidstudioproctice.myviewpager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.lc.proctice.androidstudioproctice.DrawBasicPicShapeActitivty;
import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 19/8/15.
 */
public class WelcomeActivity extends Activity {
    private Boolean isFirstIn = false;
    private static final int HOME=1001;
    private static final int GUIDE=1002;
    private static final int TIME=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        init();
    }

    private void init() {
        SharedPreferences preference = getSharedPreferences("guide",MODE_PRIVATE);
        //第一次默认为true
        isFirstIn = preference.getBoolean("isFirstIn", true);

        if(!isFirstIn){
            handler.sendEmptyMessageDelayed(HOME,TIME);
        }else {
            handler.sendEmptyMessageDelayed(GUIDE,TIME);
            SharedPreferences.Editor editor = preference.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }
    }

    private void goHome(){
        Intent intent = new Intent(getApplicationContext(), DrawBasicPicShapeActitivty.class);
        startActivity(intent);
        finish();
    }

    private void goGuide(){
        Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HOME:
                    goHome();
                    break;
                case GUIDE:
                    goGuide();
                    break;
            }
        }
    };
}

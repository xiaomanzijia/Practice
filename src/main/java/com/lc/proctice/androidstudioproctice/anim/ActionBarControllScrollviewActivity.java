package com.lc.proctice.androidstudioproctice.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollView;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollViewCallbacks;
import com.lc.proctice.androidstudioproctice.anim.widget.ScrollState;

/**
 * Created by licheng on 15/6/16.
 */
public class ActionBarControllScrollviewActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbarcontrolscrollview);

        ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.scroll);
        scrollView.setScrollViewCallbacks(this);

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScoll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancleMotionEvent(ScrollState scrollState) {

        ActionBar actionBar = getSupportActionBar();

        if(actionBar == null){
            return;
        }

        if(scrollState == ScrollState.UP){
            if(actionBar.isShowing()){
                actionBar.hide();
            }
        }else if(scrollState == ScrollState.DOWN){
            if(!actionBar.isShowing()){
                actionBar.show();
            }
        }

    }
}

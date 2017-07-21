package com.lc.proctice.androidstudioproctice.myviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.lc.proctice.androidstudioproctice.DrawBasicPicShapeActitivty;
import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licheng on 19/8/15.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private GuideViewPagerAdapter adapter;
    private List<View> views;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3};
    private ImageView[] dots;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        initView();
        initDots();



    }

    private void initDots() {
        dots = new ImageView[views.size()];
        for(int i = 0;i<views.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one,null));
        views.add(inflater.inflate(R.layout.two,null));
        views.add(inflater.inflate(R.layout.three,null));
        adapter = new GuideViewPagerAdapter(this,views);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);

        btn_start = (Button) views.get(2).findViewById(R.id.start_btn);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DrawBasicPicShapeActitivty.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        for(int j = 0;j<views.size();j++){
            if(j==i){
                dots[j].setImageResource(R.drawable.login_point_selected);
            }else {
                dots[j].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        
    }
}

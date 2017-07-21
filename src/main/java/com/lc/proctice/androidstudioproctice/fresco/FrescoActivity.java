package com.lc.proctice.androidstudioproctice.fresco;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 22/7/16.
 */
public class FrescoActivity extends Activity {

    SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.imageView);

        /** 在ListView中使用会new大量对象 **/
//        new FrescoUtils.FrescoBuilder(simpleDraweeView, FrescoActivity.this)
//                .isCircle(true)
//                .setBackGroundImage(getDrawable(R.drawable.img2))
//                .build();

        /** 设置圆角 **/
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(10f);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300) //设置渐进时间
                .setPlaceholderImage(getDrawable(R.drawable.one)) //设置占位图
                .setBackground(getDrawable(R.drawable.img2)) //设置背景图
//                .setOverlay(getDrawable(R.drawable.welcome_android)) //设置叠加图
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP) //设置缩放类型
                .setRoundingParams(roundingParams)
                .build();

        FrescoInstanceUtils.getInstance().loadImage("http://pic1a.nipic.com/2008-12-01/2008121175139413_2.jpg", simpleDraweeView, hierarchy);


    }
}

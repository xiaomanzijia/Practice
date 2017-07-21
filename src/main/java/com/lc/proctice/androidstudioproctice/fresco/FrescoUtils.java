package com.lc.proctice.androidstudioproctice.fresco;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by licheng on 22/7/16.
 * fresco封装类
 */
public class FrescoUtils {

    private Context mContext;
    private SimpleDraweeView simpleDraweeView; //需要显示的图片控件

    private GenericDraweeHierarchyBuilder builder; //构造图片显示参数
    private GenericDraweeHierarchy hierarchy;

    private FrescoBuilder frescoBuilder;


    /** 图片设置参数 **/
    private Drawable placeHolderImage, //占位图
            backgroundImage; //背景图
    private ScalingUtils.ScaleType imageScaleType; //缩放类型
    private float radius; //圆角率
    private int fadeDuration; //渐进时间
    private boolean isCircle = false; //是否圆形图


    private FrescoUtils(FrescoBuilder frescoBuilder) {

        this.mContext = frescoBuilder.mContext;
        this.simpleDraweeView = frescoBuilder.simpleDraweeView;

        this.placeHolderImage = frescoBuilder.placeHolderImage;
        this.backgroundImage = frescoBuilder.backgroundImage;
        this.imageScaleType = frescoBuilder.imageScaleType;
        this.radius = frescoBuilder.radius;
        this.fadeDuration = frescoBuilder.fadeDuration;
        this.isCircle = frescoBuilder.isCircle;


        buildAll();

    }


    /** 最终调用方法 给图片设置所有配置 **/
    private void buildAll(){

        builder = new GenericDraweeHierarchyBuilder(mContext.getResources());

        hierarchy = builder.setPlaceholderImage(placeHolderImage)
                .setBackground(backgroundImage)
                .setActualImageScaleType(imageScaleType)
                .setFadeDuration(fadeDuration)
                .build();

        if(isCircle){
            RoundingParams roundingParams = RoundingParams.asCircle();
            hierarchy.setRoundingParams(roundingParams);
        }
        simpleDraweeView.setHierarchy(hierarchy);
    }

    /** Fresco参数构造器 **/
    public static class FrescoBuilder{

        private Context mContext;
        private SimpleDraweeView simpleDraweeView; //需要显示的图片控件

        private Drawable placeHolderImage, //占位图
                backgroundImage; //背景图
        private ScalingUtils.ScaleType imageScaleType; //缩放类型
        private float radius; //圆角率
        private int fadeDuration; //渐进时间
        private boolean isCircle = false; //是否圆形图

        public FrescoBuilder(SimpleDraweeView simpleDraweeView, Context mContext) {
            this.simpleDraweeView = simpleDraweeView;
            this.mContext = mContext;
        }

        public FrescoBuilder setPlaceHolderImage(Drawable placeHolderImage){
            this.placeHolderImage = placeHolderImage;
            return this;
        }

        public FrescoBuilder setBackGroundImage(Drawable backGroundImage){
            this.backgroundImage = backGroundImage;
            return this;
        }

        public FrescoBuilder setImageScaleType(ScalingUtils.ScaleType scaleType){
            this.imageScaleType = scaleType;
            return this;
        }

        public FrescoBuilder setRadius(float radius){
            this.radius = radius;
            return this;
        }

        public FrescoBuilder setFadeDuration(int fadeDuration){
            this.fadeDuration = fadeDuration;
            return this;
        }

        public FrescoBuilder isCircle(boolean isCircle){
            this.isCircle = isCircle;
            return this;
        }

        public FrescoUtils build(){
            return new FrescoUtils(this);
        }
    }

}

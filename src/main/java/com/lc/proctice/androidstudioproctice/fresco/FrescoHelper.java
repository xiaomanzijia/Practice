package com.lc.proctice.androidstudioproctice.fresco;

import android.net.Uri;
import android.support.annotation.DrawableRes;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by licheng on 22/7/16.
 */
public class FrescoHelper {

    /**
     * uri对应的图片在指定宽高在simpleDraweeView上显示
     * @param simpleDraweeView
     * @param uri
     * @return
     */
    public static void setImage(SimpleDraweeView simpleDraweeView, Uri uri) {

        ImageRequest request = ImageRequest.fromUri(uri);
        setImage(simpleDraweeView, request);
    }


    /**
     * uri对应的图片在指定宽高在simpleDraweeView上显示，options主要是用来设置大小，Fresco会找到最适应的图片大小
     * @param simpleDraweeView
     * @param uri
     * @param options
     */
    public static void setImage(SimpleDraweeView simpleDraweeView, Uri uri, ResizeOptions options) {

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(options)
                .setAutoRotateEnabled(true)
                .build();
        setImage(simpleDraweeView, request);
    }


    /**
     * 将ImageRequest请求返回的图片显示在simpleDraweeView上
     * @param simpleDraweeView
     * @param request
     */
    public static void setImage(SimpleDraweeView simpleDraweeView, ImageRequest request) {

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
        simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
    }



    /**
     * 给simpleDraweeView设置res下的资源图片
     * @param simpleDraweeView
     * @param resId 图片对应的resId
     */
    public static void setImageResource(SimpleDraweeView simpleDraweeView, @DrawableRes int resId) {

        ImageRequestBuilder imageRequest = ImageRequestBuilder.newBuilderWithResourceId(resId);
        simpleDraweeView.setImageURI(imageRequest.getSourceUri());
    }




    /**
     * 给photo的图片以半径为radius的圆角显示
     * @param photoView
     * @param radius
     */
    public static void setCornersRadius(SimpleDraweeView photoView, int radius) {

        GenericDraweeHierarchy hierarchy = photoView.getHierarchy();
        RoundingParams roundingParams = hierarchy.getRoundingParams();
        if (roundingParams == null) {

            roundingParams = new RoundingParams();
        }
        roundingParams.setCornersRadius(radius);
        hierarchy.setRoundingParams(roundingParams);
    }


}

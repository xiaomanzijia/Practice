package com.lc.proctice.androidstudioproctice.fresco;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

/**
 * Created by licheng on 22/7/16.
 */
public class FrescoInstanceUtils {


    private static FrescoInstanceUtils instance;

    private FrescoInstanceUtils() {
    }

    public synchronized static FrescoInstanceUtils getInstance(){
        if(instance == null){
            instance = new FrescoInstanceUtils();
        }
        return instance;
    }

    public void loadImage(String imageUrl, SimpleDraweeView imageView, GenericDraweeHierarchy hierarchy){
        ImageRequest request = ImageRequest.fromUri(imageUrl);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setOldController(imageView.getController())
                .build();
        imageView.setController(controller);
        imageView.setHierarchy(hierarchy);
    }

    public void loadImage(String imageUrl, SimpleDraweeView imageView){
        ImageRequest request = ImageRequest.fromUri(imageUrl);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setOldController(imageView.getController())
                .build();
        imageView.setController(controller);
    }

}

package com.lc.proctice.androidstudioproctice.anim;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 10/6/16.
 */
public abstract class BaseFragment extends Fragment {

    //获取actionBarSize
    protected int getActionBarSize(){
        Activity activity = getActivity();
        if(null == activity) return 0;
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        TypedArray typedArray = activity.obtainStyledAttributes(typedValue.data,textSizeAttr);
        int actionBarSize = typedArray.getDimensionPixelSize(0,-1);
        typedArray.recycle();
        return actionBarSize;
    }

    //获取屏幕高度
    protected int getScreenHeight() {
        Activity activity = getActivity();
        if (activity == null) {
            return 0;
        }
        return activity.findViewById(android.R.id.content).getHeight();
    }

}

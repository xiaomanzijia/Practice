package com.lc.proctice.androidstudioproctice.anim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollView;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollViewCallbacks;

/**
 * Created by licheng on 10/6/16.
 */
public class ViewPagerTabScrollViewFragment extends BaseFragment {

    public static final String ARG_SCROLL_Y = "ARG_SCROLL_Y";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrollview, container, false);
        final ObservableScrollView scrollView = (ObservableScrollView) view.findViewById(R.id.scroll);
        Activity parentActivity = getActivity();
        if(parentActivity instanceof ObservableScrollViewCallbacks){
            Bundle bundle = getArguments();
            if(null != bundle && bundle.containsKey(ARG_SCROLL_Y)){
                final int scrollY = bundle.getInt(ARG_SCROLL_Y,0);
                ScrollUtils.addOnGlobalLayoutListener(scrollView, new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0,scrollY);
                    }
                });
            }

            scrollView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.root));
            scrollView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
        }
        return view;
    }
}

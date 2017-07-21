package com.lc.proctice.androidstudioproctice.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollView;
import com.lc.proctice.androidstudioproctice.anim.widget.ObservableScrollViewCallbacks;
import com.lc.proctice.androidstudioproctice.anim.widget.ScrollState;
import com.lc.proctice.androidstudioproctice.anim.widget.SlidingTabLayout;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * Created by licheng on 10/6/16.
 */
public class ViewPagerTabScrollviewWithFabActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{

    private View mHeaderView,mToolbarView;
    private ViewPager mPager;
    private ScrollViewPagerAdapter pagerAdapter;

    private int mBaseTranslationY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagertab);
        //toolbar
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mHeaderView = findViewById(R.id.header);
        //给HeaderView设置阴影浮起4dp
        ViewCompat.setElevation(mHeaderView,getResources().getDimension(R.dimen.toolbar_elevation));
        mToolbarView = findViewById(R.id.toolbar);
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScrollViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accent));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);

        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                propagateToolbarState(toolbarIsShown());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        propagateToolbarState(toolbarIsShown());
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScoll, boolean dragging) {
        //headerView动画处理
        if(dragging){
            //toolbar高度
            int toolbarHeight = mToolbarView.getHeight();
            //headerView滑动高度
            float currentHeaderTranslationY = ViewHelper.getTranslationY(mHeaderView);

            Log.i("ViewPagerActivity",toolbarHeight + " " + currentHeaderTranslationY + " " + firstScoll);

            if (firstScoll) {
                if (-toolbarHeight < currentHeaderTranslationY) {
                    mBaseTranslationY = scrollY;
                }
            }

            Log.i("ViewPagerActivity",scrollY + " " + mBaseTranslationY);

            //headerView滑动距离
            float headerTranslationY = ScrollUtils.getFloat(-(scrollY - mBaseTranslationY), -toolbarHeight, 0);
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewHelper.setTranslationY(mHeaderView, headerTranslationY);

        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancleMotionEvent(ScrollState scrollState) {
        int toolbarHeight = mToolbarView.getHeight();
        Fragment fragment = pagerAdapter.getItemAt(mPager.getCurrentItem());
        View view = fragment.getView();
        final ObservableScrollView scrollView = (ObservableScrollView) view.findViewById(R.id.scroll);
        int scrollY = scrollView.getCurrentScrollY();
        if (scrollState == ScrollState.DOWN) {
            showToolbar();
        } else if (scrollState == ScrollState.UP) {
            if (toolbarHeight <= scrollY) {
                hideToolbar();
            } else {
                showToolbar();
            }
        } else {
            // Even if onScrollChanged occurs without scrollY changing, toolbar should be adjusted
            if (toolbarIsShown() || toolbarIsHidden()) {
                // Toolbar is completely moved, so just keep its state
                // and propagate it to other pages
                propagateToolbarState(toolbarIsShown());
            } else {
                // Toolbar is moving but doesn't know which to move:
                // you can change this to hideToolbar()
                showToolbar();
            }
        }
    }

    private boolean toolbarIsShown() {
        return ViewHelper.getTranslationY(mHeaderView) == 0;
    }

    private boolean toolbarIsHidden() {
        return ViewHelper.getTranslationY(mHeaderView) == -mToolbarView.getHeight();
    }

    private void showToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        if (headerTranslationY != 0) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(0).setDuration(200).start();
        }
        propagateToolbarState(true);
    }

    private void hideToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        int toolbarHeight = mToolbarView.getHeight();
        if (headerTranslationY != -toolbarHeight) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(-toolbarHeight).setDuration(200).start();
        }
        propagateToolbarState(false);
    }

    private void propagateToolbarState(boolean isShown) {
        int toolbarHeight = mToolbarView.getHeight();

        // Set scrollY for the fragments that are not created yet
        pagerAdapter.setScrollY(isShown ? 0 : toolbarHeight);

        // Set scrollY for the active fragments
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            // Skip current item
            if (i == mPager.getCurrentItem()) {
                continue;
            }

            // Skip destroyed or not created item
            Fragment f = pagerAdapter.getItemAt(i);
            if (f == null) {
                continue;
            }

            View view = f.getView();
            if (view == null) {
                continue;
            }
            ObservableScrollView scrollView = (ObservableScrollView) view.findViewById(R.id.scroll);
            if (isShown) {
                // Scroll up
                if (0 < scrollView.getCurrentScrollY()) {
                    scrollView.scrollTo(0, 0);
                }
            } else {
                // Scroll down (to hide padding)
                if (scrollView.getCurrentScrollY() < toolbarHeight) {
                    scrollView.scrollTo(0, toolbarHeight);
                }
            }
        }
    }
}

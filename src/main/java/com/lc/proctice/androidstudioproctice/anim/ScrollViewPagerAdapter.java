package com.lc.proctice.androidstudioproctice.anim;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by licheng on 10/6/16.
 */
public class ScrollViewPagerAdapter extends FragmentStatePagerAdapter {

    private int scrollY;

    private static final String[] TITLES = new String[]{"Applepie", "Butter Cookie", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop"};


    private SparseArray<Fragment> mPages;

    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

    public ScrollViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mPages = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ViewPagerTabScrollViewFragment();
        if (0 <= scrollY) {
            Bundle args = new Bundle();
            args.putInt(ViewPagerTabScrollViewFragment.ARG_SCROLL_Y, scrollY);
            fragment.setArguments(args);
        }
        mPages.put(position,fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(0 > mPages.indexOfKey(position)){
            mPages.remove(position);
        }
        super.destroyItem(container, position, object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    public Fragment getItemAt(int position) {
        return mPages.get(position);
    }
}

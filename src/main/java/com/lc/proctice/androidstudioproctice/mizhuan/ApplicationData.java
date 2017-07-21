package com.lc.proctice.androidstudioproctice.mizhuan;

import android.graphics.drawable.Drawable;

/**
 * Created by licheng on 15/8/15.
 */
public class ApplicationData {
    private String appName;
    private String packageName;
    private Drawable appicon;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getAppicon() {
        return appicon;
    }

    public void setAppicon(Drawable appicon) {
        this.appicon = appicon;
    }
}

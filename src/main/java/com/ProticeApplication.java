package com;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by licheng on 24/5/16.
 */
public class ProticeApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        ProticeApplication application = (ProticeApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Logger.init("LICHENGLOG");

        refWatcher = LeakCanary.install(this);

        Stetho.initializeWithDefaults(this);
    }


}

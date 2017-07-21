package com.lc.proctice.androidstudioproctice.mysurfaceview;

import android.app.Activity;
import android.os.Bundle;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 21/7/17.
 */

public class SurfaceActivity extends Activity {

    private CircleSurfaceView surfaceview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfaceview);
        surfaceview = (CircleSurfaceView) findViewById(R.id.surfaceview);
    }

}

package com.lc.proctice.androidstudioproctice.myview;

import android.app.Activity;
import android.os.Bundle;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 31/1/16.
 */
public class BlankActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_layout);
    }
}

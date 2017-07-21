package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by licheng on 11/6/16.
 */
public class WithAcitonONEActivity extends Activity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_activity_layout);
        text = (TextView) findViewById(R.id.textshow);
        text.setText("我是一个带有ACTIONONE的activity");
    }
}

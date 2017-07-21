package com.lc.proctice.androidstudioproctice.contact;

import android.app.Activity;
import android.os.Bundle;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 17/8/15.
 */
public class GetContactsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        GetContactNumber.getNumber(this);
    }
}

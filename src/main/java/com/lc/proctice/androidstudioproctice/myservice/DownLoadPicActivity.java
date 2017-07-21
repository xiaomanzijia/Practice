package com.lc.proctice.androidstudioproctice.myservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 18/8/15.
 */
public class DownLoadPicActivity extends Activity {
    Button btn_bindService,btn_startService,btn_download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservice);

        btn_bindService = (Button) findViewById(R.id.btn_bindService);
        btn_startService = (Button) findViewById(R.id.btn_startService);
        btn_download = (Button) findViewById(R.id.btn_download);

        btn_bindService.setVisibility(View.GONE);
        btn_startService.setVisibility(View.GONE);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent(getApplicationContext(),IntentSer.class);
                startService(service);
            }
        });
    }
}

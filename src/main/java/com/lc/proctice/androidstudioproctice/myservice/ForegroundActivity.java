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
public class ForegroundActivity extends Activity {
    Button btn_bindService,btn_startService,btn_download;
    private Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservice);

        btn_bindService = (Button) findViewById(R.id.btn_bindService);
        btn_startService = (Button) findViewById(R.id.btn_startService);
        btn_download = (Button) findViewById(R.id.btn_download);

        service = new Intent(getApplicationContext(),ForegroundSer.class);

        btn_bindService.setText("停止前台服务");
        btn_startService.setText("开启前台服务");
        btn_download.setVisibility(View.GONE);

        btn_startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(service);
            }
        });

        btn_bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(service);
            }
        });

    }
}

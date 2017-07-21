package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by licheng on 18/8/15.
 */
public class MyServiceActivity extends Activity {
    private Button btn_bindSerice,btn_startService;
    private static final String TAG = "MyServiceActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservice);
        btn_bindSerice = (Button) findViewById(R.id.btn_bindService);
        btn_startService = (Button) findViewById(R.id.btn_startService);

        btn_bindSerice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(MyService.ACTION),connection,BIND_AUTO_CREATE);
            }
        });

        btn_startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MyService.ACTION));
            }
        });

    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(TAG,"onServiceConnected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG,"onServiceDisconnected!");
        }
    };

    @Override
    protected void onDestroy() {
        Log.v(TAG,"onDestoryService!");
        unbindService(connection);
        super.onDestroy();
    }
}

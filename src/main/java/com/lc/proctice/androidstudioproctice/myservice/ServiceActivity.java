package com.lc.proctice.androidstudioproctice.myservice;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 18/8/15.
 */
public class ServiceActivity extends Activity {
    private static final String TAG = "ServiceActivity";
    Button btn_bindService,btn_stopbindService,btn_getServiceStatus;
    BindService.MyBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countnumberservice);

        btn_bindService = (Button) findViewById(R.id.btn_bindService);
        btn_stopbindService = (Button) findViewById(R.id.btn_stopbindService);
        btn_getServiceStatus = (Button) findViewById(R.id.getServiceStatus);

        final Intent intent = new Intent();
        intent.setAction("com.licheng.countnumberService");

        //绑定服务
        btn_bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, connection, Service.BIND_AUTO_CREATE);
            }
        });


        //获取计数
        btn_getServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binder!=null){
                    Toast.makeText(getApplicationContext(),binder.getCount()+"",Toast.LENGTH_SHORT).show();
                    Log.v(TAG,""+binder.getCount());
                }else {
                    Toast.makeText(getApplicationContext(),"请先绑定",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //停止绑定服务
        btn_stopbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder=null;
                unbindService(connection);
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "--Service Connected--");
            // 取得Service对象中的Binder对象
            binder = (BindService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "--Service Disconnected--");
        }
    };
}

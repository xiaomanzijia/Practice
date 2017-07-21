package com.lc.proctice.androidstudioproctice.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.lc.proctice.androidstudioproctice.R;

public class GridViewDemo extends Activity {
    private GridView gridView=null;
    private MyGridViewAdapter adapter=null;
    private MyGridViewAdapterWithText adapterWithText = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewmain);
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MyGridViewAdapter(getApplicationContext());
        adapterWithText = new MyGridViewAdapterWithText(getApplicationContext());

        gridView.setAdapter(adapterWithText);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"你点击了第"+(position+1)+"图片",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

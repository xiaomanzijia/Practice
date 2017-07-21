package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lc.proctice.androidstudioproctice.adapter.FirstAdapter;
import com.lc.proctice.androidstudioproctice.adapter.SecondAdapter;
import com.lc.proctice.androidstudioproctice.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by licheng on 21/4/16.
 */
public class CategoryActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listFirst;
    private ListView listLast;
    private FirstAdapter firstAdapter;
    private SecondAdapter secondAdapter;
    private ArrayList<Map<String,Object>> datalist;
    private final String TAG = "CategoryActivity";
    private ArrayList<String> arrayListFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listFirst = (ListView) findViewById(R.id.lv_first);
        listLast = (ListView) findViewById(R.id.lv_last);
        arrayListFirst = new ArrayList<>();
        String cName = CommonUtil.getData(CategoryActivity.this,"category.json");
        HashMap<String, Object> cMap = new Gson().fromJson(cName,HashMap.class);
        datalist = (ArrayList<Map<String, Object>>) cMap.get("data");
        for(Map<String,Object> map : datalist){
            String key = map.keySet().toString();
            arrayListFirst.add(key.substring(1,key.length() - 1));
        }
        firstAdapter = new FirstAdapter(datalist,CategoryActivity.this);
        listFirst.setAdapter(firstAdapter);
        listFirst.setOnItemClickListener(this);
        selectPosition(0);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectPosition(position);
    }

    private void selectPosition(int position){
        String key = arrayListFirst.get(position);
        ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) datalist.get(position).get(key);
        secondAdapter = new SecondAdapter(list,CategoryActivity.this,handler);
        listLast.setAdapter(secondAdapter);
        secondAdapter.notifyDataSetChanged();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String obj = (String) msg.obj;
                    Toast.makeText(CategoryActivity.this,obj,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

}

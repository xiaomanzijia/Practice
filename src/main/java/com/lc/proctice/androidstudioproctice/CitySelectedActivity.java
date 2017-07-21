package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lc.proctice.androidstudioproctice.dialog.SelectCityDialog;
import com.lc.proctice.androidstudioproctice.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by licheng on 21/4/16.
 */
public class CitySelectedActivity extends Activity implements View.OnClickListener {

    private LinearLayout llCity;
    private ArrayList<Map<String, Object>> districtList;
    private SelectCityDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_address);
        llCity = (LinearLayout) findViewById(R.id.ll_city);
        String districts = CommonUtil.getData(CitySelectedActivity.this,"ccity.json");
        Map<String,Object> map = new Gson().fromJson(districts,HashMap.class);
        districtList = (ArrayList<Map<String, Object>>) map.get("ccity");
        dialog = new SelectCityDialog(CitySelectedActivity.this,districtList);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_city:
                Toast.makeText(CitySelectedActivity.this,"选择城市",Toast.LENGTH_SHORT).show();
                dialog.show();
                break;
            default:
                break;
        }
    }
}

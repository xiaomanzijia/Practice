package com.lc.proctice.androidstudioproctice.shopcar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.adapter.ShopCartAdapter;
import com.lc.proctice.androidstudioproctice.utils.CommonUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by licheng on 22/4/16.
 */
public class ShopCarActivty extends Activity {

    private ListView listView;
    private LinearLayout llCheck;
    private LinearLayout llCd;
    private TextView tvPrice;
    private TextView tvPay;
    private TextView tvCount;
    private ShopCartAdapter adapter;
    private int count;
    private double price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcar);
        listView = (ListView) findViewById(R.id.listview);
        llCheck = (LinearLayout) findViewById(R.id.ll_check);
        llCd = (LinearLayout) findViewById(R.id.ll_cd);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvPay = (TextView) findViewById(R.id.tv_pay);
        tvCount = (TextView) findViewById(R.id.tv_count);
        String shopcar = CommonUtil.getData(ShopCarActivty.this,"shopcar.json");
        Map<String,Object> shopcarMap = new Gson().fromJson(shopcar, HashMap.class);
        ArrayList<Map<String,Object>> shopcarList = (ArrayList<Map<String, Object>>) shopcarMap.get("shopcar");
        adapter = new ShopCartAdapter(ShopCarActivty.this,shopcarList,handler);
        listView.setAdapter(adapter);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    double p = (double) msg.obj;
                    price += p;
                    DecimalFormat df = new DecimalFormat("#.##");
                    tvPrice.setText(df.format(price));
                    break;
                default:
                    break;
            }
        }
    };
}

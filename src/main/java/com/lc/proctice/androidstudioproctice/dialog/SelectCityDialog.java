package com.lc.proctice.androidstudioproctice.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.wheelview.widget.OnWheelChangedListener;
import com.lc.proctice.androidstudioproctice.wheelview.widget.WheelView;
import com.lc.proctice.androidstudioproctice.wheelview.widget.adapters.AbstractWheelTextAdapter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by licheng on 21/4/16.
 */
public class SelectCityDialog extends AlertDialog implements OnWheelChangedListener {

    private WheelView mViewProvince, mViewCity, mViewArea;
    private ArrayList<Map<String, Object>> districtList;// 装载的省市区
    protected ArrayList<Map<String,Object>> mProvinceDatas, mCityDatas,
            mAreaDatas; // 省市区数组
    private final String TAG = "SelectCityDialog";
    private Context mContext;


    public SelectCityDialog(Context context, ArrayList<Map<String, Object>> districtList) {
        super(context);
        this.mContext = context;
        this.districtList = districtList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_cities);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 这句话起全屏的作用
        getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);
        setCancelable(true);
        this.setCanceledOnTouchOutside(true);
        mViewProvince = (WheelView) findViewById(R.id.wv_country);
        mViewCity = (WheelView) findViewById(R.id.wv_city);
        mViewArea = (WheelView) findViewById(R.id.wv_area);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewArea.addChangingListener(this);

        mProvinceDatas = new ArrayList<>();
        mCityDatas = new ArrayList<>();
        mAreaDatas = new ArrayList<>();


        mViewProvince.setVisibleItems(5);
        mViewCity.setVisibleItems(5);
        mViewArea.setVisibleItems(5);

        for (Map<String,Object> map : districtList){
            mProvinceDatas.add((Map<String, Object>) map.get("province"));
        }

        ProvinceAdapter provinceAdapter = new ProvinceAdapter(mContext, mProvinceDatas);
        mViewProvince.setViewAdapter(provinceAdapter);
        mViewProvince.setCurrentItem(16);

    }

    private class ProvinceAdapter extends AbstractWheelTextAdapter {
        private ArrayList<Map<String, Object>> cList;

        protected ProvinceAdapter(Context context,
                                  ArrayList<Map<String, Object>> cList) {
            super(context, R.layout.country_layout, R.id.country_name);
            setItemTextResource(R.id.country_name);
            this.cList = cList;
        }

        @Override
        public int getItemsCount() {
            return cList.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return (CharSequence) cList.get(index).get("name");
        }
    }



    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        switch (wheel.getId()){
            case R.id.wv_country:
                ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) districtList.get(mViewProvince.getCurrentItem()).get("item");
                mCityDatas.clear();
                for(Map<String,Object> map : list){
                    mCityDatas.add((Map<String, Object>) map.get("city"));
                }

                ProvinceAdapter cityAdapter = new ProvinceAdapter(mContext,mCityDatas);
                mViewCity.setViewAdapter(cityAdapter);
                mViewCity.setCurrentItem(0);

                mAreaDatas = (ArrayList<Map<String, Object>>) list.get(0).get("area");
                ProvinceAdapter areaAdapter = new ProvinceAdapter(mContext,mAreaDatas);
                mViewArea.setViewAdapter(areaAdapter);
                mViewArea.setCurrentItem(0);

                break;
            case R.id.wv_city:
                ArrayList<Map<String,Object>> list1 = (ArrayList<Map<String, Object>>) districtList.get(mViewProvince.getCurrentItem()).get("item");
                mAreaDatas = (ArrayList<Map<String, Object>>) list1.get(mViewCity.getCurrentItem()).get("area");
                ProvinceAdapter areaAdapter1 = new ProvinceAdapter(mContext,mAreaDatas);
                mViewArea.setViewAdapter(areaAdapter1);
                mViewArea.setCurrentItem(0);
                break;
            case R.id.wv_area:
                break;
            default:
                break;
        }
    }
}

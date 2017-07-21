package com.lc.proctice.androidstudioproctice.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * Created by licheng on 22/4/16.
 */
public class ShopCartAdapter extends BaseAdapter {

    private ArrayList<Map<String,Object>> shopcarList;
    private Context mContext;
    private final static int TYPE_HEAD = 0;
    private final static int TYPE_ITEM = 1;
    private LayoutInflater inflater;
    private Set<Integer>  headPosSet; //记录TYPE_HEAD坐标位置

    private Vector<Boolean> sc; //记录选中的checBox状态

    Map<Integer,String> iteminfoMap;

    private Handler mHandler;

    public ShopCartAdapter(Context mContext, ArrayList<Map<String, Object>> shopcarList, Handler handler) {
        this.mContext = mContext;
        this.shopcarList = shopcarList;
        inflater = LayoutInflater.from(mContext);
        mHandler = handler;
        initCheckBoxFalse();
    }

    private void initCheckBoxFalse(){
        sc = new Vector<>();
        for (int i = 0; i < getCount(); i++) {
            sc.add(false);
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        if(shopcarList == null)
            return 0;
        for (Map<String,Object> map : shopcarList){
            ArrayList<Map<String,Object>> druginfolist = (ArrayList<Map<String, Object>>) map.get("druginfo");
            count += druginfolist.size() + 1;
        }
        return count;
    }

    @Override
    public Map<String,Object> getItem(int position) {
        int typeheadstart = 0;
        int index = 0;
        int typeheadPosition = 0;
        int itemPostion = 0;
        iteminfoMap = new HashMap<>();
        for (Map<String,Object> map : shopcarList){
            ArrayList<Map<String,Object>> druglist = (ArrayList<Map<String, Object>>) map.get("druginfo");
            int druglistSize = druglist.size();
            String a = typeheadstart+"-"+(typeheadstart + druglistSize);
            typeheadstart += druglistSize + 1;
            iteminfoMap.put(index,a);
            index ++;
        }
        Log.i("ShopCartAdapter",iteminfoMap.toString());
        for (Map.Entry<Integer,String> entry : iteminfoMap.entrySet()){
            String a = entry.getValue();
            String[] aa = a.split("-");
            int b = Integer.valueOf(aa[0]);
            int c = Integer.valueOf(aa[1]);
            if(position >= b && position <= c){
                typeheadPosition = entry.getKey();
                itemPostion = position - b - 1;
            }
        }
        if(headPosSet.contains(position)){
            return shopcarList.get(typeheadPosition);
        }else {
            ArrayList<Map<String,Object>> druglist = (ArrayList<Map<String, Object>>) shopcarList.get(typeheadPosition).get("druginfo");
            return druglist.get(itemPostion);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case TYPE_HEAD:
                Map<String,Object> headMap = getItem(position);
                ViewHolder1 viewHolder1;
                if(null == convertView){
                    convertView = inflater.inflate(R.layout.adapter_item_header,
                            null);
                    viewHolder1 = new ViewHolder1();
                    viewHolder1.tvHeader = (TextView) convertView
                            .findViewById(R.id.tv_header);
                    viewHolder1.llShopNext = (LinearLayout) convertView
                            .findViewById(R.id.ll_shop_next);

                    viewHolder1.ll_total = (LinearLayout) convertView
                            .findViewById(R.id.ll_total);
                    viewHolder1.tvGetCoubon = (TextView) convertView
                            .findViewById(R.id.tv_get_coubon);

                    convertView.setTag(viewHolder1);
                }else {
                    viewHolder1 = (ViewHolder1) convertView.getTag();
                }
                viewHolder1.ll_total.setSelected(sc.get(position));
                viewHolder1.ll_total.setTag(position);
                viewHolder1.ll_total.setOnClickListener(listener);
                viewHolder1.tvHeader.setText((String)headMap.get("shopname"));
                break;
            case TYPE_ITEM:
                Map<String,Object> itemMap = getItem(position);
                ViewHolder2 viewHolder2 = null;
                if(null == convertView){
                    viewHolder2 = new ViewHolder2();
                    convertView = inflater.inflate(
                            R.layout.adapter_item_content_new, null);
                    viewHolder2 = new ViewHolder2();
                    viewHolder2.content = (TextView) convertView
                            .findViewById(R.id.tv_shopname);
                    viewHolder2.tvShopPrice = (TextView) convertView
                            .findViewById(R.id.tv_shop_price);
                    viewHolder2.ibAdd = (ImageView) convertView
                            .findViewById(R.id.ib_add);
                    viewHolder2.ivReduce = (ImageView) convertView
                            .findViewById(R.id.ib_reduce);
                    viewHolder2.llAdreduce = (LinearLayout) convertView
                            .findViewById(R.id.ll_adreduce);
                    viewHolder2.tvCount = (TextView) convertView
                            .findViewById(R.id.tv_drug_count);
                    viewHolder2.llCheck = (LinearLayout) convertView
                            .findViewById(R.id.ll_check);
                    viewHolder2.rlGostore = (RelativeLayout) convertView
                            .findViewById(R.id.rl_gostore);
                    viewHolder2.ivImageview = (ImageView) convertView
                            .findViewById(R.id.iv_imageview);

                    convertView.setTag(viewHolder2);
                }else {
                    viewHolder2 = (ViewHolder2) convertView.getTag();
                }
                viewHolder2.llCheck.setSelected(sc.get(position));
                viewHolder2.llCheck.setTag(position);
                viewHolder2.llCheck.setOnClickListener(listener);
                viewHolder2.content.setText((String)itemMap.get("drugname"));
                viewHolder2.tvShopPrice.setText((String)itemMap.get("price"));
                viewHolder2.tvCount.setText((String)itemMap.get("num"));
                break;
            default:
                break;
        }
        return convertView;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        int position = 0;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_check:
                    position = (int) v.getTag();
                    setSingleCheck(position);
                    break;
                case R.id.ll_total:
                    position = (int) v.getTag();
                    int groupIndex = 0;
                    //判断position属于哪一个group
                    for (Map.Entry<Integer,String> map : iteminfoMap.entrySet()){
                        String[] aa = map.getValue().split("-");
                        int a = Integer.valueOf(aa[0]);
                        int b = Integer.valueOf(aa[1]);
                        if(position >= a && position <= b){
                            groupIndex = map.getKey();
                        }
                    }
                    setGroup(groupIndex,position);
                    break;
                default:
                    break;
            }
        }
    };

    //选中购物车分组
    private void setGroup(int groupIndex, int position) {
        if(sc.get(position) == false){
            sc.setElementAt(true,position);
            setGroupSingleCheckStatus(groupIndex,true);
        }else {
            sc.setElementAt(false,position);
            setGroupSingleCheckStatus(groupIndex,false);
        }
        notifyDataSetChanged();
    }

    //计算商品价格
    private double price(int position){
        int groupId = 0;
        int itemPostion = 0;

        for (Map.Entry<Integer,String> entry : iteminfoMap.entrySet()){
            String a = entry.getValue();
            String[] aa = a.split("-");
            int b = Integer.valueOf(aa[0]);
            int c = Integer.valueOf(aa[1]);
            if(position >= b && position <= c){
                itemPostion = position - b - 1;
            }
        }
        //获取价格
        //判断position属于哪一个group
        for (Map.Entry<Integer,String> map : iteminfoMap.entrySet()){
            String[] aa = map.getValue().split("-");
            int a = Integer.valueOf(aa[0]);
            int b = Integer.valueOf(aa[1]);
            if(position >= a && position <= b){
                groupId = map.getKey();
            }
        }
        ArrayList<Map<String,Object>> druglist = (ArrayList<Map<String, Object>>) shopcarList.get(groupId).get("druginfo");
        Map<String,Object> map1 = druglist.get(itemPostion);
        double price = Double.valueOf(map1.get("price").toString());
        int num = Integer.valueOf(map1.get("num").toString());
        double priceall = num * price;
        return priceall;
    }

    //商品单选
    private void setSingleCheck(int position) {
        if(sc.get(position) == false){
            sc.setElementAt(true,position);
            Message msg = new Message();
            msg.what = 1;
            msg.obj = price(position);
            mHandler.sendMessage(msg);
        }else {
            sc.setElementAt(false,position);
            Message msg = new Message();
            msg.what = 1;
            msg.obj = - price(position);
            mHandler.sendMessage(msg);
        }
        Set set = new HashSet();
        int groupPosition = 0;
        //检测该组item是否全选或不全选状态
        for (Map.Entry<Integer,String> map : iteminfoMap.entrySet()){
            String[] aa = map.getValue().split("-");
            int a = Integer.valueOf(aa[0]);
            int b = Integer.valueOf(aa[1]);
            if(position >= a && position <= b){
                groupPosition = a;
                for (int i = a + 1; i < b + 1; i++) {
                    set.add(sc.get(i).booleanValue());
                }
            }
        }
        if(set.size() == 1){
            if(set.contains(true)){
                sc.setElementAt(true,groupPosition);
            }else {
                sc.setElementAt(false,groupPosition);
            }
        }else {
            sc.setElementAt(false,groupPosition);
        }
        notifyDataSetChanged();
    }

    //设置group下的所有item状态
    private void setGroupSingleCheckStatus(int position,boolean flag){
        Log.i("setGroup",position+"   " +iteminfoMap.toString());
        for (Map.Entry<Integer,String> map : iteminfoMap.entrySet()){
            int groupPosition = map.getKey();
            double priceall = 0;
            String childArray = map.getValue();
            String[] aa = childArray.split("-");
            int a = Integer.valueOf(aa[0]);
            int b = Integer.valueOf(aa[1]);
            if(position == groupPosition){
                if(flag){
                    for (int i = a + 1; i < b + 1; i++) {
                        if(sc.get(i) == false){
                            ArrayList<Map<String, Object>> druglist = (ArrayList<Map<String, Object>>) shopcarList.get(position).get("druginfo");
                            Map<String, Object> map1 = druglist.get(i - a - 1);
                            double price = Double.valueOf(map1.get("price").toString());
                            int count = Integer.valueOf(map1.get("num").toString());
                            priceall += price * count;
                        }
                        sc.setElementAt(flag, i);
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = priceall;
                    mHandler.sendMessage(msg);
                }else {
                    for (int i = a + 1; i < b + 1; i++) {
                        sc.setElementAt(flag, i);
                        ArrayList<Map<String, Object>> druglist = (ArrayList<Map<String, Object>>) shopcarList.get(position).get("druginfo");
                        Map<String, Object> map1 = druglist.get(i - a - 1);
                        double price = Double.valueOf(map1.get("price").toString());
                        int count = Integer.valueOf(map1.get("num").toString());
                        priceall += price * count;
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = - priceall;
                    mHandler.sendMessage(msg);
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int headItemPosition = 0; //记录TYPE_HEAD位置坐标
        headPosSet = new HashSet<>();
        for (Map<String,Object> map : shopcarList){
            ArrayList<Map<String,Object>> druginfolist = (ArrayList<Map<String, Object>>) map.get("druginfo");
            int count = druginfolist.size() + 1;
            headPosSet.add(headItemPosition);
            headItemPosition += count;
        }
        Log.i("ShopCartAdapter",headPosSet.toString());
        return headPosSet.contains(position) ? TYPE_HEAD : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolder1 {
        public LinearLayout llShopNext;
        public TextView tvGetCoubon;
        private TextView tvHeader;
        private LinearLayout ll_total;

    }

    class ViewHolder2 {
        public LinearLayout llCheck;
        private TextView content;
        private TextView tvShopPrice;
        private ImageView ibAdd;
        private TextView tvDrugcount;
        private ImageView ivReduce;
        private LinearLayout llAdreduce;
        private TextView tvCount;
        private RelativeLayout rlGostore;
        private ImageView ivImageview;
    }
}

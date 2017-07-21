package com.lc.proctice.androidstudioproctice.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.myview.MyGridView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by licheng on 21/4/16.
 */
public class SecondAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Map<String,Object>> list;
    protected LayoutInflater inflate;
    private Handler handler;

    public SecondAdapter(ArrayList<Map<String,Object>> list, Context mContext, Handler handler) {
        this.list = list;
        this.mContext = mContext;
        inflate = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vHodler = null;
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.adapter_category_gridview,
                    null);
            vHodler = new ViewHodler();
            vHodler.mgvCategory = (MyGridView) convertView
                    .findViewById(R.id.mgv_category);
            vHodler.tvSecond = (TextView) convertView
                    .findViewById(R.id.tv_second);
            vHodler.mgvAdpter = new MgvAdapter(mContext);
            convertView.setTag(vHodler);
        } else {
            vHodler = (ViewHodler) convertView.getTag();
        }
        String second = list.get(position).keySet().toString();
        vHodler.tvSecond.setText(second.substring(1,second.length() - 1));
        ArrayList<String> listSecond = (ArrayList<String>) list.get(position).get(second.substring(1,second.length() - 1));
        vHodler.mgvAdpter.setDate(listSecond);
        vHodler.mgvAdpter.notifyDataSetChanged();
        vHodler.mgvCategory.setAdapter(vHodler.mgvAdpter);
        vHodler.mgvCategory.setOnItemClickListener(new ItemClick(listSecond));
        return convertView;
    }

    class ViewHodler {
        public MgvAdapter mgvAdpter;
        private MyGridView mgvCategory;
        private TextView tvSecond;
    }

    class ItemClick implements AdapterView.OnItemClickListener{

        private ArrayList<String> list;

        public ItemClick(ArrayList<String> list) {
            this.list = list;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Message msg = new Message();
            msg.what = 0;
            msg.obj = list.get(position);
            handler.sendMessage(msg);
        }
    }
}

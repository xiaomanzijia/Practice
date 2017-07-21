package com.lc.proctice.androidstudioproctice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by licheng on 21/4/16.
 */
public class FirstAdapter extends BaseAdapter {

    private ArrayList<Map<String,Object>> list;
    private Context mContext;
    protected LayoutInflater inflate;

    public FirstAdapter(ArrayList<Map<String, Object>> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflate = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (v == null) {
            v = inflate.inflate(R.layout.adapter_item_category, null);
            viewHolder = new ViewHolder();
            viewHolder.ivLine = (ImageView) v.findViewById(R.id.iv_line);
            viewHolder.tvText = (TextView) v.findViewById(R.id.tv_text);
            viewHolder.rlText = (RelativeLayout) v.findViewById(R.id.rl_text);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tvText.setText(list.get(position).keySet().toString());
        return v;
    }

    class ViewHolder {
        private RelativeLayout rlText;
        private TextView tvText;
        private ImageView ivLine;
    }
}

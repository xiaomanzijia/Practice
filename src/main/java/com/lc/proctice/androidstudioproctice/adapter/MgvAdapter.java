package com.lc.proctice.androidstudioproctice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;
import java.util.List;

public class MgvAdapter extends BaseAdapter {
	private Context context;
	private List<String> mItemlist;

	private LayoutInflater inflate;

	public MgvAdapter(Context context) {
		super();
		this.context = context;
		this.inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItemlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoder viewHoder;
		if (convertView == null) {
			viewHoder = new ViewHoder();
			convertView = inflate.inflate(R.layout.adapter_grid_view, null);
			viewHoder.tvgridview = (TextView) convertView
					.findViewById(R.id.tv_gridview);
			convertView.setTag(viewHoder);
		} else {
			viewHoder = (ViewHoder) convertView.getTag();
		}
		viewHoder.tvgridview.setText(mItemlist.get(position));
		return convertView;
	}

	class ViewHoder {
		public TextView tvgridview;
	}

	public void setDate(ArrayList<String> itemList) {
		this.mItemlist = itemList;
	}

}

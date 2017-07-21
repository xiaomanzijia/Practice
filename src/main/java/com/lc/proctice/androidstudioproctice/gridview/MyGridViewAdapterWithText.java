package com.lc.proctice.androidstudioproctice.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 30/7/15.
 */
public class MyGridViewAdapterWithText extends BaseAdapter {
    private Context context;

    public MyGridViewAdapterWithText(Context context) {
        this.context = context;
    }


    private int[] images={R.drawable.img1, R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,
            R.drawable.img7,R.drawable.img8,R.drawable.img9};

    private String[] texts = {"pic1","pic2","pic3","pic4","pic5","pic6","pic7","pic8","pic9"};

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridviewitem, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(texts[position]);
        viewHolder.imageView.setImageResource(images[position]);

        return convertView;
    }




    class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }
}

package com.lc.proctice.androidstudioproctice.recyleview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licheng on 16/1/16.
 */
public class RecyleViewActivity extends Activity {
    private RecyclerView  recyclerView;
    private List<String> mDatas;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyleviwe_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyleview);

        initData();

        recyclerView.setAdapter(adapter = new HomeAdapter());
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyleViewActivity.this));

    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add(i+"");
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHodler>{


        @Override
        public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHodler hodler = new MyViewHodler(LayoutInflater.from(RecyleViewActivity.this)
                    .inflate(R.layout.recyleview_item_layout,parent,false));
            return hodler;
        }

        @Override
        public void onBindViewHolder(MyViewHodler holder, int position) {
            holder.textView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHodler extends RecyclerView.ViewHolder{
            TextView textView;

            public MyViewHodler(View itemView) {
                super(itemView);
                //注意textView来自itemView 不然会报NULL
                textView = (TextView) itemView.findViewById(R.id.textRecyleview);
            }
        }
    }
}

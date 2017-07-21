package com.lc.proctice.androidstudioproctice.myview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;
import com.lc.proctice.androidstudioproctice.myview.mygroup.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licheng on 1/8/16.
 */
public class HorizontalScrollviewActivity extends Activity {

    HorizontalScrollView mHorizontalScrollView;

    private DisplayMetrics dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontalscrollview);
        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalscrollview);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        List<String> dataList = new ArrayList<>();
        for (int j = 0; j < 30; j++) {
            dataList.add("item" + j);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HorizontalScrollviewActivity.this,
                R.layout.content_list_item, R.id.name, dataList);

        /** 创建三个ListView添加到容器中 **/
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < 3; i++) {

            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.content_layout, mHorizontalScrollView, false);
            viewGroup.getLayoutParams().width = dm.widthPixels;

            ListView listview = (ListView) viewGroup.findViewById(R.id.list);
            TextView textview = (TextView) viewGroup.findViewById(R.id.title);
            textview.setText("list" + i);

            listview.setAdapter(adapter);
            mHorizontalScrollView.addView(viewGroup);
        }
    }
}

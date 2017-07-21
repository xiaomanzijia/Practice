package com.lc.proctice.androidstudioproctice.myview;

import android.app.Activity;
import android.os.Bundle;

import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by licheng on 26/7/16.
 */
public class PieViewActivity extends Activity {

    private PieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        pieView = (PieView) findViewById(R.id.pieView);


        List<PieData> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int value = random.nextInt(50);
            PieData pie = new PieData("扇形" + i, Float.valueOf(value));
            dataList.add(pie);
        }
        pieView.setmStartAngle(15f);
        pieView.setDataList(dataList);



    }
}

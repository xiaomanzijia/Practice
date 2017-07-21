package com.lc.proctice.androidstudioproctice.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import com.lc.proctice.androidstudioproctice.R;

import java.io.File;

/**
 * Created by licheng on 24/8/15.
 */
public class ImageShowActivity extends Activity {
    private ImageView imgshow;
    private String path = Environment.getExternalStorageDirectory().getPath()+"/licheng/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgshow);

        imgshow = (ImageView) findViewById(R.id.imgshow);



        imgshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                startActivityForResult(intent, 110);

            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        File file = new File(path+"img.jpg");

        if(file.exists()&&file.length()>0){
            String name = path + "img.jpg";
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inSampleSize = 4;
            Bitmap bm = BitmapFactory.decodeFile(name,option);
            imgshow.setImageBitmap(bm);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==110){


        }
    }
}

package com.lc.proctice.androidstudioproctice.photo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import com.lc.proctice.androidstudioproctice.R;

import java.io.*;

/**
 * Created by licheng on 24/8/15.
 */
public class PhotoActivity extends Activity {
    private TextView takePhoto,photoAlbum,cancel;
    private String path = Environment.getExternalStorageDirectory().getPath()+"/licheng/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        initView();

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            }
        });

        photoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        takePhoto = (TextView)this.findViewById(R.id.tab3_take_pictures);
        photoAlbum = (TextView)this.findViewById(R.id.tab3_photo_album);
        cancel = (TextView)this.findViewById(R.id.tab3_photo_cancel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                ContentResolver resolver = getContentResolver();
                try {
                    InputStream input = resolver.openInputStream(data.getData());BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inPreferredConfig = Bitmap.Config.RGB_565;
                    opt.inPurgeable = true;
                    opt.inInputShareable = true;
                    //从设备中获取照片
                    Bitmap bitmap = BitmapFactory.decodeStream(input, null, opt);
                    //将照片写进文件夹
                    setPicToView(bitmap);
                    finish();

//                    File file = new File(path+"img.jpg");
//
//                    if(file.exists()&&file.length()>0){
//                        Intent intent = new Intent();
//                        intent.putExtra("photo",file);
//                        setResult(111, intent);
//                        finish();
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                Bitmap bitmap = BitmapFactory.decodeFile(new File(Environment.getExternalStorageDirectory(), "img.jpg").getAbsolutePath());
                break;
            default:
                break;
        }
    }

    //将照片写进创建的文件夹
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path  + "img.jpg";//图片名字


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//个人喜欢从80开始,
        mBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 2000 && options > 0) {
            baos.reset();
            options -= 10;
            mBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

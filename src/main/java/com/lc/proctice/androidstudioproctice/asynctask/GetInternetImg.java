package com.lc.proctice.androidstudioproctice.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by licheng on 14/10/15.
 */
public class GetInternetImg extends AsyncTask<String,Integer,Bitmap> {
    private ProgressBar mProgressBar;
    private Context mContext;
    private TextView mTextView;
    private ImageView mImageView;
    private String urlstr;

    public GetInternetImg(Context context, ProgressBar progressBar, TextView textView, ImageView imageView) {
        this.mContext = context;
        this.mProgressBar = progressBar;
        this.mTextView = textView;
        this.mImageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTextView.setText("下载图片");
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        // TODO Auto-generated method stub
        URL myFileUrl = null;
        Bitmap bitmap = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            myFileUrl = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection)myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            is =conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);

            byte[] data = new byte[1024];
            int seg = 0;
            long total = conn.getContentLength();
            long current = 0;
            while (!isCancelled()&&(seg = is.read(data)) != -1){
                current += seg;
                int progress = (int) ((long)current/total*100);
                publishProgress(progress);

                SystemClock.sleep(1000);
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(is != null){
                    is.close();
                }
                if( conn != null){
                    conn.disconnect();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.setProgress(values[0]);
        mTextView.setText(values[0] + "%");
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
        mProgressBar.setVisibility(View.GONE);
        mTextView.setText("下载完成");
        super.onPostExecute(bitmap);

    }
}
package com.lc.proctice.androidstudioproctice.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lc.proctice.androidstudioproctice.R;

import java.util.Random;

/**
 * Created by licheng on 23/7/16.
 */
public class MyDialogActivity extends Activity {


    Button btnOpenDialog;
    private ProgressDialog progressDialog1;
    private ProgressThread progressThread;

    private final static int DIALOG_ONE = 111;
    private final static int DIALOG_TWO = 112;
    private final static int DIALOG_THREE = 113;
    private final static int DIALOG_FOUR = 114;
    private final static int DIALOG_FIVE = 115;
    private final static int DIALOG_SIX = 116;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);
        btnOpenDialog = (Button) findViewById(R.id.btnOpenDialog);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int ran = random.nextInt(6) + 111;
                showDialog(ran);
            }
        });
    }






    protected Dialog onCreateDialog(int id){

        Dialog dialog = null;

        switch (id){
            case DIALOG_ONE:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定取消吗？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MyDialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                dialog = builder.create();
                break;
            case DIALOG_TWO:

                final CharSequence[] items = {"red","yellow","blue","white","black"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("请选择颜色")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MyDialogActivity.this,items[which].toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog = builder1.create();
                break;
            case DIALOG_THREE:
                final CharSequence[] itemss = {"red","yellow","blue","white","black"};
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("请选择颜色")
                        .setSingleChoiceItems(itemss, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MyDialogActivity.this,itemss[which].toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog = builder2.create();
                break;
            case DIALOG_FOUR:
                ProgressDialog progressDialog = ProgressDialog.show(MyDialogActivity.this, "", "加载中...", true);
                progressDialog.setCancelable(true);
                return progressDialog;
            case DIALOG_FIVE:
                progressDialog1 = new ProgressDialog(this);
                progressDialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog1.setMessage("加载中...");
                progressDialog1.setCancelable(true);
                return progressDialog1;
            case DIALOG_SIX:
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_notice, (ViewGroup) findViewById(R.id.layout_group),false);
                TextView textView = (TextView) view.findViewById(R.id.textNotice);
                textView.setText("自定义Dialog");
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

                builder3.setView(view);
                dialog = builder3.create();
                break;
            default:
                dialog = null;
                break;
        }

        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id){
            case DIALOG_FIVE:
                progressDialog1.setProgress(0);
                progressThread = new ProgressThread(handler);
                progressThread.start();
                break;
        }
    }

//    final Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            int total = msg.arg1;
//            progressDialog1.setProgress(total);
//            if (total >= 100){
//                dismissDialog(DIALOG_FIVE);
//                progressThread.setState(ProgressThread.STATE_DONE);
//            }
//        }
//    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int progress = msg.what;
            progressDialog1.setProgress(progress);
            if(progress >= 100){
                dismissDialog(DIALOG_FIVE);
                progressThread.setRuning(false);
            }
        }
    };


//    /** Nested class that performs progress calculations (counting) */
//    private class ProgressThread extends Thread {
//        Handler mHandler;
//        final static int STATE_DONE = 0;
//        final static int STATE_RUNNING = 1;
//        int mState;
//        int total;
//
//        ProgressThread(Handler h) {
//            mHandler = h;
//        }
//
//        public void run() {
//            mState = STATE_RUNNING;
//            total = 0;
//            while (mState == STATE_RUNNING) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    Log.e("ERROR", "Thread Interrupted");
//                }
//                Message msg = mHandler.obtainMessage();
//                msg.arg1 = total;
//                mHandler.sendMessage(msg);
//                total++;
//            }
//        }
//
//        /* sets the current state for the thread,
//         * used to stop the thread */
//        public void setState(int state) {
//            mState = state;
//        }
//    }

    private class ProgressThread extends Thread{

        private Handler mHandler;
        private int progress;
        private boolean isRuning = false;

        ProgressThread(Handler handler){
            mHandler = handler;
        }

        @Override
        public void run() {

            isRuning = true;

            while (isRuning){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = mHandler.obtainMessage();
                msg.what = progress;
                mHandler.sendMessage(msg);

                progress ++;
            }

        }

        public void setRuning(boolean runing) {
            isRuning = runing;
        }
    }



}

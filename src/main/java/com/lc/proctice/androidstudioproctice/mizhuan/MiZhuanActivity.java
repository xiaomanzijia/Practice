package com.lc.proctice.androidstudioproctice.mizhuan;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licheng on 15/8/15.
 */
public class MiZhuanActivity extends Activity implements AdapterView.OnItemClickListener{
    ArrayAdapter<String> adapter;
    ListView applicationList;
    private ArrayList<ApplicationData> applicationDatas;
    PackageManager packageManager;
    private static String TAG ="MiZhuanActivity";
    TextView tv_counttimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mizhuan);
        applicationList = (ListView) findViewById(R.id.application_list);
        tv_counttimer = (TextView) findViewById(R.id.tv_counttimer);
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1);
        adapter.add("12306");
        applicationList.setAdapter(adapter);
        applicationList.setOnItemClickListener(this);

        for(int i = 0;i<getMobilePackageName().size();i++){
            Log.d(TAG,"应用名称："+getMobilePackageName().get(i).getAppName()+" 应用包名："+getMobilePackageName().get(i).getPackageName());
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(isAvilible("com.jhlc.txwl")){
            Intent intent = packageManager.getLaunchIntentForPackage("com.jhlc.txwl");
            startActivity(intent);
        }else {
            Intent intent = new Intent();
            intent.setData(Uri.parse("https://kyfw.12306.cn/otn/appDownload/init"));
            intent.setAction(intent.ACTION_VIEW);
            startActivity(intent);
        }

        if(isRuning("com.jhlc.txwl")){
            Toast.makeText(getApplicationContext(),"天下无赖正在前台运行",Toast.LENGTH_SHORT).show();
            //倒计时开始
            timer.start();
        }else {
            Toast.makeText(getApplicationContext(),"天下无赖退出前台运行",Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        if(isTopActivity("com.jhlc.txwl")){
//            Toast.makeText(getApplicationContext(),"天下无赖正在前台运行",Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(getApplicationContext(),"天下无赖退出前台运行",Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //扫描当前设备已安装应用包名
    private ArrayList<ApplicationData> getMobilePackageName(){
        packageManager = getPackageManager();
        applicationDatas = new ArrayList<ApplicationData>();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for(PackageInfo packs : packageInfos){
            if((packs.applicationInfo.flags& ApplicationInfo.FLAG_SYSTEM)==0){
                ApplicationData applicationData = new ApplicationData();
                applicationData.setAppName(packs.applicationInfo.loadLabel(packageManager).toString());
                applicationData.setPackageName(packs.packageName);
                applicationDatas.add(applicationData);
            }
        }
        return applicationDatas;
    }

    //检测是否安装了应用
    private boolean isAvilible(String packageName){
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for(int i = 0;i<packageInfos.size();i++){
            if(packageInfos.get(i).packageName.equals(packageName))
                return true;
        }
        return false;
    }

    //判断应用是否在前台运行
    private boolean isRuning(String packageName){
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(100);
        for(ActivityManager.RunningTaskInfo info : runningTaskInfos){
            if(info.topActivity.getPackageName().equals(packageName)){
                return true;
            }
        }
        return false;
    }

    private boolean isTopActivity(String packageName){
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo>  tasksInfo = activityManager.getRunningTasks(1);
        if(tasksInfo.size() > 0){
            //应用程序位于堆栈的顶层
            if(packageName.equals(tasksInfo.get(0).topActivity.getPackageName())){
                return true;
            }
        }
        return false;
    }

    //体验应用倒计时
    CountDownTimer timer = new CountDownTimer(120000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_counttimer.setText("您还需体验："+millisUntilFinished/1000+"秒，再提交申请！");
        }

        @Override
        public void onFinish() {
            tv_counttimer.setText("倒计时结束，您可以提交申请啦！");
        }
    };
}

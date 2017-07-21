package com.lc.proctice.androidstudioproctice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.anim.ActionBarControllScrollviewActivity;
import com.lc.proctice.androidstudioproctice.anim.FlexibleSpaceActionBarScrollviewActiivty;
import com.lc.proctice.androidstudioproctice.anim.FlexibleSpaceWithImageScrollViewActivity;
import com.lc.proctice.androidstudioproctice.anim.ViewPagerTabScrollviewWithFabActivity;
import com.lc.proctice.androidstudioproctice.asynctask.AsyncTaskActivity;
import com.lc.proctice.androidstudioproctice.asynctask.TestDriverActivity;
import com.lc.proctice.androidstudioproctice.greendao.BookActivity;
import com.lc.proctice.androidstudioproctice.greendao.NoteActivity;
import com.lc.proctice.androidstudioproctice.greendao.StudentActivity;
import com.lc.proctice.androidstudioproctice.gridview.GridViewDemo;
import com.lc.proctice.androidstudioproctice.httpclient.HttpURLConnectionActivity;
import com.lc.proctice.androidstudioproctice.indexablelistview.IndexableListViewActivity;
import com.lc.proctice.androidstudioproctice.myservice.DownLoadPicActivity;
import com.lc.proctice.androidstudioproctice.myservice.ForegroundActivity;
import com.lc.proctice.androidstudioproctice.myservice.MessengerActivity;
import com.lc.proctice.androidstudioproctice.myservice.ServiceActivity;
import com.lc.proctice.androidstudioproctice.mysurfaceview.SurfaceActivity;
import com.lc.proctice.androidstudioproctice.myview.BlankActivity;
import com.lc.proctice.androidstudioproctice.myview.FlowLayout;
import com.lc.proctice.androidstudioproctice.myview.FlowLayoutActivity;
import com.lc.proctice.androidstudioproctice.myview.HorizontalScrollviewActivity;
import com.lc.proctice.androidstudioproctice.myview.MyTextViewActivity;
import com.lc.proctice.androidstudioproctice.myview.PieView;
import com.lc.proctice.androidstudioproctice.myview.PieViewActivity;
import com.lc.proctice.androidstudioproctice.myview.gesturelock.SettingGestureLockActivity;
import com.lc.proctice.androidstudioproctice.myview.liziyu.LiZiYuActicity;
import com.lc.proctice.androidstudioproctice.myview.viewmeasure.MyTextView;
import com.lc.proctice.androidstudioproctice.recyleview.RecyleViewActivity;
import com.lc.proctice.androidstudioproctice.rxandroid.Example1Activity;
import com.lc.proctice.androidstudioproctice.rxandroid.Example2Activity;
import com.lc.proctice.androidstudioproctice.rxandroid.Example3Activity;
import com.lc.proctice.androidstudioproctice.rxandroid.Example4Activity;
import com.lc.proctice.androidstudioproctice.screen.MergeOptimizeActivity;
import com.licheng.duotai.InstrumentTest;

import me.student.greendao.Student;


/**
 * Created by licheng on 22/7/15.
 */
public class ListActivity extends Activity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private int position=0;
    private final String TAG = "ListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();

        switch (position){
            case 0:
                adapter.clear();
                adapter.add("Activity生命周期");
                adapter.add("Service生命周期");
                adapter.add("用Service作为后台实现计数");
                adapter.add("Service:从网络下载一张图片");
                adapter.add("Messenger进程间通信");
                adapter.add("前台服务");
                break;
            case 1:
                adapter.clear();
                adapter.add("绘制基本图形");
                adapter.add("自定义TextView");
                adapter.add("自定义ViewGroup");
                adapter.add("自定义方形条粒子雨效果");
                adapter.add("手势解锁");
                adapter.add("RecycleView");
                adapter.add("圆形ImageView控件");
                adapter.add("扇形图");
                adapter.add("FlowLayout");
                adapter.add("Surfaceview");
                break;
            case 2:
                adapter.clear();
                adapter.add("手势监听");
                adapter.add("图片左右滑动－－滑动事件入门");
                break;
            case 3:
                adapter.clear();
                adapter.add("监听ScrollView滑动－－基础");
                adapter.add("监听ScrollView滑动－－Strick滑动块");
                adapter.add("自定义strick");
                adapter.add("ScrollView滑动冲突");
                break;
            case 5:
                adapter.clear();
                adapter.add("GridView－－自定义BaseAdapterItem－图片文字");
                break;
            case 8:
                adapter.clear();
                adapter.add("AysncTask");
                adapter.add("Handler");
                adapter.add("HttpURL请求同步异步");
                adapter.add("Thread Looper Message");
                break;
            case 13:
                adapter.clear();
                adapter.add("<merge>减少布局层次");
                break;
            case 21:
                adapter.clear();
                adapter.add("Example1Activity");
                adapter.add("Example2Activity");
                adapter.add("Example3Activity");
                adapter.add("Example4Activity");
                break;
            case 22:
                adapter.clear();
                adapter.add("ViewPagerTabScrollviewWithFabActivity");
                adapter.add("ActionBarControllScrollviewActivity");
                adapter.add("FlexibleSpaceActionBarScrollviewActiivty");
                adapter.add("FlexibleSpaceWithImageScrollViewActivity");
                break;
            case 23:
                adapter.clear();
                adapter.add("Intent Action");
                adapter.add("Intent Data");
                adapter.add("Intent Category");
                adapter.add("Intent Compent");
                break;
            case 25:
                adapter.clear();
                adapter.add("Note");
                adapter.add("Book");
                adapter.add("Student");
                break;
            default:
                adapter.clear();
                break;
        }

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        setOnClickListener();


    }

    private void setOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView tv_item_content = (TextView) adapterView.getChildAt(position).findViewById(android.R.id.text1);

                if("绘制基本图形".equals(tv_item_content.getText())){
                    Intent intent1 = new Intent(ListActivity.this,DrawBasicPicShapeActitivty.class);
                    startActivity(intent1);
                }
                if("自定义TextView".equals(tv_item_content.getText())){
                    Intent intent2 = new Intent(ListActivity.this,MyTextViewActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
                }
                if("自定义方形条粒子雨效果".equals(tv_item_content.getText())){
                    Intent intent2 = new Intent(ListActivity.this,LiZiYuActicity.class);
                    startActivity(intent2);
                }
                if("手势解锁".equals(tv_item_content.getText())){
                    Intent intent2 = new Intent(ListActivity.this,SettingGestureLockActivity.class);
                    startActivity(intent2);
                }
                if("手势监听".equals(tv_item_content.getText())){
                    Intent intent3 = new Intent(ListActivity.this,TouchActivity.class);
                    startActivity(intent3);
                }
                if("图片左右滑动－－滑动事件入门".equals(tv_item_content.getText())){
                    Intent intent4 = new Intent(ListActivity.this,ViewFlipperActivity.class);
                    startActivity(intent4);
                }
                if("监听ScrollView滑动－－基础".equals(tv_item_content.getText())){
                    Intent intent5 = new Intent(ListActivity.this,ScrollViewBasicAvtivity.class);
                    startActivity(intent5);
                }
                if("监听ScrollView滑动－－Strick滑动块".equals(tv_item_content.getText())){
                    Intent intent6 = new Intent(ListActivity.this,MeituanScrollViewActivity.class);
                    startActivity(intent6);
                }
                if("自定义strick".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this,MyStrickActivity.class);
                    startActivity(intent);
                }
                if("多态".equals(tv_item_content.getText())){
                    Intent intent7 = new Intent(ListActivity.this,InstrumentTest.class);
                    startActivity(intent7);
                }
                if("GridView－－自定义BaseAdapterItem－图片文字".equals(tv_item_content.getText())){
                    Intent intent8 = new Intent(ListActivity.this, GridViewDemo.class);
                    startActivity(intent8);
                }
                if("AysncTask".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, AsyncTaskActivity.class);
                    startActivity(intent);
                }
                if("Handler".equals(tv_item_content.getText())){
                    Intent intent9 = new Intent(ListActivity.this, AsyncTaskActivity.class);
                    startActivity(intent9);
                }
                if("Activity生命周期".equals(tv_item_content.getText())){
                    Intent intent10 = new Intent(ListActivity.this, LifeCircleActivity.class);
                    startActivity(intent10);
                }
                if("Service生命周期".equals(tv_item_content.getText())){
                    Intent intent11 = new Intent(ListActivity.this, MyServiceActivity.class);
                    startActivity(intent11);
                }
                if("用Service作为后台实现计数".equals(tv_item_content.getText())){
                    Intent intent12 = new Intent(ListActivity.this, ServiceActivity.class);
                    startActivity(intent12);
                }
                if("Service:从网络下载一张图片".equals(tv_item_content.getText())){
                    Intent intent13 = new Intent(ListActivity.this, DownLoadPicActivity.class);
                    startActivity(intent13);
                }
                if("前台服务".equals(tv_item_content.getText())){
                    Intent intent14 = new Intent(ListActivity.this, ForegroundActivity.class);
                    startActivity(intent14);
                }
                if("<merge>减少布局层次".equals(tv_item_content.getText())){
                    Intent intent15 = new Intent(ListActivity.this, MergeOptimizeActivity.class);
                    startActivity(intent15);
                }
                if("ContentProvider".equals(tv_item_content.getText())){
                    Intent intent15 = new Intent(ListActivity.this, IndexableListViewActivity.class);
                    startActivity(intent15);
                }
                if("IndexableListView".equals(tv_item_content.getText())){
                    Intent intent16 = new Intent(ListActivity.this, IndexableListViewActivity.class);
                    startActivity(intent16);
                }
                if("RecycleView".equals(tv_item_content.getText())){
                    Intent intent17 = new Intent(ListActivity.this, RecyleViewActivity.class);
                    startActivity(intent17);
                }
                if("HttpURL请求同步异步".equals(tv_item_content.getText())){
                    Intent intent18 = new Intent(ListActivity.this, HttpURLConnectionActivity.class);
                    startActivity(intent18);
                }
                if("Thread Looper Message".equals(tv_item_content.getText())){
                    Intent intent19 = new Intent(ListActivity.this, TestDriverActivity.class);
                    startActivity(intent19);
                }
                if("自定义ViewGroup".equals(tv_item_content.getText())){
                    Intent intent19 = new Intent(ListActivity.this, BlankActivity.class);
                    startActivity(intent19);
                }
                if("圆形ImageView控件".equals(tv_item_content.getText())){
                    Intent intent20 = new Intent(ListActivity.this, RoundImageViewActivity.class);
                    startActivity(intent20);
                }
                if("Example1Activity".equals(tv_item_content.getText())){
                    Intent intent21 = new Intent(ListActivity.this, Example1Activity.class);
                    startActivity(intent21);
                }
                if("Example2Activity".equals(tv_item_content.getText())){
                    Intent intent21 = new Intent(ListActivity.this, Example2Activity.class);
                    startActivity(intent21);
                }
                if("Example3Activity".equals(tv_item_content.getText())){
                    Intent intent21 = new Intent(ListActivity.this, Example3Activity.class);
                    startActivity(intent21);
                }
                if("Example4Activity".equals(tv_item_content.getText())){
                    Intent intent21 = new Intent(ListActivity.this, Example4Activity.class);
                    startActivity(intent21);
                }
                if("Intent Action".equals(tv_item_content.getText())){
                    //拨打电话
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_DIAL);
//                    intent.setData(Uri.parse("tel:18329027970"));
//                    startActivity(intent);
                    //打开网页
                    Intent intent1 = new Intent();
                    intent1.setAction(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse("https://m.baidu.com/"));
                    startActivity(intent1);
                }
                if("Intent Category".equals(tv_item_content.getText())){
                    Intent intent = new Intent("com.lc.proctice.androidstudioproctice.WithAcitonONEActivity.ACTIONONE");
//                    intent.addCategory("com.lichent.protice.CATEGORYTWO");
                    startActivity(intent);
                }
                if("Note".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, NoteActivity.class);
//                    intent.addCategory("com.lichent.protice.CATEGORYTWO");
                    startActivity(intent);
                }
                if("Book".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, BookActivity.class);
//                    intent.addCategory("com.lichent.protice.CATEGORYTWO");
                    startActivity(intent);
                }
                if("Student".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, StudentActivity.class);
//                    intent.addCategory("com.lichent.protice.CATEGORYTWO");
                    startActivity(intent);
                }
                if("ViewPagerTabScrollviewWithFabActivity".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, ViewPagerTabScrollviewWithFabActivity.class);
                    startActivity(intent);
                }
                if("ActionBarControllScrollviewActivity".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, ActionBarControllScrollviewActivity.class);
                    startActivity(intent);
                }
                if("FlexibleSpaceActionBarScrollviewActiivty".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, FlexibleSpaceActionBarScrollviewActiivty.class);
                    startActivity(intent);
                }
                if("FlexibleSpaceWithImageScrollViewActivity".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, FlexibleSpaceWithImageScrollViewActivity.class);
                    startActivity(intent);
                }
                if("Messenger进程间通信".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, MessengerActivity.class);
                    startActivity(intent);
                }
                if("扇形图".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, PieViewActivity.class);
                    startActivity(intent);
                }
                if("ScrollView滑动冲突".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, HorizontalScrollviewActivity.class);
                    startActivity(intent);
                }
                if("FlowLayout".equals(tv_item_content.getText())){
                    Intent intent = new Intent(ListActivity.this, FlowLayoutActivity.class);
                    startActivity(intent);
                }
                if("Surfaceview".equals(tv_item_content.getText())) {
                    startActivity(new Intent(ListActivity.this, SurfaceActivity.class));
                }
            }
        });
    }

    private void initView() {
        Log.d(TAG,getIntent().getStringExtra("from_list_item"));
        position = Integer.valueOf(getIntent().getStringExtra("from_list_item"));
        listView = (ListView) findViewById(R.id.listView);
//        listView.setLayoutAnimation(getAnimationController());
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1);
    }

    protected LayoutAnimationController getAnimationController() {
        int duration=500;
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(duration);
        set.addAnimation(animation);

        animation = new TranslateAnimation(-50f, 0f, 0f, 0f);
        animation.setDuration(duration);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set, 1f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return controller;
    }
}

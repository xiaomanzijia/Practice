package com.lc.proctice.androidstudioproctice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lc.proctice.androidstudioproctice.anim.propertyanim.PropertyAnimActivity;
import com.lc.proctice.androidstudioproctice.contact.GetContactsActivity;
import com.lc.proctice.androidstudioproctice.dialog.MyDialogActivity;
import com.lc.proctice.androidstudioproctice.fresco.FrescoActivity;
import com.lc.proctice.androidstudioproctice.indexablelistview.IndexableListViewActivity;
import com.lc.proctice.androidstudioproctice.indexablelistview.MyIndexableListViewActivity;
import com.lc.proctice.androidstudioproctice.mizhuan.MiZhuanActivity;
import com.lc.proctice.androidstudioproctice.mvp.BeautyActivity;
import com.lc.proctice.androidstudioproctice.myprovider.ContentProviderActivity;
import com.lc.proctice.androidstudioproctice.myviewpager.WelcomeActivity;
import com.lc.proctice.androidstudioproctice.photo.ImageShowActivity;
import com.lc.proctice.androidstudioproctice.shopcar.ShopCarActivty;
import com.lc.proctice.androidstudioproctice.wechatslideview.WeChatSlideViewActivity;
import com.lc.proctice.androidstudioproctice.weixin.WeChatMainActivity;
import com.lc.proctice.androidstudioproctice.zdyviewgroup.ZDYViewGroupActivity;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        adapter.add("Android生命周期");
        adapter.add("自定义View");
        adapter.add("触摸事件");
        adapter.add("ScrollView滑动监听");
        adapter.add("百度地图");
        adapter.add("GridView");
        adapter.add("自定义ViewGroup");
        adapter.add("米赚");
        adapter.add("异步线程AsyncTask");
        adapter.add("获取手机通讯录");
        adapter.add("ViewPager制作引导页");
        adapter.add("微信朋友圈分享");
        adapter.add("图片上传");
        adapter.add("UI优化");
        adapter.add("IndexableListView");
        adapter.add("MyIndexableListView");
        adapter.add("微信滑动删除");
        adapter.add("三级目录选择");
        adapter.add("省份城市选择");
        adapter.add("购物车");
        adapter.add("Google官方MVP");
        adapter.add("RxAndroid");
        adapter.add("滑动界面动画");
        adapter.add("Intent");
        adapter.add("ContentProvider");
        adapter.add("greenDao数据库");
        adapter.add("Fresco");
        adapter.add("Dialog");
        adapter.add("动画");
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        setOnClickListener();


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



    }

    private void setOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        intent.putExtra("from_list_item",String.valueOf(position));
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), ListActivity.class);
                        intent1.putExtra("from_list_item", String.valueOf(position));
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), ListActivity.class);
                        intent2.putExtra("from_list_item", String.valueOf(position));
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(),ListActivity.class);
                        intent3.putExtra("from_list_item",String.valueOf(position));
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getApplicationContext(),MiZhuanActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(getApplicationContext(),ListActivity.class);
                        intent5.putExtra("from_list_item",String.valueOf(position));
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(getApplicationContext(),ZDYViewGroupActivity.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(getApplicationContext(),MiZhuanActivity.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(getApplicationContext(),ListActivity.class);
                        intent8.putExtra("from_list_item",String.valueOf(position));
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(getApplicationContext(),GetContactsActivity.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(getApplicationContext(),WelcomeActivity.class);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(getApplicationContext(),WeChatMainActivity.class);
                        startActivity(intent11);
                        break;
                    case 12:
                        Intent intent12 = new Intent(getApplicationContext(),ImageShowActivity.class);
                        startActivity(intent12);
                        break;
                    case 13:
                        Intent intent13 = new Intent(getApplicationContext(),ListActivity.class);
                        intent13.putExtra("from_list_item", String.valueOf(position));
                        startActivity(intent13);
                        break;
                    case 14:
                        Intent intent14 = new Intent(getApplicationContext(),IndexableListViewActivity.class);
                        startActivity(intent14);
                        break;
                    case 15:
                        Intent intent15 = new Intent(getApplicationContext(),MyIndexableListViewActivity.class);
                        startActivity(intent15);
                        break;
                    case 16:
                        Intent intent16 = new Intent(getApplicationContext(),WeChatSlideViewActivity.class);
                        startActivity(intent16);
                        break;
                    case 17:
                        Intent intent17 = new Intent(getApplicationContext(),CategoryActivity.class);
                        startActivity(intent17);
                        break;
                    case 18:
                        Intent intent18 = new Intent(getApplicationContext(),CitySelectedActivity.class);
                        startActivity(intent18);
                        break;
                    case 19:
                        Intent intent19 = new Intent(getApplicationContext(),ShopCarActivty.class);
                        startActivity(intent19);
                        break;
                    case 20:
                        Intent intent20 = new Intent(getApplicationContext(),BeautyActivity.class);
                        startActivity(intent20);
                        break;
                    case 21:
                        Intent intent21 = new Intent(getApplicationContext(),ListActivity.class);
                        intent21.putExtra("from_list_item", String.valueOf(position));
                        startActivity(intent21);
                        break;
                    case 22:
                        Intent intent22 = new Intent(getApplicationContext(),ListActivity.class);
                        intent22.putExtra("from_list_item", String.valueOf(position));
                        startActivity(intent22);
                        break;
                    case 23:
                        Intent intent23 = new Intent(getApplicationContext(),ListActivity.class);
                        intent23.putExtra("from_list_item", String.valueOf(position));
                        startActivity(intent23);
                        break;
                    case 24:
                        Intent intent24 = new Intent(getApplicationContext(),ContentProviderActivity.class);
                        startActivity(intent24);
                        break;
                    case 25:
                        Intent intent25 = new Intent(getApplicationContext(),ListActivity.class);
                        intent25.putExtra("from_list_item", String.valueOf(position));
                        startActivity(intent25);
                        break;
                    case 26:
                        Intent intent26 = new Intent(getApplicationContext(),FrescoActivity.class);
                        startActivity(intent26);
                    case 27:
                        Intent intent27 = new Intent(getApplicationContext(),MyDialogActivity.class);
                        startActivity(intent27);
                        break;
                    case 28:
                        Intent intent28 = new Intent(getApplicationContext(),PropertyAnimActivity.class);
                        startActivity(intent28);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

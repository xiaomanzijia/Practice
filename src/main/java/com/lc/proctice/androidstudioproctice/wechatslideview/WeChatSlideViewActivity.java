package com.lc.proctice.androidstudioproctice.wechatslideview;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lc.proctice.androidstudioproctice.R;

import java.util.ArrayList;

/**
 * Created by licheng on 4/11/15.
 */
public class WeChatSlideViewActivity extends Activity {
    private WeChatListView listView;
    private ArrayList<WeChatMessageItem> list;

//    private Animation ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat_slideview_main);

        listView = (WeChatListView) findViewById(R.id.wechat_list);
        listView.setLayoutAnimation(getAnimationController());

        list = new ArrayList<WeChatMessageItem>();
        for(int i = 0;i < 20;i++){
            WeChatMessageItem item = new WeChatMessageItem();
            if(i%3==0){
                item.iconRes=R.drawable.default_qq_avatar;
                item.msg="Hello World!";
                item.title="微信团队";
                item.time="09:33";
            }else {
                item.iconRes=R.drawable.default_qq_avatar;
                item.msg="老张开车去东北！";
                item.title="智行科技";
                item.time="昨天08:23";
            }
            list.add(item);
        }
        listView.setAdapter(new WeChatSlideAdapter());
    }

    public class WeChatSlideAdapter extends BaseAdapter{
        private LayoutInflater mInflater;


        WeChatSlideAdapter(){
            super();
            mInflater = getLayoutInflater();
//            ani = AnimationUtils.loadAnimation(WeChatSlideViewActivity.this, R.anim.item_anim);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            SlideViewWeChat slideViewWeChat = (SlideViewWeChat) convertView;
            if(slideViewWeChat==null){
                View itemView = mInflater.inflate(R.layout.wechat_list_item,null);

//                ani.setDuration(1000);
//                itemView.setAnimation(ani);
                
                slideViewWeChat = new SlideViewWeChat(getApplicationContext());

                slideViewWeChat.setContentView(itemView);

                viewHolder = new ViewHolder(slideViewWeChat);

                //设置点击事件


                slideViewWeChat.setTag(viewHolder);

            }else{
                viewHolder = (ViewHolder) slideViewWeChat.getTag();
            }

            WeChatMessageItem item = list.get(position);
            item.slideView = slideViewWeChat;

            viewHolder.icon.setImageResource(item.iconRes);
            viewHolder.title.setText(item.title);
            viewHolder.msg.setText(item.msg);
            viewHolder.time.setText(item.time);

            return slideViewWeChat;
        }
    }

    public static class ViewHolder{
        public ImageView icon;
        public TextView title;
        public TextView msg;
        public TextView time;
        public ViewGroup deleteHolder;

        ViewHolder(View view){
            icon = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.title);
            msg = (TextView) view.findViewById(R.id.msg);
            time = (TextView) view.findViewById(R.id.time);
            deleteHolder = (ViewGroup) view.findViewById(R.id.holderview);
        }
    }


    public class WeChatMessageItem {
        public int iconRes;
        public String title;
        public String msg;
        public String time;
        public SlideViewWeChat slideView;
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


        /** 获取LayoutAnimationController对象，设置delay延时 **/
        LayoutAnimationController controller = new LayoutAnimationController(set, 1f);

        /** 设置动画加载顺序 **/
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);


        return controller;
    }
}

package com.lc.proctice.androidstudioproctice.weixin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.lc.proctice.androidstudioproctice.R;
import com.tencent.mm.sdk.modelmsg.*;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.net.URL;

/**
 * Created by licheng on 24/8/15.
 */
public class WeChatMainActivity extends Activity {
    private static final int THUMB_SIZE = 50;

    private static final String APP_ID = "wx59cbaac5466c82cd";

    private Button btn_regtoWx,btn_sendText,btn_sendImg;

    private String text = "不需要明白";

    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat);

        regToWeixin();

        initView();

        btn_regtoWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regToWeixin();
            }
        });


        btn_sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText();
            }
        });

        btn_sendImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendImg();
                sendWebPage();
            }
        });


    }

    private void sendImg() {
        String url = "http://d.hiphotos.baidu.com/image/pic/item/3b292df5e0fe992597d036bd30a85edf8cb1717a.jpg";

        try {
            WXImageObject imgObj = new WXImageObject();
            imgObj.imageUrl = url;

            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = imgObj;

            Bitmap bmp = BitmapFactory.decodeStream(new URL(url).openStream());
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
            bmp.recycle();
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("firstimg");
            req.message = msg;
            //是否分享到朋友圈
//            req.scene = SendMessageToWX.Req.WXSceneTimeline;
            api.sendReq(req);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void sendText() {
        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        // msg.title = "Will be ignored";
        msg.description = text;

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("firsttext"); // transaction字段用于唯一标识一个请求
        req.message = msg;

        //是否分享到朋友圈
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        // 调用api接口发送数据到微信
        api.sendReq(req);

    }

    private void sendWebPage(){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://94.com/";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "94执行力";
        msg.description = "给企业和组织提供上级与下属之间任务分配及完成情况汇报功能，帮助企业迅速提高执行力。";
//        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.send_music_thumb);
//        msg.thumbData = Util.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("first_webpage");
        req.message = msg;
//        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);

    }

    private void initView() {
        btn_regtoWx = (Button) findViewById(R.id.btn_regtoWx);
        btn_sendText = (Button) findViewById(R.id.btn_sendText);
        btn_sendImg = (Button) findViewById(R.id.btn_sendImg);
    }

    private void regToWeixin(){
        //将应用注册到微信
        api = WXAPIFactory.createWXAPI(getApplicationContext(),APP_ID,true);
        api.registerApp(APP_ID);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}

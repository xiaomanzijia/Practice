package com.lc.proctice.androidstudioproctice.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by licheng on 17/1/16. 最原始的Android网络请求 Get Post
 */
public class NetUtils {

    //单元测试
    public static void main(String[] args) {
        String response = get("http://www.diandidaxue.com:8080/apiServer.do?opcode=getBeauty&pageNum=1&numPerPage=2");
        System.out.println("自定义Get请求"+response);
    }

    public static String post(String url,String content){
        HttpURLConnection conn = null;
        try {
            //创建一个URL对象
            URL mURL = new URL(url);
            try {
                //获取HttpURLConnection对象
                conn = (HttpURLConnection) mURL.openConnection();
                conn.setRequestMethod("POST");
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(10000);
                conn.setDoOutput(true);//允许向服务器输出内容

                //post参数
                String data = content;
                //获取一个输出流，向服务器写数据
                OutputStream out = conn.getOutputStream();
                out.write(data.getBytes());
                out.flush();
                out.close();

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    InputStream is = conn.getInputStream();
                    String respose = getStringFromInputString(is);
                    return respose;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return null;
    }

    public static String get(String url){
        HttpURLConnection conn = null;
        try {
            URL mURL = new URL(url);
            try {
                conn = (HttpURLConnection) mURL.openConnection();

                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(10000);

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    InputStream is = conn.getInputStream();
                    String response = getStringFromInputString(is);
                    return  response;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return null;
    }

    private static String getStringFromInputString(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        //模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        is.close();
        String state = os.toString();//把流中的数据转换成字符串
        os.close();
        return  state;
    }
}

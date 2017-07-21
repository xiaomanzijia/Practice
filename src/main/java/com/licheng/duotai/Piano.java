package com.licheng.duotai;

import java.util.Scanner;

/**
 * Created by licheng on 30/7/15.
 */
public class Piano extends Instrument {
    @Override
    public void play() {
        System.out.println("弹奏钢琴");
    }

    public static void main(String[] args) {
        System.out.println(toChinese("10506043"));
    }

    private static String toChinese(String temp) {
        // 单位数组
        String[] units = new String[] {"十", "百", "千", "万", "十", "百", "千", "亿"};

        // 中文大写数字数组
        String[] numeric = new String[] {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        

        String res = "";

        for (int i = -1; temp.length() > 0; i++) {

            int j = Integer.parseInt(temp.substring(temp.length() - 1, temp.length()));

            String rtemp = numeric[j];

            // 数值不是0且不是个位 或者是万位或者是亿位 则取单位
            if(j != 0 && i != -1 || i % 8 == 3 || i % 8 == 7){
                rtemp += units[i % 8];
            }

            res = rtemp + res;

            temp = temp.substring(0, temp.length() - 1);
        }


        
        return res;
    }
}

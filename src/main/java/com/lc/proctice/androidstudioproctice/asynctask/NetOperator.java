package com.lc.proctice.androidstudioproctice.asynctask;

/**
 * Created by licheng on 17/8/15.
 */
public class NetOperator {
    public void operator(){
        try {
            //休眠一秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

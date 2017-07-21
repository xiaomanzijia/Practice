package com.lc.proctice.androidstudioproctice.myview;

/**
 * Created by licheng on 26/7/16.
 */
public class PieData {

    private String name; //名字
    private float value; //数值
    private float percentage; //百分比

    private int color; //颜色
    private float angle; //角度

    public PieData(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

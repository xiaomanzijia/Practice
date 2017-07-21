package com.lc.proctice.androidstudioproctice.screen;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.lc.proctice.androidstudioproctice.R;

/**
 * Created by licheng on 26/8/15.
 */
public class OkCancelBar extends LinearLayout {
    private View view;
    public OkCancelBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setWeightSum(1.0f);

        view = LayoutInflater.from(context).inflate(R.layout.okcancelbar, this, true);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.OkCancelBar, 0, 0);

        String text = array.getString(R.styleable.OkCancelBar_okLabel);
        if (text == null) text = "Ok";
        ((Button) view.findViewById(R.id.okcancelbar_ok)).setText(text);

        text = array.getString(R.styleable.OkCancelBar_cancelLabel);
        if (text == null) text = "Cancel";
        ((Button) view.findViewById(R.id.okcancelbar_cancel)).setText(text);

        array.recycle();
    }
}

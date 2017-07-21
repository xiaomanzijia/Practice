package com.lc.proctice.androidstudioproctice.wheelview.widget;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class ShuoMClickableSpan extends ClickableSpan {
	
	Context context;
	int color;
	Class<?> cls;
	public ShuoMClickableSpan(Context context, int color, Class<?> cls){
		super();
		this.context = context;
		this.color = color;
		this.cls = cls;
		
	}
	
	
	@Override
	public void updateDrawState(TextPaint ds) {
		ds.setColor(color);
		ds.setUnderlineText(true);
	}


	@Override
	public void onClick(View widget) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		context.startActivity(intent);

	}


}

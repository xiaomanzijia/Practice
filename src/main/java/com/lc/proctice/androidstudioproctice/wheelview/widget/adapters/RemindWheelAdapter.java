package com.lc.proctice.androidstudioproctice.wheelview.widget.adapters;

import android.content.Context;

import com.lc.proctice.androidstudioproctice.wheelview.widget.WheelAdapter;


/**
 * Numeric Wheel adapter.
 */
public class RemindWheelAdapter implements WheelAdapter {

	private String values = null;
	private String data[] = null;
    private Context context;
	public RemindWheelAdapter(String[] data) {
		this.data = data;
	}
    
	public RemindWheelAdapter(String[] data, Context context) {
		super();
		this.data = data;
		this.context = context;
	}

	public String getItem(int index) {
		if (index >= 0 && index < getItemsCount()) {
			String values = data[index];
			setValue(values);
			return values;
		}
		return null;
	}

	public String getValues() {
		return values;
	}

	public void setValue(String value) {
		this.values = value;
	}

	public int getItemsCount() {
		return data.length;
	}

	@Override
	public int getMaximumLength() {
		// TODO Auto-generated method stub
		return data.length;
	}
}

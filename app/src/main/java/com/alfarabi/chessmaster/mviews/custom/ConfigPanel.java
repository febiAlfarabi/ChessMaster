package com.alfarabi.chessmaster.mviews.custom;

import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ConfigPanel extends LinearLayout implements MyCustomInitializer{

	
	
	public ConfigPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			initObject();
			initUI();
			initCustomUI();
			
		}
	
	}

	@Override
	public void initObject() {
		
	}

	@Override
	public void initUI() {
	}

	@Override
	public void initCustomUI() {
		// TODO Auto-generated method stub
		
	}


	


}

package com.alfarabi.chessmaster.mviews.custom;


import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ConfigBasePanel extends RelativeLayout implements MyCustomInitializer{

	private Context context ;
	
	public ConfigBasePanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context ;
		if (!isInEditMode()){
			initObject();
			initUI();
			initCustomUI();
		}
	}

	@Override
	public void initObject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initUI() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void initCustomUI() {
//		setPadding(10, 10, 10, 10);
		
	}

	

}

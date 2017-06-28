package com.alfarabi.chessmaster.mviews.layout;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PlayerInfo extends LinearLayout implements MyCustomInitializer{

	private AttributeSet attrs ;
	private Context context ;
	public boolean mine = false ;
	
	public PlayerInfo(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context ;
		this.attrs = attrs ;
				
		if (isInEditMode()) {
			
		}else{
			
			initObject();
			initUI();
			initCustomUI();
		}
	
	}

	@Override
	public void initObject() {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlayerInfo, 0, 0);
		try {
			this.mine = a.getBoolean(R.styleable.PlayerInfo_mine, false);
					
		} finally {
			a.recycle();
		}
	}

	@Override
	public void initUI() { }

	@Override
	public void initCustomUI() { }
	
}

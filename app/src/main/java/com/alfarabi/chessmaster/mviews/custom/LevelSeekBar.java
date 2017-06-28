package com.alfarabi.chessmaster.mviews.custom;


import com.alfarabi.chessmaster.memoriez.JChezz;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class LevelSeekBar extends SeekBar{

	private Float limitLevel ;
	private int level = 1;
	private SeekBarPanel sbp ;
	
	public LevelSeekBar(Context context, SeekBarPanel sbp) {
		super(context);
		setMax(90);
		int l = JChezz.getInstance().getStaticLevel();
		setProgress((l*10)-10);
		this.sbp = sbp;
	}
	
	public LevelSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		setMax(90);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getX()>limitLevel) {
			Float assLevel = event.getX()/limitLevel;
			level = assLevel.intValue()+1;
			setProgress((level*10)-10);
		}else{
			level = 1;
			setProgress((level*10)-10);
		}
		sbp.invalidate();
		JChezz.getInstance().setStaticLevel(level);
		Log.i("Level", String.valueOf(JChezz.getInstance().getStaticLevel()));
		return true;
	}
	
	public void setLimitLevel(Float limitLevel) {
		this.limitLevel = limitLevel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}

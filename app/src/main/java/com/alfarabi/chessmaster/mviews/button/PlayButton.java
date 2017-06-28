package com.alfarabi.chessmaster.mviews.button;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.media.theme.JDesign;
import com.alfarabi.chessmaster.media.theme.MThemes;
import com.alfarabi.chessmaster.tools.DimensionTools;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public final class PlayButton extends Button{

	private int windowsWeight = 0;
	
	public PlayButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setVisibility(GONE);
		if (isInEditMode()) {
			this.setBackgroundResource(R.drawable.bg_play);
			this.setWidth(30);
			this.setHeight(25);
			
		}else{
			windowsWeight = DimensionTools.countAndGetBoardWeight(context);
			initThemes(MThemes.DEFAULT_THEME);
		}
		setVisibility(VISIBLE);
		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_DOWN) {
			getLayoutParams().width = windowsWeight/5+5;
			getLayoutParams().height = windowsWeight/6+5;
			setLayoutParams(getLayoutParams());
		}else if (event.getAction()==MotionEvent.ACTION_UP){
			getLayoutParams().width = windowsWeight/5+5;
			getLayoutParams().height = windowsWeight/6+5;
			setLayoutParams(getLayoutParams());
		}
		return super.onTouchEvent(event);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		getLayoutParams().width = windowsWeight/5;
		getLayoutParams().height = windowsWeight/6;
		setLayoutParams(getLayoutParams());
		
		super.onDraw(canvas);
	}
	
	public void initThemes(char theme){
		if (theme==JDesign.getInstance().getMthemes().getCurrentTheme()) {
			
		}else{
			JDesign.getInstance().getMthemes().setCurrentTheme(theme);
		}
		char currentTheme = JDesign.getInstance().getMthemes().getCurrentTheme();
		this.setBackgroundResource(JDesign.getInstance().getMthemes().getThemes().get(currentTheme).getBgPlayButton());
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

}

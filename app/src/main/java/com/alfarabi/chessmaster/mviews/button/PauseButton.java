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

public final class PauseButton extends Button{

	private int weight = 0;
	private char curentTheme ;
	private int heightOfDvc = 0;
	
	public PauseButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setVisibility(GONE);
		if (isInEditMode()) {
			weight = 16;
			setWidth(weight);
			setHeight(weight);
			this.setBackgroundResource(R.drawable.bg_pause_button);
		}else{
			weight = DimensionTools.countAndGetBoardWeight(context);
			heightOfDvc = DimensionTools.getHeight(context);
			weight = (heightOfDvc-(heightOfDvc/10)-weight)/3;
			initThemes(MThemes.DEFAULT_THEME);
		}
		setVisibility(VISIBLE);
		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_DOWN) {
			this.setBackgroundResource(JDesign.getInstance().getMthemes().getThemes().get(curentTheme).getBgPauseButtonSelected());
			setWidth(weight+5);
			setHeight(weight+5);
			
		}else if (event.getAction()==MotionEvent.ACTION_UP){
			this.setBackgroundResource(JDesign.getInstance().getMthemes().getThemes().get(curentTheme).getBgPauseButton());
			setWidth(weight);
			setHeight(weight);
			
		}
		
		return super.onTouchEvent(event);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
	}
	
	public void initThemes(char theme){
		if (theme==JDesign.getInstance().getMthemes().getCurrentTheme()) {
			
		}else{
			JDesign.getInstance().getMthemes().setCurrentTheme(theme);
		}
		
		this.curentTheme = JDesign.getInstance().getMthemes().getCurrentTheme();
		this.setBackgroundResource(JDesign.getInstance().getMthemes().getThemes().get(curentTheme).getBgPauseButton());
		setWidth(weight);
		setHeight(weight);
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

}

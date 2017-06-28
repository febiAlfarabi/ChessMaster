package com.alfarabi.chessmaster.mviews.custom;

import com.alfarabi.chessmaster.media.theme.JDesign;
import com.alfarabi.chessmaster.media.theme.MThemes;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class BaseAppWall extends RelativeLayout{

	public BaseAppWall(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) {
//			this.setBackgroundResource(R.drawable.bg_wall);
		}else{
			initThemes(MThemes.DEFAULT_THEME);
			
		}
	}
	
	public void initThemes(char theme){

		if (theme==JDesign.getInstance().getMthemes().getCurrentTheme()) {
			
		}else{
			JDesign.getInstance().getMthemes().setCurrentTheme(theme);
		}
		char currentTheme = JDesign.getInstance().getMthemes().getCurrentTheme();
		this.setBackgroundResource(JDesign.getInstance().getMthemes().getThemes().get(currentTheme).getBgWalpaper());
	}

}

package com.alfarabi.chessmaster.tools;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public final class DimensionTools {
	
	public static Integer getWidth(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		return display.getWidth();
	}
	
	public static Integer getHeight(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		return display.getHeight();
	}
	
	public static int countAndGetBoardWeight(Context context){
		int h = 0;
		if (getHeight(context)==getWidth(context)) {
			h = getHeight(context);
		}else if (getHeight(context)>getWidth(context)) {
			h = getWidth(context);
		}else if (getHeight(context)<getWidth(context)) {
			h = getHeight(context);
		}
		return h;
		
	}

}

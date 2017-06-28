package com.alfarabi.chessmaster.mviews;

import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class BaseBoardLayout extends RelativeLayout implements MyCustomInitializer{

	public BaseBoardLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) {
//			JUST FOR EDITING DEVELOPMENT MODE
			Board board = new Board(context, attrs);
			this.addView(board, new LayoutParams(board.windowWeight, board.windowWeight));
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
		// TODO Auto-generated method stub
		
	}
	
	

}

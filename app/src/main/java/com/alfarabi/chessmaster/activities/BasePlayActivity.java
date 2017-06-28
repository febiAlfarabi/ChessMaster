package com.alfarabi.chessmaster.activities;

import java.util.ArrayList;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.aic.MinimaxAB;
import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;
import com.alfarabi.chessmaster.interfazes.PlayActionListener;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.memoriez.JHistoriez;
import com.alfarabi.chessmaster.model.History;
import com.alfarabi.chessmaster.mviews.BaseBoardLayout;
import com.alfarabi.chessmaster.mviews.Board;
import com.alfarabi.chessmaster.mviews.Piece;
import com.alfarabi.chessmaster.tools.Calculazy;
import com.alfarabi.chessmaster.tools.Collectionz;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public class BasePlayActivity extends Activity implements MyCustomInitializer, PlayActionListener, OnClickListener{

	public Board board ;
	public BaseBoardLayout baseBoardLayout ;
	public MinimaxAB minimaxAB ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	protected void initStartConfiguration(){
		JHistoriez.getInstance().setHistories(new ArrayList<History>());
		if (JChezz.getInstance().getMyColor()==Color.WHITE) {
			onChangeSession(JChezz.MY_SESSION);
		
		}else if (JChezz.getInstance().getMyColor()==Color.BLACK) {
			onChangeSession(JChezz.YOUR_SESSION);
		}
	}
	
	@Override
	public void removePieceFromBoard(Piece piece) {
		baseBoardLayout.removeView(piece);
	}
	
	@Override
	public void onChangeSession(boolean session) {
		JChezz.getInstance().setSession(session);
//		Calculazy.routingCondition(board);
	}

	@Override
	public void initObject() {
		board = new Board(this);
		
	}

	@Override
	public void initUI() {
		baseBoardLayout = (BaseBoardLayout)findViewById(R.id.baseBoardLayout);
		baseBoardLayout.addView(board, new LayoutParams(board.windowWeight, board.windowWeight));
		
		Collectionz.initStart(this, baseBoardLayout, board);
		
	}

	@Override
	public void initCustomUI() { }
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
	
	}
	
}

package com.alfarabi.chessmaster.activities;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.aic.MinimaxAB;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.memoriez.JHistoriez;
import com.alfarabi.chessmaster.mviews.Piece;
import com.alfarabi.chessmaster.mviews.button.PauseButton;
import com.alfarabi.chessmaster.mviews.button.UndoButton;
import com.alfarabi.chessmaster.mviews.custom.ImageIcon;
import com.alfarabi.chessmaster.mviews.dialog.PauseDialog;
import com.alfarabi.chessmaster.mviews.layout.GameInfo;
import com.alfarabi.chessmaster.mviews.layout.PlayerInfo;
import com.alfarabi.chessmaster.mviews.textview.MoveDuration;
import com.alfarabi.chessmaster.mviews.textview.TotalDuration;
import com.alfarabi.chessmaster.tools.Calculazy;
import com.alfarabi.chessmaster.tools.Constant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public final class PlayActivity extends BasePlayActivity{

	public UndoButton undoButton ;
	private PauseButton pauseButton ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.play_activity);
		initObject();
		initUI();
		initCustomUI();
		initStartConfiguration();
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public void initUI() {
		super.initUI();
		undoButton = (UndoButton) findViewById(R.id.undoButton);
		undoButton.setOnClickListener(this);
		pauseButton = (PauseButton) findViewById(R.id.pauseButton);
		pauseButton.setOnClickListener(this);
	}
	
	@Override
	public void onChangeSession(boolean session) {
		super.onChangeSession(session);
		char condition = Calculazy.calculazorWhileGame(board);
		if(condition==Calculazy.FREE_PLAY){
			minimaxAB = null;
			if (session==JChezz.YOUR_SESSION) {
				((PlayerInfo)findViewById(R.id.yourPlayerInfo)).setBackgroundDrawable(getResources().getDrawable(R.drawable.player_unselected));
				((PlayerInfo)findViewById(R.id.myPlayerInfo)).setBackgroundColor(Color.TRANSPARENT);
				
				((ImageIcon)findViewById(R.id.myImageIcon)).myAnimate(false);
				((ImageIcon)findViewById(R.id.yourImageIcon)).myAnimate(true);
				((GameInfo)findViewById(R.id.myGameInfo)).countMove(false);
				((GameInfo)findViewById(R.id.yourGameInfo)).countMove(true);

				if (JChezz.getInstance().isPlayingWithCom()) {
					minimaxAB = new MinimaxAB(this);
					minimaxAB.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Piece.YOU);
				}
			}else if (session==JChezz.MY_SESSION){
				((PlayerInfo)findViewById(R.id.yourPlayerInfo)).setBackgroundColor(Color.TRANSPARENT);
				((PlayerInfo)findViewById(R.id.myPlayerInfo)).setBackgroundDrawable(getResources().getDrawable(R.drawable.player_selected));
				((ImageIcon)findViewById(R.id.myImageIcon)).myAnimate(true);
				((ImageIcon)findViewById(R.id.yourImageIcon)).myAnimate(false);
				((GameInfo)findViewById(R.id.myGameInfo)).countMove(true);
				((GameInfo)findViewById(R.id.yourGameInfo)).countMove(false);
				
			}			
		}else{
			if (condition==Calculazy.P_ONE_WIN) {
				new PauseDialog(this, PauseDialog.P_TWO_LOOSE).show();
			}else if (condition==Calculazy.P_TWO_WIN) {
				new PauseDialog(this, PauseDialog.P_ONE_LOOSE).show();
				
			}
		}

		
	}
	
	@Override
	public void onClick(View v) {
		v.setEnabled(false);
		if (v.getId()==R.id.undoButton) {
			undoButton.setEnabled(false);
			undoButton.setClickable(false);
			if (JHistoriez.getInstance().getHistories().size()>0) {
				if (minimaxAB!=null) {
					minimaxAB.cancel(true);
					minimaxAB = null;
				}
				board.undoLastHistoryPlayerMove();
				
			}
			undoButton.setEnabled(true);
			undoButton.setClickable(true);
			
		}else if (v.getId()==R.id.pauseButton){
			pauseButton.setEnabled(false);
			pauseButton.setClickable(false);
			new PauseDialog(this, PauseDialog.PAUSE).show();
			pauseButton.setEnabled(true);
			pauseButton.setClickable(true);
			
		}
		v.setEnabled(true);
		super.onClick(v);
		
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			stopGamesAndBackToConfig();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	public void stopGamesAndBackToConfig(){
		if (minimaxAB!=null) {
			minimaxAB.cancel(true);
		}
	}
	
}

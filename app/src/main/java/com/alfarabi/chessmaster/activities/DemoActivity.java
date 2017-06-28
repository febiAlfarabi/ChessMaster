package com.alfarabi.chessmaster.activities;

import java.util.Timer;
import java.util.TimerTask;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.aic.MinimaxAB;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.mviews.Piece;
import com.alfarabi.chessmaster.tools.Calculazy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class DemoActivity extends BasePlayActivity {

	private Timer timer ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		JChezz.getInstance().setStaticLevel(4);
		setContentView(R.layout.demo_activity);
		initObject();
		initUI();
		initCustomUI();
		initStartConfiguration();
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void initObject() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 0;
			@Override
			public void run() {
				i++;
				if (i>=30) {
					nextToConfigActivity();
				}
			}
		}, 1, 1000);
		super.initObject();
	}
	
	@Override
	public void initUI() {
		super.initUI();
		findViewById(R.id.nextButton).setOnClickListener(this);
		
	}
	
	@Override
	public void initCustomUI() {
		super.initCustomUI();
	}
	
	@Override
	public void onChangeSession(boolean session) {
		super.onChangeSession(session);
		if(Calculazy.calculazorWhileGame(board)==Calculazy.FREE_PLAY){
			if (session==JChezz.YOUR_SESSION) {
				minimaxAB = new MinimaxAB(this);
				minimaxAB.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Piece.YOU);
			}else if (session==JChezz.MY_SESSION) {
				minimaxAB = new MinimaxAB(this);
				minimaxAB.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Piece.ME);
			
			}
		}else{
			timer.cancel();
			nextToConfigActivity();
		}
		
		
	}
	private void nextToConfigActivity(){
		Intent intent = new Intent(this, ChessConfigActivity.class);
		if (minimaxAB!=null) {
			minimaxAB.cancel(true);
			minimaxAB = null;
		}
		if (timer!=null) {
			timer.cancel();
			timer = null;
		}
		JChezz.getInstance().setStaticLevel(1);
		startActivity(intent);
		this.finish();
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.nextButton) {
			nextToConfigActivity();
		}
		super.onClick(v);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			if (timer!=null) {
				timer.cancel();
				timer = null;
			}
			if (minimaxAB!=null) {
				minimaxAB.cancel(true);
				minimaxAB = null;
			}
		}
		this.finish();
		System.exit(0);
		return super.onKeyDown(keyCode, event);
	}

}

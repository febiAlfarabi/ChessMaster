package com.alfarabi.chessmaster.activities;

import java.util.Calendar;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.tools.ADSThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public final class ChessConfigActivity extends Activity implements MyCustomInitializer, OnClickListener, OnCheckedChangeListener{

	private RadioGroup vsOptionsRg, colorOptionsRg ;
//	private ADSThread adsThread ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chess_config_activity);
		initObject();
		initUI();
		initCustomUI();
		initialize();
	}
	
	
	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.playButton) {
			JChezz.getInstance().setDemo(false);
			startActivity(new Intent(this, PlayActivity.class));
		}
	}


	@Override
	public void initObject() { 

	}


	@Override
	public void initUI() {
		vsOptionsRg = (RadioGroup)findViewById(R.id.vsOptionsRg);
		colorOptionsRg = (RadioGroup)findViewById(R.id.colorOptionsRg);
		vsOptionsRg.setOnCheckedChangeListener(this);
		colorOptionsRg.setOnCheckedChangeListener(this);
		findViewById(R.id.playButton).setOnClickListener(this);
	}


	@Override
	public void initCustomUI() { }
	
	public void initialize(){
		if (JChezz.getInstance().isPlayingWithCom()) vsOptionsRg.check(R.id.vsComRbLeft);
		else vsOptionsRg.check(R.id.vsPlayerRbRight);
		
		if (JChezz.getInstance().getMyColor()==Color.WHITE) colorOptionsRg.check(R.id.whiteRbLeft);
		else colorOptionsRg.check(R.id.blackRbRight);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initialize();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		this.finish();
		System.exit(0);
		
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group.getId()==R.id.vsOptionsRg) {
			if (checkedId==R.id.vsComRbLeft) {
				JChezz.getInstance().setPlayingWithCom(true);
			}else if (checkedId==R.id.vsPlayerRbRight) {
				JChezz.getInstance().setPlayingWithCom(false);
			}
		}else if (group.getId()==R.id.colorOptionsRg) {
			if (checkedId==R.id.whiteRbLeft) {
				JChezz.getInstance().setMyColor(Color.WHITE);
			}else if (checkedId==R.id.blackRbRight) {
				JChezz.getInstance().setMyColor(Color.BLACK);
			}
		}
	}
}

package com.alfarabi.chessmaster;

import java.util.Timer;
import java.util.TimerTask;

import com.alfarabi.chessmaster.activities.DemoActivity;
import com.alfarabi.chessmaster.memoriez.JChezz;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;

public class StartActivity extends Activity {
	

	private Timer timer ;
	private int pc = 0 ;
	private TextView versionTv ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		versionTv = (TextView) findViewById(R.id.versionTv);
		try {
			versionTv.setText("Ver. "+getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				pc++;
				if (pc>=2) {
					nextActivity();
				}
			}
		}, 0, 1000);
		
		
	}
	
	public void nextActivity(){
		if (timer!=null) {
			timer.cancel();
			timer = null;
		}
		JChezz.getInstance().setDemo(true);
		startActivity(new Intent(this, DemoActivity.class));
		this.finish();
	}

	@Override
	protected void onDestroy() {
		if (timer!=null) {
			timer.cancel();
			timer = null;
		}
		super.onDestroy();
	}
	
	@Override
	public void onBackPressed() {
		if (timer!=null) {
			timer.cancel();
			timer = null;
		}
		this.finish();
		super.onBackPressed();
	}
	
}

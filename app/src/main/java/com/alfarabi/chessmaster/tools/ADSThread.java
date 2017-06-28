package com.alfarabi.chessmaster.tools;

import java.util.Calendar;

import android.util.Log;

import com.alfarabi.chessmaster.activities.ChessConfigActivity;

public class ADSThread extends Thread{
	
	ChessConfigActivity cca ;
	
	public boolean adsOn = false ;
	public boolean hasShow = false ;
	
	public ADSThread(ChessConfigActivity cca, boolean adsOn, boolean hasShow) {
		this.cca = cca;
		
	}
	
	public void startThread(){
		this.start();
	}
	
	@Override
	public void run() {
		super.run();
		try {
			
			while (true) {
				while (adsOn && !hasShow) {
					long now = Calendar.getInstance().getTimeInMillis();
					Log.i("count ads show", String.valueOf(now/(Math.random()*10)));
					if (now%(Math.random()*10)>3.0) {
						cca.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								adsOn = false ;
								hasShow = true ;
							}
						});
					}
					sleep(2000);
				}
				sleep(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

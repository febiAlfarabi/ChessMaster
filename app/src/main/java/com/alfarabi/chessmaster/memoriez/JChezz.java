package com.alfarabi.chessmaster.memoriez;

import java.io.Serializable;
import java.util.Calendar;

import android.graphics.Color;

public class JChezz implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940099028489995335L;
	public static final boolean MY_SESSION = true ;
	public static final boolean YOUR_SESSION = false ;
	
	private boolean playingWithCom = true, demo , session = MY_SESSION ;
	private int myColor = Color.WHITE;
	private int yourColor = Color.BLACK;
	private int staticLevel = 0 ;
	private int dynamicLevel = 0 ;
	private int maxLevel = 0;
	private static JChezz instance ;
	private long lastAdsShow = Calendar.getInstance().getTimeInMillis() ;
	
	public boolean isPlayingWithCom() {
		return playingWithCom;
	}
	public void setPlayingWithCom(boolean playingWithCom) {
		this.playingWithCom = playingWithCom;
	}
	public int getMyColor() {
		return myColor;
	}
	public void setMyColor(int myColor) {
		if (myColor==Color.WHITE) this.yourColor = Color.BLACK;
		else if (myColor==Color.BLACK) this.yourColor = Color.WHITE;
		
		this.myColor = myColor;
	}
	public int getYourColor() {
		return yourColor;
	}
	public void setYourColor(int yourColor) {
		if (yourColor==Color.WHITE) this.myColor = Color.BLACK;
		else if (yourColor==Color.BLACK) this.myColor = Color.WHITE;
		
		this.yourColor = yourColor;
	}
	public boolean isSession() {
		return session;
	}
	public void setSession(boolean session) {
		this.session = session;
	}
	public boolean isDemo() {
		return demo;
	}
	public void setDemo(boolean demo) {
		this.demo = demo;
	}
	public int getStaticLevel() {
		return staticLevel;
	}
	public void setStaticLevel(int staticLevel) {
		this.staticLevel = staticLevel;
	}
	public int getDynamicLevel() {
		return dynamicLevel;
	}
	public void setDynamicLevel(int dynamicLevel) {
		this.dynamicLevel = dynamicLevel;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	public long getLastAdsShow() {
		return lastAdsShow;
	}
	public void setLastAdsShow(long lastAdsShow) {
		this.lastAdsShow = lastAdsShow;
	}
	public static JChezz getInstance() {
		return instance;
	}
	static {
		instance = new JChezz();
	}

}

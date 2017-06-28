package com.alfarabi.chessmaster.media.theme;

import java.io.Serializable;

public class JDesign implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3730631821514741856L;
	private MThemes mthemes = new MThemes();
	private static JDesign instance ;
	
	
	public MThemes getMthemes() {
		return mthemes;
	}
	public void setMthemes(MThemes mthemes) {
		this.mthemes = mthemes;
	}
	
	static {
		instance = new JDesign();
	}
	
	public static JDesign getInstance() {
		return instance;
	}
	
}

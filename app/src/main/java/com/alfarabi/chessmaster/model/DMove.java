package com.alfarabi.chessmaster.model;

public class DMove extends History{
	
	public static final long LIMIT_FOR_MAX = -999999 , LIMIT_FOR_MIN = 999999 ;
	public Long alpha ;
	public Long beta ;
	public boolean alphaBetaBreak = false ;
	public boolean randomBreak = false ;

	public DMove() {
		super();
	}
}

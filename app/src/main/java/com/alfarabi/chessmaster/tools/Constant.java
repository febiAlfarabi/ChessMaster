package com.alfarabi.chessmaster.tools;

import java.util.HashMap;

import android.util.SparseIntArray;


public class Constant {
	
	public static final String DEV_EMAIL = "alfarabidwik@gmail.com";
	public static final String SENDER_EMAIL = "com.alfarabi.chessmaster@gmail.com";
	public static final String SENDER_EMAIL_PASSWORD = "gioibanez";
	public static final String KUISIONER_CHESS_MASTER = "KUISIONER CHESS MASTER";
	
	
			
	
	public static final int COLUMNS[][] =	{
												{18,1},{28,1},{38,1},{48,1},{58,1},{68,1},{78,1},{88,1},
												{17,2},{27,2},{37,2},{47,2},{57,2},{67,2},{77,2},{87,2},
												{16,2},{26,2},{36,3},{46,3},{56,3},{66,3},{76,2},{86,2},
												{15,2},{25,2},{35,3},{45,4},{55,4},{65,3},{75,2},{85,2},
												{14,2},{24,2},{34,3},{44,4},{54,4},{64,3},{74,2},{84,2},
												{13,2},{23,2},{33,3},{43,3},{53,3},{63,3},{73,2},{83,2},
												{12,2},{22,2},{32,2},{42,2},{52,2},{62,2},{72,2},{82,2},
												{11,1},{21,1},{31,1},{41,1},{51,1},{61,1},{71,1},{81,1},
												};
	
	
	public static final SparseIntArray MAPPING_COLUMN_VALUE = mapping();
	
	
	public static final SparseIntArray mapping(){
		SparseIntArray hashMap = new SparseIntArray();
		for (int i = 0; i < COLUMNS.length; i++) {
			hashMap.put(COLUMNS[i][0], COLUMNS[i][1]);
		}
		return hashMap;
	}
	
	public static final String MYSHAREDPREF = "MYSHAREDPREF";
	public static final String ANSWERED = "ANSWERED";


}

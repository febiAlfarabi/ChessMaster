package com.alfarabi.chessmaster.tools;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.alfarabi.chessmaster.model.Column;
import com.alfarabi.chessmaster.model.DMove;
import com.alfarabi.chessmaster.mviews.Board;
import com.alfarabi.chessmaster.mviews.Piece;

public final class Util {
	
//	static{
//		System.loadLibrary("ChessMaster");
//	}
	
	public static int getColumnLabel(float x, float y, int weight){
		Integer dotW = Board.MARGIN+((weight%8)/2);
		Integer dotH = Board.MARGIN+((weight%8)/2);
		boolean s = false ;
		
		for (int i = 1; i <=8; i++) {
			for (int j = 1; j <= 8; j++) {
				if ((x>dotW.floatValue() && x<Integer.valueOf(dotW+weight/8).floatValue())
						&& 
					(y>dotH.floatValue() && y<Integer.valueOf(dotH+weight/8).floatValue())) {
					s = true ;
					return matrixToInt(Board.COLUMNS[j], Board.ROWS[Board.ROWS.length-i]);
					
					
				}
				dotW = dotW+weight/8 ;
			}
			dotW = Board.MARGIN+((weight%8)/2);
			dotH = dotH+weight/8;
			if(s) break ;
		}
		
		return 0;
	}
	
//	public static native String locToString(int x, int y);
//	
//	public static native int[] locToInt(String loc);

	public static int matrixToInt(int x, int y){
		return (x*10)+y;
	}
	
	public static int[] singleToMatrix(int single){
		int[] ret = new int[2];
		ret[0] = single/10;
		ret[1] = single%10;
		return ret;
	}
	
	
	public static String getUserCountry(Context context) {
	    try {
	        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	        final String simCountry = tm.getSimCountryIso();
	        if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
	            return simCountry.toLowerCase(Locale.US);
	        }
	        else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
	            String networkCountry = tm.getNetworkCountryIso();
	            if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
	                return networkCountry.toLowerCase(Locale.US);
	            }
	        }
	    }
	    catch (Exception e) { }
	    return "NULL";
	}
	
	
//	public static native DMove getDecissionNative(int level, Long[] myPieces, Long[] yPieces, HashMap<String, Column> columns
//			, HashMap<String, Long> piecesInColumn, HashMap<Long, Piece> pieces);
//	
	
//	public static int[] locToInt(String loc){
//	int t[] = locToIntg(loc);
//	return t;
////	locToIntg(loc);
////	try {
////		String[] xy = {loc.split(":")[0],loc.split(":")[1]};
////		int[] xyi = new int[]{Integer.valueOf(xy[0]), Integer.valueOf(xy[1])};
////		return xyi;
////	
////	} catch (Exception e) {
////		e.printStackTrace();
////		return new int[]{0,0};
////	}
//}
	
//	public static String locToString(int x, int y){
//	return String.valueOf(x)+":"+String.valueOf(y);
//}
	
}

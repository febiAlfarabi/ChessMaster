package com.alfarabi.chessmaster.tools;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.widget.TextView;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.mviews.Board;
import com.alfarabi.chessmaster.mviews.Piece;
import com.alfarabi.chessmaster.mviews.dialog.PauseDialog;

public final class Calculazy {
	
	
	public static final int CHECK_MODE = 9;
	
	public static final char REMIS = 'R';
	public static final char P_ONE_WIN = '1';
	public static final char P_TWO_WIN = '2';
	public static final char FREE_PLAY = 'F';
	public static boolean hasChess = false ;
	
	
	public static char calculazorWhileGame(Board board){
		if (!JChezz.getInstance().isDemo()) {
			((TextView)board.pa.findViewById(R.id.yourNotifTv)).setText("");
			((TextView)board.pa.findViewById(R.id.myNotifTv)).setText("");
			
		}
		hasChess = false ;
		JChezz.getInstance().setDynamicLevel(0);
		for (int i = 0; i < Collectionz.myPiecez.length; i++) {
			int[] s = Mobilitiez.getPossiblePlaced(board, board.pieces.get(Collectionz.myPiecez[i]));
			for (int j = 0; j < s.length; j++) {
				for (int x = 0; x < Collectionz.yourPiecez.length; x++) {
					if (board.pieces.get(Collectionz.yourPiecez[x])!=null) {
						if (board.pieces.get(Collectionz.yourPiecez[x]).pTAG==Piece.KING) {
							hasChess = true ;
						}
						if (s[j]==board.pieces.get(Collectionz.yourPiecez[x]).loc) {
							JChezz.getInstance().setDynamicLevel(JChezz.getInstance().getStaticLevel());
							if (board.pieces.get(Collectionz.yourPiecez[x]).pTAG==Piece.KING) {
								if (!JChezz.getInstance().isDemo()) ((TextView)board.pa.findViewById(R.id.yourNotifTv)).setText("Check");
							}
						}
					}
				}
			}
		}
		if (!hasChess) {
			return P_ONE_WIN ;
		}
		
		hasChess = false ;
		for (int i = 0; i < Collectionz.yourPiecez.length; i++) {
			int[] s = Mobilitiez.getPossiblePlaced(board, board.pieces.get(Collectionz.yourPiecez[i]));
			for (int j = 0; j < s.length; j++) {
				for (int x = 0; x < Collectionz.myPiecez.length; x++) {
					if (board.pieces.get(Collectionz.myPiecez[x])!=null) {
						if (board.pieces.get(Collectionz.myPiecez[x]).pTAG==Piece.KING) {
							hasChess = true ;
						}
						if (s[j]==board.pieces.get(Collectionz.myPiecez[x]).loc) {
							JChezz.getInstance().setDynamicLevel(JChezz.getInstance().getStaticLevel());
							if (board.pieces.get(Collectionz.myPiecez[x]).pTAG==Piece.KING) {
								if (!JChezz.getInstance().isDemo()) ((TextView)board.pa.findViewById(R.id.myNotifTv)).setText("Check");
							}
						}
					}
				}
			}
		}
		
		if (!hasChess) {
			return P_TWO_WIN ;
		}

		return FREE_PLAY;
	}
	
	
	public static int getLevel(Board board){
		int result = 0 ;
		result = JChezz.getInstance().getDynamicLevel();
		if (result>2) {
			result = result-1;
			result = result*2;
		}

		int p1 = 0;
		int p2 = 0;
		int p3 = 0;
		
		for (int i = 0; i < Collectionz.yourPiecez.length; i++) {
			int m = Mobilitiez.getPossiblePlaced(board, board.pieces.get(Collectionz.yourPiecez[i])).length;
			p1+= m;
		}
		
		for (int i = 0; i < Collectionz.myPiecez.length; i++) {
			int m = Mobilitiez.getPossiblePlaced(board, board.pieces.get(Collectionz.myPiecez[i])).length;
			p2+= m;
		}
//		Log.i("################### P1", String.valueOf(p1));
//		Log.i("################### P2", String.valueOf(p2));
//		Log.i("################### P1*P2", String.valueOf(p1*p2));
		
		int total = 0 ;
		for (int i = 2; i <= result; i++) {
			total += p1*p2;
			if (total>1200) {
				result = i-(i%2);
				break ;
			}
			p3 = p1 ;
			p1 = p2 ;
			p2 = p3 ;
		}
//		Log.i("################### RESULT", String.valueOf(result));
		return result ;
	}
	
	
	
}

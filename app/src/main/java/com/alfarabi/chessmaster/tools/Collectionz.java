package com.alfarabi.chessmaster.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.graphics.Color;
import com.alfarabi.chessmaster.activities.BasePlayActivity;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.mviews.BaseBoardLayout;
import com.alfarabi.chessmaster.mviews.Board;
import com.alfarabi.chessmaster.mviews.Piece;
import com.nineoldandroids.view.ViewHelper;

public final class Collectionz implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6174865617271617245L;
	
	public static long[] myPiecez ; 
	public static long[] yourPiecez ;
	
	public static final void initStart(BasePlayActivity pa, BaseBoardLayout baseBoardLayout, Board board){
		myPiecez = new long[16];
		yourPiecez = new long[16];
		Calendar cons = Calendar.getInstance();
		int weight = board.initColumns();
		int y = 0;
		int m = 0;
		
		for (int i = 1; i <=8; i++) {
			if (Board.ROWS[Board.ROWS.length-i]==8) {
				for (int j = 1; j <= 8; j++) {
					Calendar c = Calendar.getInstance();
					long id = (c.getTimeInMillis()*cons.getTimeInMillis()*Double.doubleToLongBits(Math.random()))+Double.doubleToLongBits(Math.random());
					int colName = Util.matrixToInt(Board.COLUMNS[j], Board.ROWS[Board.ROWS.length-i]);
					
					if (Board.COLUMNS[j]==1||Board.COLUMNS[j]==8) {
						Piece piece = Piece.create(pa, Piece.ROOK, Piece.YOU, weight, id);
						ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
						ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
						piece.loc = Util.matrixToInt(Board.COLUMNS[j], Board.ROWS[Board.ROWS.length-i]) ;
						baseBoardLayout.addView(piece);
						yourPiecez[y] = board.addPiece(piece, colName);
						
						
					}else if (Board.COLUMNS[j]==2||Board.COLUMNS[j]==7) {
						Piece piece = Piece.create(pa, Piece.KNIGHT, Piece.YOU, weight, id);
						ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
						ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
						piece.loc = colName ;
						baseBoardLayout.addView(piece);
						yourPiecez[y] = board.addPiece(piece, colName);
						
					}else if (Board.COLUMNS[j]==3||Board.COLUMNS[j]==6) {
						Piece piece = Piece.create(pa, Piece.BISHOP, Piece.YOU, weight, id);
						ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
						ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
						piece.loc = colName ;
						baseBoardLayout.addView(piece);
						yourPiecez[y] = board.addPiece(piece, colName);
						
					}else if (Board.COLUMNS[j]==4) {
						if (JChezz.getInstance().getYourColor()==Color.BLACK) {
							Piece piece = Piece.create(pa, Piece.QUEEN, Piece.YOU, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							yourPiecez[y] = board.addPiece(piece, colName);
								
						}else if (JChezz.getInstance().getYourColor()==Color.WHITE){
							Piece piece = Piece.create(pa, Piece.KING, Piece.YOU, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							yourPiecez[y] = board.addPiece(piece, colName);
							
						}
					}else if (Board.COLUMNS[j]==5) {
						if (JChezz.getInstance().getYourColor()==Color.BLACK) {
							Piece piece = Piece.create(pa, Piece.KING, Piece.YOU, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							yourPiecez[y] = board.addPiece(piece, colName);
							
						}else if (JChezz.getInstance().getYourColor()==Color.WHITE){
							Piece piece = Piece.create(pa, Piece.QUEEN, Piece.YOU, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							yourPiecez[y] = board.addPiece(piece, colName);
								
						}
					}
					y++;
				}
		
			}else if (Board.ROWS[Board.ROWS.length-i]==7){
				for (int j = 1; j <= 8; j++) {
					Calendar c = Calendar.getInstance();
					long id = (c.getTimeInMillis()*cons.getTimeInMillis())+Double.doubleToLongBits(Math.random());
					int colName = Util.matrixToInt(Board.COLUMNS[j], Board.ROWS[Board.ROWS.length-i]);
					
					Piece piece = Piece.create(pa, Piece.PAWN, Piece.YOU, weight, id);
					ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
					ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
					piece.loc = colName ;
					baseBoardLayout.addView(piece);
					yourPiecez[y] = board.addPiece(piece, colName);
					y++;
				}
			
			}else if (Board.ROWS[Board.ROWS.length-i]==2){
				for (int j = 1; j <= 8; j++) {
					Calendar c = Calendar.getInstance();
					long id = (c.getTimeInMillis()*cons.getTimeInMillis())+Double.doubleToLongBits(Math.random());
					int colName = Util.matrixToInt(Board.COLUMNS[j], Board.ROWS[Board.ROWS.length-i]);
					
					Piece piece = Piece.create(pa, Piece.PAWN, Piece.ME, weight, id);
					ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
					ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
					piece.loc = colName ;
					baseBoardLayout.addView(piece);
					myPiecez[m] = board.addPiece(piece, colName);
					m++;
				}
			
			}else if (Board.ROWS[Board.ROWS.length-i]==1){
				for (int j = 1; j <= 8; j++) {
					Calendar c = Calendar.getInstance();
					long id = (c.getTimeInMillis()*cons.getTimeInMillis())+Double.doubleToLongBits(Math.random());
					int colName = Util.matrixToInt(Board.COLUMNS[j], Board.ROWS[Board.ROWS.length-i]);
					

					if (Board.COLUMNS[j]==1||Board.COLUMNS[j]==8) {
						Piece piece = Piece.create(pa, Piece.ROOK, Piece.ME, weight, id);
						ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
						ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
						piece.loc = colName ;
						baseBoardLayout.addView(piece);
						myPiecez[m] = board.addPiece(piece, colName);
							
					}else if (Board.COLUMNS[j]==2||Board.COLUMNS[j]==7) {
						Piece piece = Piece.create(pa, Piece.KNIGHT, Piece.ME, weight, id);
						ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
						ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
						piece.loc = colName ;
						baseBoardLayout.addView(piece);
						myPiecez[m] = board.addPiece(piece, colName);
						
					}else if (Board.COLUMNS[j]==3||Board.COLUMNS[j]==6) {
						Piece piece = Piece.create(pa, Piece.BISHOP, Piece.ME, weight, id);
						ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
						ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
						piece.loc = colName ;
						baseBoardLayout.addView(piece);
						myPiecez[m] = board.addPiece(piece, colName);
						
					}else if (Board.COLUMNS[j]==4) {
						if (JChezz.getInstance().getMyColor()==Color.BLACK) {
							Piece piece = Piece.create(pa, Piece.KING, Piece.ME, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							myPiecez[m] = board.addPiece(piece, colName);
							
						}else if (JChezz.getInstance().getMyColor()==Color.WHITE){
							Piece piece = Piece.create(pa, Piece.QUEEN, Piece.ME, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							myPiecez[m] = board.addPiece(piece, colName);
							
						}
					}else if (Board.COLUMNS[j]==5) {
						if (JChezz.getInstance().getMyColor()==Color.BLACK) {
							Piece piece = Piece.create(pa, Piece.QUEEN, Piece.ME, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							myPiecez[m] = board.addPiece(piece, colName);
							
						}else if (JChezz.getInstance().getMyColor()==Color.WHITE){
							Piece piece = Piece.create(pa, Piece.KING, Piece.ME, weight, id);
							ViewHelper.setTranslationX(piece, board.columns.get(colName).x);
							ViewHelper.setTranslationY(piece, board.columns.get(colName).y);
							piece.loc = colName ;
							baseBoardLayout.addView(piece);
							myPiecez[m] = board.addPiece(piece, colName);
							
						}
					}
					m++;
				}
			}
		}
		startupDefaulSorting(board);
	}

	public static void startupDefaulSorting(Board board){
		long[] mp = new long[myPiecez.length];
		int mcount = 0;
		for (int i = 0; i < Piece.SORTING.length; i++) {
			for (int j = 0; j < myPiecez.length; j++) {
				if (board.pieces.get(myPiecez[j]).pTAG==Piece.SORTING[i]) {
					mp[mcount] = myPiecez[j];
//					removeMyPiece(myPiecez[j]);
					mcount++;
				}
			}
		}
		myPiecez = Arrays.copyOf(mp, mp.length);
		
		
		long[] yp = new long[yourPiecez.length];
		int ycount = 0;
		for (int i = 0; i < Piece.SORTING.length; i++) {
			for (int j = 0; j < yourPiecez.length; j++) {
				if (board.pieces.get(yourPiecez[j]).pTAG==Piece.SORTING[i]) {
					yp[ycount] = yourPiecez[j];
//					removeYourPiece(yourPiecez[j]);
					ycount++;
				}
			}
		}
		yourPiecez = Arrays.copyOf(yp, yp.length);
		
	}

	
	public static void sorting(Board board, long key, boolean mine){
		if (mine==Piece.ME) {
			removeMyPiece(key);
			addMyPiece(key);
			int[] mob = Mobilitiez.getPossiblePlaced(board, board.pieces.get(key));
			for (int i = 0; i < mob.length; i++) {
				if (board.pieceInColumn.get(mob[i])!=null) {
					if (board.pieces.get(board.pieceInColumn.get(mob[i])).pMINE==Piece.YOU) {
						removeYourPiece(board.pieceInColumn.get(mob[i]));
						addYourPiece(board.pieceInColumn.get(mob[i]));
					}
				}
			}
		}else if (mine==Piece.YOU){
			removeYourPiece(key);
			addYourPiece(key);
			int[] mob = Mobilitiez.getPossiblePlaced(board, board.pieces.get(key));
			for (int i = 0; i < mob.length; i++) {
				if (board.pieceInColumn.get(mob[i])!=null) {
					if (board.pieces.get(board.pieceInColumn.get(mob[i])).pMINE==Piece.ME) {
						removeMyPiece(board.pieceInColumn.get(mob[i]));
						addMyPiece(board.pieceInColumn.get(mob[i]));
					}
				}
			}
		}
		
	}
	
	public static void removeYourPiece(long key){
		int size = 0;
		for (int i = 0; i < yourPiecez.length; i++) {
			if (yourPiecez[i]==key) {
				yourPiecez[i] = 0;
			}else{
				size++;
			}
		}
		if (size!=yourPiecez.length) {
			long[] aCopy = new long[size];
			int count = 0;
			for (int i = 0; i < yourPiecez.length; i++) {
				if (yourPiecez[i]!=0) {
					aCopy[count] = yourPiecez[i];
					count++;
				}
			}
			yourPiecez = Arrays.copyOf(aCopy, aCopy.length);
		}
		
	}
	
	public static void removeMyPiece(long key){
		int size = 0;
		for (int i = 0; i < myPiecez.length; i++) {
			if (myPiecez[i]==key) {
				myPiecez[i] = 0;
			}else{
				size++;
			}
		}
		if (size!=myPiecez.length) {
			long[] aCopy = new long[size];
			int count = 0;
			for (int i = 0; i < myPiecez.length; i++) {
				if (myPiecez[i]!=0) {
					aCopy[count] = myPiecez[i];
					count++;
				}
			}
			myPiecez = Arrays.copyOf(aCopy, aCopy.length);
		}
	}
	
	public static void addMyPiece(long key){
		long[] mp = new long[myPiecez.length+1];
		mp[0]=key;
		for (int i = 1; i < mp.length; i++) {
			mp[i] = myPiecez[i-1];
		}
		myPiecez = Arrays.copyOf(mp, mp.length);
//		myPiecez = Arrays.copyOf(myPiecez, myPiecez.length);
//		myPiecez[myPiecez.length-1] = key;
		
	}
	
	public static void addYourPiece(long key){
		long[] yp = new long[yourPiecez.length+1];
		yp[0]=key;
		for (int i = 1; i < yp.length; i++) {
			yp[i] = yourPiecez[i-1];
		}
		yourPiecez = Arrays.copyOf(yp, yp.length);
//		yourPiecez = Arrays.copyOf(yourPiecez, yourPiecez.length);
//		yourPiecez[yourPiecez.length-1] = key;
		
	}
	
}

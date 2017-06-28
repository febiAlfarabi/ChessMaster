package com.alfarabi.chessmaster.tools;

import java.util.HashMap;
import java.util.List;
import com.alfarabi.chessmaster.mviews.Board;
import com.alfarabi.chessmaster.mviews.Piece;

public final class JPoints {
	
	private static final HashMap<Character, Long> piecesValues = new HashMap<Character, Long>();
	
	public static long getPoints(Board board, boolean mine){
		// MIN
		long columnAndPiecesAndMobilityV = 
				getColumnAndPieceAndMobilityValue(board, Collectionz.yourPiecez) - getColumnAndPieceAndMobilityValue(board, Collectionz.myPiecez); 
		long threatValue = 0;
		//getThreateningValue(board, Piece.YOU)-getThreateningValue(board, Piece.ME)==0?getThreateningValue(board, mine):getThreateningValue(board, Piece.YOU)-getThreateningValue(board, Piece.ME);
//		if (mine==Piece.YOU) {
//			threatValue = -getThreateningValue(board, Piece.ME);
//		}else if (mine==Piece.ME) {
//			threatValue = -getThreateningValue(board, Piece.YOU);	
//		}
		return columnAndPiecesAndMobilityV+threatValue ;
	
	}

	private static long getColumnAndPieceAndMobilityValue(Board board, long[] piecesKey){
		long values = 0 ;
		for (int i = 0; i < piecesKey.length; i++) {
			if (board.pieces.get(piecesKey[i])!=null) {
				values += Constant.MAPPING_COLUMN_VALUE.get(board.pieces.get(piecesKey[i]).loc);
				values += getPiecesValue().get(board.pieces.get(piecesKey[i]).pTAG);
				values += Mobilitiez.getPossiblePlaced(board, board.pieces.get(piecesKey[i])).length;

			}
		}

		return values ;
	}
	
	private static long getThreateningValue(Board board, boolean mine){
		long value = 0;	
		long yThreaten = 0;
		long mThreaten = 0;
		
//		for (int i = 0; i < Collectionz.getMyPiecez().size(); i++) {
//			int
//		}
		if (mine==Piece.ME) {
			for (int i = 0; i < Collectionz.myPiecez.length; i++) {
				int[] s = Mobilitiez.getPossiblePlaced(board, board.pieces.get(Collectionz.myPiecez[i]));
				for (int j = 0; j < s.length; j++) {
					for (int x = 0; x < Collectionz.yourPiecez.length; x++) {
						if (board.pieces.get(Collectionz.yourPiecez[x])!=null) {
							if (s[j]==board.pieces.get(Collectionz.yourPiecez[x]).loc) {
								value += (getPiecesValue().get(board.pieces.get(Collectionz.yourPiecez[x]).pTAG))/Collectionz.yourPiecez.length;
							}
						}
					}
				}
			}
		}else if (mine==Piece.YOU) {
			for (int i = 0; i < Collectionz.yourPiecez.length; i++) {
				int[] s = Mobilitiez.getPossiblePlaced(board, board.pieces.get(Collectionz.yourPiecez[i]));
				for (int j = 0; j < s.length; j++) {
					for (int x = 0; x < Collectionz.myPiecez.length; x++) {
						if (board.pieces.get(Collectionz.myPiecez[x])!=null) {
							if (s[j]==board.pieces.get(Collectionz.myPiecez[x]).loc) {
								value += (getPiecesValue().get(board.pieces.get(Collectionz.myPiecez[x]).pTAG))/Collectionz.myPiecez.length;
							}
						}
					}
				}
			}
		}
		
		return value ;
	}
	
	public final static HashMap<Character, Long> getPiecesValue(){
		piecesValues.put(Piece.PAWN, Long.valueOf(100));
		piecesValues.put(Piece.ROOK, Long.valueOf(500));
		piecesValues.put(Piece.KNIGHT, Long.valueOf(300));
		piecesValues.put(Piece.BISHOP, Long.valueOf(300));
		piecesValues.put(Piece.QUEEN, Long.valueOf(900));
		piecesValues.put(Piece.KING, Long.valueOf(100000));
		return piecesValues ;
		
	}
}

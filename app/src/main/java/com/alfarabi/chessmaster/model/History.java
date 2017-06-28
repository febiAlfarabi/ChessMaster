package com.alfarabi.chessmaster.model;

import com.alfarabi.chessmaster.mviews.Piece;

public class History{
	
	public long piecesId ;
	public int from ;
	public int to ;
	public Piece eatedPiece;
	
	public History() {
		// TODO Auto-generated constructor stub
	}
	
	public History(long piecesId, int from, int to, Piece eatedPiece) {
		// TODO Auto-generated constructor stub
		this.piecesId = piecesId;
		this.from = from;
		this.to = to ;
		this.eatedPiece = eatedPiece ;
	}

	public History(long piecesId, int from, int to) {
		// TODO Auto-generated constructor stub
		this.piecesId = piecesId;
		this.from = from;
		this.to = to ;
	}
}

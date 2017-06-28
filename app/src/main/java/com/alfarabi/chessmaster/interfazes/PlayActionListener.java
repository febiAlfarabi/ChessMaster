package com.alfarabi.chessmaster.interfazes;

import com.alfarabi.chessmaster.mviews.Piece;

public interface PlayActionListener {
	
	public void removePieceFromBoard(Piece piece);
	public void onChangeSession(boolean session);
	
	
}

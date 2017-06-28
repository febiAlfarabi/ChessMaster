package com.alfarabi.chessmaster.mviews;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.memoriez.JChezz;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class Piece extends ImageView{
	
	public static final char PAWN = 'P', ROOK = 'R', KNIGHT = 'K', BISHOP = 'B', QUEEN = 'Q', KING = 'G';
	public static final char[] SORTING = {KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN};
	public static final int WHITE = Color.WHITE;
	public static final int BLACK = Color.BLACK;
	public static final boolean ME = true ;
	public static final boolean YOU = false ;
	public static final byte FREE = 0;
	public static final byte READY_TO_EATED = 1;
	public static final byte SELECTED = 2;
	
	public boolean threatned = false ;
	public char pTAG ; 
	public int loc ;
	public int pCOLOR ;
	public boolean pMINE ;
	public byte status = FREE;
	public long id ;
	
	public Piece(Context context) {
		super(context);
	}
	
	public void initDesignPiece(byte status){
		this.status = status ;
		
		if (pTAG==PAWN) {
			if (status==READY_TO_EATED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_pawn_preasured);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_pawn_preasured);
					
			}else if (status==SELECTED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_pawn_selected);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_pawn_selected);
				
			}else if (status==FREE) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_pawn);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_pawn);
				
			}
			
		}else if (pTAG==ROOK) {
			if (status==READY_TO_EATED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_rook_preasured);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_rook_preasured);
				
			}else if (status==SELECTED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_rook_selected);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_rook_selected);
				
			}else if (status==FREE) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_rook);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_rook);
				
			}
			
		}else if (pTAG==KNIGHT) {
			if (status==READY_TO_EATED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_knight_preasured);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_knight_preasured);
				
			}else if (status==SELECTED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_knight_selected);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_knight_selected);
				
			}else if (status==FREE) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_knight);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_knight);
					
			}
			
			
		}else if (pTAG==BISHOP) {
			if (status==READY_TO_EATED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_bishop_preasured);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_bishop_preasured);
				
			}else if (status==SELECTED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_bishop_selected);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_bishop_selected);
				
			}else if (status==FREE) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_bishop);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_bishop);
					
			}
			
		}else if (pTAG==QUEEN) {
			if (status==READY_TO_EATED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_queen_preasured);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_queen_preasured);
				
			}else if (status==SELECTED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_queen_selected);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_queen_selected);
				
			}else if (status==FREE) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_queen);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_queen);
					
			}
			
			
		}else if (pTAG==KING) {
			if (status==READY_TO_EATED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_king_preasured);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_king_preasured);
				
			}else if (status==SELECTED) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_king_selected);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_king_selected);
				
			}else if (status==FREE) {
				if (pCOLOR==Color.BLACK) this.setImageResource(R.drawable.black_king);
				else if (pCOLOR==Color.WHITE) this.setImageResource(R.drawable.white_king);
					
			}
		}
	}
	
	public static Piece create(Context context, char TAG,  boolean MINE, int weight, long id){
		Piece piece = new Piece(context);
		piece.pTAG = TAG;
		piece.pCOLOR = MINE==ME ? JChezz.getInstance().getMyColor():JChezz.getInstance().getYourColor();
		piece.pMINE = MINE ;
		piece.status = FREE ;
		piece.id = id ;
		piece.initDesignPiece(piece.status);
		piece.setLayoutParams(new LayoutParams(weight, weight));
		return piece;
	}
	

}

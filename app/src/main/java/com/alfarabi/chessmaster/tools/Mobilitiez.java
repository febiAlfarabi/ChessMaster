package com.alfarabi.chessmaster.tools;
import android.util.Log;

import com.alfarabi.chessmaster.mviews.Board;
import com.alfarabi.chessmaster.mviews.Piece;

public final class Mobilitiez {
	
	
	public final static int[] getPossiblePlaced(Board board, Piece piece){
		int[] res = new int[0];
		if (piece.pTAG==Piece.PAWN) {
			res =  minimumArray(pawnPossibleMove(board, piece));
			
		}else if (piece.pTAG==Piece.ROOK) {
			res =  minimumArray(rookPossibleMove(board, piece));
		
		}else if (piece.pTAG==Piece.KNIGHT) {
			res =  minimumArray(knightPossibleMove(board, piece));
		
		}else if (piece.pTAG==Piece.BISHOP) {
			res =  minimumArray(bishopPossibleMove(board, piece));
		
		}else if (piece.pTAG==Piece.QUEEN) {
			res =  minimumArray(queenPossibleMove(board, piece));
		
		}else if (piece.pTAG==Piece.KING) {
			res =  minimumArray(kingPossibleMove(board, piece));
		
		}
		return res ;
		
	}
	
	
	private final static int[] pawnPossibleMove(Board board, Piece piece){
		int[] moves = new int[4];
		
		int x = Util.singleToMatrix(piece.loc)[0];
		int y = Util.singleToMatrix(piece.loc)[1];
		
		if (piece.pMINE==Piece.ME) {
			if (y+1<=8) {
				if (board.pieceInColumn.get(Util.matrixToInt(x, y+1))==null) {
					moves[0] = Util.matrixToInt(x, y+1);
					if (y+2<=4) {
						if (board.pieceInColumn.get(Util.matrixToInt(x, y+2))==null) {
							moves[1] = Util.matrixToInt(x, y+2);
						}
					}
				}
				
			}
			
			
			if (x-1>=1&&y+1<=8) {
				if (board.pieceInColumn.get(Util.matrixToInt(x-1, y+1))!=null) {
					if (!board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y+1))).pMINE) {
						moves[2] = Util.matrixToInt(x-1, y+1);
					}
				}
			}
			if (x+1<=8&&y+1<=8) {
				if (board.pieceInColumn.get(Util.matrixToInt(x+1, y+1))!=null) {
					if (!board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y+1))).pMINE) {
						moves[3] = Util.matrixToInt(x+1, y+1);
					}
				}
			}
			
		}else if (piece.pMINE==Piece.YOU){
			
			if (y-1>=1) {
				if (board.pieceInColumn.get(Util.matrixToInt(x, y-1))==null) {
					moves[0] = Util.matrixToInt(x, y-1);
					
					if (y-2>=5) {
						if (board.pieceInColumn.get(Util.matrixToInt(x, y-2))==null) {
							moves[1] = Util.matrixToInt(x, y-2);
						}
					}
				}
				
			}
			
			
			if (x-1>=1&&y-1>=1) {
				if (board.pieceInColumn.get(Util.matrixToInt(x-1, y-1))!=null) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y-1))).pMINE) {
						moves[2] = Util.matrixToInt(x-1, y-1);
					}
				}
			}
			if (x+1<=8&&y-1>=1) {
				if (board.pieceInColumn.get(Util.matrixToInt(x+1, y-1))!=null) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y-1))).pMINE) {
						moves[3] = Util.matrixToInt(x+1, y-1);
					}
				}
			}
		}
		return moves;
	}

	private final static int[] rookPossibleMove(Board board, Piece piece){
		int[] moves = new int[32];
		int x = Util.singleToMatrix(piece.loc)[0];
		int y = Util.singleToMatrix(piece.loc)[1];
		int count = 0 ;
		for (int i = 1; i < 8; i++) {
			if (x+i<=8) {
				if (board.pieceInColumn.get(Util.matrixToInt(x+i, y))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+i, y))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x+i, y);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+i, y))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x+i, y);
						}
						count++;
						break ;
					} 
					
				}else{
					moves[i] = Util.matrixToInt(x+i, y);
				}
			}
			count++;
		}
		
		for (int i = 1; i < 8; i++) {
			if (y+i<=8) {
				if (board.pieceInColumn.get(Util.matrixToInt(x, y+i))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y+i))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x, y+i);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y+i))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x, y+i);
						}
						count++;
						break ;
					} 
					
				}else{
					moves[count] = Util.matrixToInt(x, y+i);
				}
			}
			count++;
		}
		
		for (int i = 1; i < 8; i++) {
			if (y-i>=1) {
				if (board.pieceInColumn.get(Util.matrixToInt(x, y-i))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y-i))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x, y-i);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y-i))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x, y-i);
						}
						count++;
						break ;
					} 
					
				}else{
					moves[count] = Util.matrixToInt(x, y-i);
				}
			}
			count++;
		}
		
		for (int i = 1; i < 8; i++) {
			if (x-i>=1) {
				if (board.pieceInColumn.get(Util.matrixToInt(x-i, y))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-i, y))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x-i, y);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-i, y))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x-i, y);
						} 
						count++;
						break ;
					} 
					
				}else{
					moves[count] = Util.matrixToInt(x-i, y);
				}
			}
			count++;
		}
		
		
		return moves ;
	}
	
	private final static int[] knightPossibleMove(Board board, Piece piece){
		int[] moves = new int[8];
		int x = Util.singleToMatrix(piece.loc)[0];
		int y = Util.singleToMatrix(piece.loc)[1];
		
		if (x-1>=1 && y+2<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x-1, y+2))==null) {
				moves[0] = Util.matrixToInt(x-1, y+2);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y+2))).pMINE==Piece.YOU) {
						moves[0] = Util.matrixToInt(x-1, y+2);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y+2))).pMINE==Piece.ME) {
						moves[0] = Util.matrixToInt(x-1, y+2);
					}
				}
			}
			
		}
		
		if (x+1<=8 && y+2<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x+1, y+2))==null) {
				moves[1] = Util.matrixToInt(x+1, y+2);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y+2))).pMINE==Piece.YOU) {
						moves[1] = Util.matrixToInt(x+1, y+2);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y+2))).pMINE==Piece.ME) {
						moves[1] = Util.matrixToInt(x+1, y+2);
					}
				}
			}
			
		}
		
		if (x-1>=1 && y-2>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x-1, y-2))==null) {
				moves[2] = Util.matrixToInt(x-1, y-2);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y-2))).pMINE==Piece.YOU) {
						moves[2] = Util.matrixToInt(x-1, y-2);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y-2))).pMINE==Piece.ME) {
						moves[2] = Util.matrixToInt(x-1, y-2);
					}
				}
			}
			
		}
		
		if (x+1<=8 && y-2>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x+1, y-2))==null) {
				moves[3] = Util.matrixToInt(x+1, y-2);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y-2))).pMINE==Piece.YOU) {
						moves[3] = Util.matrixToInt(x+1, y-2);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y-2))).pMINE==Piece.ME) {
						moves[3] = Util.matrixToInt(x+1, y-2);
					}
				}
			}
			
		}
		
//		============================
		
		if (x-2>=1 && y+1<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x-2, y+1))==null) {
				moves[4] = Util.matrixToInt(x-2, y+1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-2, y+1))).pMINE==Piece.YOU) {
						moves[4] = Util.matrixToInt(x-2, y+1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-2, y+1))).pMINE==Piece.ME) {
						moves[4] = Util.matrixToInt(x-2, y+1);
					}
				}
			}
			
		}
		
		if (x+2<=8 && y+1<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x+2, y+1))==null) {
				moves[5] = Util.matrixToInt(x+2, y+1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+2, y+1))).pMINE==Piece.YOU) {
						moves[5] = Util.matrixToInt(x+2, y+1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+2, y+1))).pMINE==Piece.ME) {
						moves[5] = Util.matrixToInt(x+2, y+1);
					}
				}
			}
			
		}
		
		if (x-2>=1 && y-1>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x-2, y-1))==null) {
				moves[6] = Util.matrixToInt(x-2, y-1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-2, y-1))).pMINE==Piece.YOU) {
						moves[6] = Util.matrixToInt(x-2, y-1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-2, y-1))).pMINE==Piece.ME) {
						moves[6] = Util.matrixToInt(x-2, y-1);
					}
				}
			}
			
		}
		
		if (x+2<=8 && y-1>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x+2, y-1))==null) {
				moves[7] = Util.matrixToInt(x+2, y-1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+2, y-1))).pMINE==Piece.YOU) {
						moves[7] = Util.matrixToInt(x+2, y-1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+2, y-1))).pMINE==Piece.ME) {
						moves[7] = Util.matrixToInt(x+2, y-1);
					}
				}
			}
			
		}
		
		return moves ;
	}
	
	private final static int[] bishopPossibleMove(Board board, Piece piece){
		int[] moves = new int[32];
		int x = Util.singleToMatrix(piece.loc)[0];
		int y = Util.singleToMatrix(piece.loc)[1];
		int count = 0 ;
		for (int i = 1; i < 8; i++) {
			if (x+i<=8&&y+i<=8) {
				if (board.pieceInColumn.get(Util.matrixToInt(x+i, y+i))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+i, y+i))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x+i, y+i);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+i, y+i))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x+i, y+i);
						}
						count++;
						break ;
					} 
					
				}else{
					moves[count] = Util.matrixToInt(x+i, y+i);
				}
			}
			count++;
		}
		
		for (int i = 1; i < 8; i++) {
			if (x-i>=1 && y+i<=8) {
				if (board.pieceInColumn.get(Util.matrixToInt(x-i, y+i))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-i, y+i))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x-i, y+i);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-i, y+i))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x-i, y+i);
						}
						count++;
						break ;
					} 
					
				}else{
					moves[count] = Util.matrixToInt(x-i, y+i);
				}
			}
			count++;
		}
		
		for (int i = 1; i < 8; i++) {
			if (x+i<=8 && y-i>=1) {
				if (board.pieceInColumn.get(Util.matrixToInt(x+i, y-i))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+i, y-i))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x+i, y-i);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+i, y-i))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x+i, y-i);
						}
						count++;
						break ;
					} 
					
				}else{
					moves[count] = Util.matrixToInt(x+i, y-i);
				}
			}
			count++;
		}
		
		for (int i = 1; i < 8; i++) {
			if (x-i>=1&&y-i>=1) {
				if (board.pieceInColumn.get(Util.matrixToInt(x-i, y-i))!=null) {
					if (piece.pMINE==Piece.ME) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-i, y-i))).pMINE==Piece.YOU) {
							moves[count] = Util.matrixToInt(x-i, y-i);
						}
						count++;
						break ;
					}else if (piece.pMINE==Piece.YOU) {
						if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-i, y-i))).pMINE==Piece.ME) {
							moves[count] = Util.matrixToInt(x-i, y-i);
						}
						count++;
						break ;
					} 
					
				}else{
					moves[count] = Util.matrixToInt(x-i, y-i);
				}
			}
			count++;
		}
		return moves ;
	}
	
	private final static int[] queenPossibleMove(Board board, Piece piece){
		int[] moves = new int[128];
		int count = 0 ;
		int[] bishops = minimumArray(bishopPossibleMove(board, piece));
		for (int i = 0; i < bishops.length; i++) {
			moves[count] = bishops[i];
			count++;
		}
		int[] rooks = minimumArray(rookPossibleMove(board, piece));
		for (int i = 0; i < rooks.length; i++) {
			moves[count] = rooks[i];
			count++;
		}
		return moves ;
	}
	
	private final static int[] kingPossibleMove(Board board, Piece piece){
		int[] moves = new int[8];
		int x = Util.singleToMatrix(piece.loc)[0];
		int y = Util.singleToMatrix(piece.loc)[1];
		
		if (x+1<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x+1, y))==null) {
				moves[0] = Util.matrixToInt(x+1, y);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y))).pMINE==Piece.YOU) {
						moves[0] = Util.matrixToInt(x+1, y);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y))).pMINE==Piece.ME) {
						moves[0] = Util.matrixToInt(x+1, y);
					}
				}
			}
			
		}
		if (x-1>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x-1, y))==null) {
				moves[1] = Util.matrixToInt(x-1, y);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y))).pMINE==Piece.YOU) {
						moves[1] = Util.matrixToInt(x-1, y);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y))).pMINE==Piece.ME) {
						moves[1] = Util.matrixToInt(x-1, y);
					}
				}
			}
			
		}
		
		
		if (y+1<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x, y+1))==null) {
				moves[2] = Util.matrixToInt(x, y+1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y+1))).pMINE==Piece.YOU) {
						moves[2] = Util.matrixToInt(x, y+1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y+1))).pMINE==Piece.ME) {
						moves[2] = Util.matrixToInt(x, y+1);
					}
				}
			}
			
		}
		
		if (y-1>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x, y-1))==null) {
				moves[3] = Util.matrixToInt(x, y-1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y-1))).pMINE==Piece.YOU) {
						moves[3] = Util.matrixToInt(x, y-1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x, y-1))).pMINE==Piece.ME) {
						moves[3] = Util.matrixToInt(x, y-1);
					}
				}
			}
			
		}
		
		if (x+1<=8 && y+1<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x+1, y+1))==null) {
				moves[4] = Util.matrixToInt(x+1, y+1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y+1))).pMINE==Piece.YOU) {
						moves[4] = Util.matrixToInt(x+1, y+1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y+1))).pMINE==Piece.ME) {
						moves[4] = Util.matrixToInt(x+1, y+1);
					}
				}
			}
			
		}
		
		if (x+1<=8 && y-1>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x+1, y-1))==null) {
				moves[5] = Util.matrixToInt(x+1, y-1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y-1))).pMINE==Piece.YOU) {
						moves[5] = Util.matrixToInt(x+1, y-1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x+1, y-1))).pMINE==Piece.ME) {
						moves[5] = Util.matrixToInt(x+1, y-1);
					}
				}
			}
			
		}
		
		if (x-1>=1 && y-1>=1) {
			if (board.pieceInColumn.get(Util.matrixToInt(x-1, y-1))==null) {
				moves[6] = Util.matrixToInt(x-1, y-1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y-1))).pMINE==Piece.YOU) {
						moves[6] = Util.matrixToInt(x-1, y-1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y-1))).pMINE==Piece.ME) {
						moves[6] = Util.matrixToInt(x-1, y-1);
					}
				}
			}
			
		}
		
		if (x-1>=1 && y+1<=8) {
			if (board.pieceInColumn.get(Util.matrixToInt(x-1, y+1))==null) {
				moves[7] = Util.matrixToInt(x-1, y+1);
			}else{
				if (piece.pMINE==Piece.ME) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y+1))).pMINE==Piece.YOU) {
						moves[7] = Util.matrixToInt(x-1, y+1);
					}
				}else if (piece.pMINE==Piece.YOU) {
					if (board.pieces.get(board.pieceInColumn.get(Util.matrixToInt(x-1, y+1))).pMINE==Piece.ME) {
						moves[7] = Util.matrixToInt(x-1, y+1);
					}
				}
			}
			
		}
		
		return moves ;
	}
	
	
	public static int[] minimumArray(int[] in){
		int size = 0;
		int[] ret ;
		for (int i = 0; i < in.length; i++) {
			if (in[i]!=0) {
				size++;
			}
		}
		ret = new int[size];
		int s = 0;
		for (int i = 0; i < in.length; i++) {
			if (in[i]!=0) {
				ret[s] = in[i];
				s++;
			}
		}
		return ret;
	}

}

package com.alfarabi.chessmaster.aic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.alfarabi.chessmaster.activities.BasePlayActivity;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.memoriez.JHistoriez;
import com.alfarabi.chessmaster.model.DMove;
import com.alfarabi.chessmaster.model.History;
import com.alfarabi.chessmaster.mviews.Piece;
import com.alfarabi.chessmaster.tools.Calculazy;
import com.alfarabi.chessmaster.tools.Collectionz;
import com.alfarabi.chessmaster.tools.JPoints;
import com.alfarabi.chessmaster.tools.Mobilitiez;
import com.alfarabi.chessmaster.tools.Util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public final class MinimaxAB extends AsyncTask<Boolean, Void, DMove>{
	
	/* Minimax
	 *		 /  \
	 * 		/\  /\
	 * 	   /\/\/\/\
	 * */
	public static final String TAG = "Aic";
	
	private BasePlayActivity pa ;
	private boolean player ;
	private int count =  0;
	public MinimaxAB(BasePlayActivity pa) {
		this.pa = pa ;
	}
	
	@Override
	protected DMove doInBackground(Boolean... b) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		this.player = b[0];
		count =  0;  
		DMove dMove = getDecissionMove(Calculazy.getLevel(pa.board), new DMove(), player, JChezz.getInstance().isSession());
		
		return dMove;
	}
	
	@Override
	protected void onPostExecute(DMove dMove) {
		super.onPostExecute(dMove);
		if (dMove==null) {
			return ;
		}
		
		if (!isCancelled()) {
//			Toast.makeText(pa, "Level : "+String.valueOf(Calculazy.getLevel(pa.board))+", count"+String.valueOf(count), Toast.LENGTH_SHORT).show();
			
			if (player==Piece.ME) {
				int[] possiblePlaced = Mobilitiez.getPossiblePlaced(this.pa.board, this.pa.board.pieces.get(dMove.piecesId));
				this.pa.board.onColumnSelected(possiblePlaced, dMove.piecesId, false);
				this.pa.board.movePieceTo(dMove.to, dMove.piecesId, true);
				this.pa.onChangeSession(JChezz.YOUR_SESSION);
				
			}else if (player==Piece.YOU) {
				int[] possiblePlaced = Mobilitiez.getPossiblePlaced(this.pa.board, this.pa.board.pieces.get(dMove.piecesId));
				this.pa.board.onColumnSelected(possiblePlaced, dMove.piecesId, false);
				this.pa.board.movePieceTo(dMove.to, dMove.piecesId, true);
				this.pa.onChangeSession(JChezz.MY_SESSION);
			}
			JHistoriez.addHistory(dMove);
		}
	}

	
	
	private DMove getDecissionMove(int deep, DMove dMove, boolean mine, boolean turn){
		if (mine==Piece.ME) dMove = myDecissionMove(deep, dMove, turn);
		
		else if (mine==Piece.YOU) dMove = yourDecissionMove(deep, dMove, turn);
		
		return dMove;
	}
	
	public synchronized DMove yourDecissionMove(int deep, DMove dMove, boolean turn){
		Long alpha = dMove.alpha;
		dMove.alpha = null;
		
		long[] yPiecesKey = Arrays.copyOf(Collectionz.yourPiecez, Collectionz.yourPiecez.length);
		
		for (int i = 0; i < yPiecesKey.length; i++) {
			if (dMove.alphaBetaBreak==true||dMove.randomBreak==true||isCancelled()) {
				break ;
			}
			if (this.pa.board.pieces.get(yPiecesKey[i])!=null) {

				int[] possiblePlaced = Mobilitiez.getPossiblePlaced(this.pa.board, this.pa.board.pieces.get(yPiecesKey[i]));
				
				for (int j = 0; j < possiblePlaced.length; j++) {
					
					int fromColumn = this.pa.board.pieces.get(yPiecesKey[i]).loc;
					this.pa.board.onColumnSelected(possiblePlaced, yPiecesKey[i], false);
					Piece pieceEnemy = null;
					if (this.pa.board.isEnemyReadyToEated(possiblePlaced[j])) {
						pieceEnemy = this.pa.board.pieces.get(this.pa.board.pieceInColumn.get(possiblePlaced[j]));
					}
					this.pa.board.movePieceTo(possiblePlaced[j], yPiecesKey[i], false);
					Collectionz.sorting(pa.board, yPiecesKey[i], Piece.YOU);
					long value = JPoints.getPoints(this.pa.board, Piece.YOU);
					if (alpha!=null) {
						if (value>=alpha) {
							this.pa.board.lastMove(new History(yPiecesKey[i], fromColumn, possiblePlaced[j], pieceEnemy), false);
							Collectionz.sorting(pa.board, yPiecesKey[i], Piece.YOU);
							dMove.alphaBetaBreak=true;
//							dMove.alpha=value;
							dMove.alpha=alpha;
							break ;
						}
					}
					
					if (deep>1) {
						DMove nextDMove = new DMove();
						nextDMove.beta=dMove.alpha;
//						nextDMove.setAlpha(dMove.alpha);
						nextDMove = getDecissionMove(deep-1, nextDMove, Piece.ME, turn); 
						
						if (dMove.alpha==null) {
							dMove.piecesId=yPiecesKey[i];
							dMove.alpha=nextDMove.beta;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							
						}else if (nextDMove.beta>dMove.alpha) {
							dMove.piecesId=yPiecesKey[i];
							dMove.alpha=nextDMove.beta;//value
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
						}
					
					}else if(deep==1){
						if (dMove.alpha==null) {
							dMove.piecesId=yPiecesKey[i];
							dMove.alpha=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							
						}else if (value>dMove.alpha) { // 
							dMove.piecesId=yPiecesKey[i];
							dMove.alpha=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
								
						}
						
					}else if(deep==0){
						
						if (dMove.alpha==null) {
							dMove.piecesId=yPiecesKey[i];
							dMove.alpha=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
						
						}else if (Math.random()<0.5&&Calendar.getInstance().getTimeInMillis()%2==0&&value>dMove.alpha){
							dMove.piecesId=yPiecesKey[i];
							dMove.alpha=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							dMove.randomBreak=true;
							this.pa.board.lastMove(new History(yPiecesKey[i], fromColumn, possiblePlaced[j], pieceEnemy), false);
							break ;
						}
					}
					count++;
					this.pa.board.lastMove(new History(yPiecesKey[i], fromColumn, possiblePlaced[j], pieceEnemy), false);
					Collectionz.sorting(pa.board, yPiecesKey[i], Piece.YOU);
				}
			}
		}
		return dMove;
	}
	
	public synchronized DMove myDecissionMove(int deep, DMove dMove, boolean turn){
		Long beta = dMove.beta;
		dMove.beta=null;
		long[] myPiecesKey = Arrays.copyOf(Collectionz.myPiecez, Collectionz.myPiecez.length);
		
		for (int i = 0; i < myPiecesKey.length; i++) {
			if (dMove.alphaBetaBreak==true||dMove.randomBreak==true||isCancelled()) {
				break ;
			}
			if (this.pa.board.pieces.get(myPiecesKey[i])!=null) {
				
				int[] possiblePlaced = Mobilitiez.getPossiblePlaced(this.pa.board, this.pa.board.pieces.get(myPiecesKey[i]));
				for (int j = 0; j < possiblePlaced.length; j++) {
					
					int fromColumn = this.pa.board.pieces.get(myPiecesKey[i]).loc;
					this.pa.board.onColumnSelected(possiblePlaced, myPiecesKey[i], false);
					Piece pieceEnemy = null;
					if (this.pa.board.isEnemyReadyToEated(possiblePlaced[j])) {
						pieceEnemy = this.pa.board.pieces.get(this.pa.board.pieceInColumn.get(possiblePlaced[j]));
					}
					this.pa.board.movePieceTo(possiblePlaced[j], myPiecesKey[i], false);
					Collectionz.sorting(pa.board, myPiecesKey[i], Piece.ME);
										
					long value = JPoints.getPoints(this.pa.board, Piece.YOU);
					if (beta!=null) {
						if (value<=beta) {
							this.pa.board.lastMove(new History(myPiecesKey[i], fromColumn, possiblePlaced[j], pieceEnemy), false);
							Collectionz.sorting(pa.board, myPiecesKey[i], Piece.ME);
							dMove.alphaBetaBreak=true;
//							dMove.setBeta(value);
							dMove.beta=beta;
							break ;
						}
					}
					if (deep>1) {
						DMove nextDMove = new DMove();
//						nextDMove.setBeta(dMove.beta);
						nextDMove.alpha=dMove.beta;
						
						nextDMove = getDecissionMove(deep-1, nextDMove, Piece.YOU, turn); 
						
						if (dMove.beta==null) {
							
							dMove.piecesId=myPiecesKey[i];
							dMove.beta=nextDMove.alpha;
							dMove.from=fromColumn;
							dMove.to = possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							
						}else if (nextDMove.alpha<dMove.beta){
							dMove.piecesId=myPiecesKey[i];
							dMove.beta=nextDMove.alpha;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							
						}
												
					}else if(deep==1){
						
						if (dMove.beta==null) {
							dMove.piecesId=myPiecesKey[i];
							dMove.beta=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							
						}else if (value<dMove.beta) { //
							dMove.piecesId=myPiecesKey[i];
							dMove.beta=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							
						}
						
					}else if(deep==0){
						if (dMove.beta==null) {
							dMove.piecesId=myPiecesKey[i];
							dMove.beta=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
						
						}else if (Math.random()<0.5&&Calendar.getInstance().getTimeInMillis()%2==0&&value<dMove.beta){
							dMove.piecesId=myPiecesKey[i];
							dMove.beta=value;
							dMove.from=fromColumn;
							dMove.to=possiblePlaced[j];
							dMove.eatedPiece=pieceEnemy;
							dMove.randomBreak=true;
							this.pa.board.lastMove(new History(myPiecesKey[i], fromColumn, possiblePlaced[j], pieceEnemy), false);
							break ;
						}
					}
					count++;
					this.pa.board.lastMove(new History(myPiecesKey[i], fromColumn, possiblePlaced[j], pieceEnemy), false);
					Collectionz.sorting(pa.board, myPiecesKey[i], Piece.ME);
				}
			}
		}
		return dMove;
	}

}

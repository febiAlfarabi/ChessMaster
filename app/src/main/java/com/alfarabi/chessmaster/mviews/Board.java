package com.alfarabi.chessmaster.mviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.alfarabi.chessmaster.activities.BasePlayActivity;
import com.alfarabi.chessmaster.media.MSound;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.memoriez.JHistoriez;
import com.alfarabi.chessmaster.model.Column;
import com.alfarabi.chessmaster.model.History;
import com.alfarabi.chessmaster.tools.Collectionz;
import com.alfarabi.chessmaster.tools.DimensionTools;
import com.alfarabi.chessmaster.tools.Mobilitiez;
import com.alfarabi.chessmaster.tools.Util;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Board extends View{

	public static final int M_BLACK = Color.parseColor("#231F1F");
	public static final int M_WHITE = Color.parseColor("#FFFFFF95");
	public static final int COLUMNS[] = {0,1,2,3,4,5,6,7,8};
	public static final int ROWS[] = {0,1,2,3,4,5,6,7,8};
	public static final long DEFAULT_LONG = -1l;
	public int windowWeight = 0;
	private int boardWeight, columnWeight ;
	public static final int MARGIN = 10 ;
	public HashMap<Integer, Column> columns = new HashMap<Integer, Column>();
	public HashMap<Integer, Long> pieceInColumn = new HashMap<Integer, Long>();
	public HashMap<Long, Piece> pieces = new HashMap<Long, Piece>();
	private final Paint paint = new Paint();
	private long pieceSelected = DEFAULT_LONG;
	private int[] possiblePlaced = new int[0];
	private RectF rec = new RectF();
	public BasePlayActivity pa ;
	private MSound mSound ;
	
	
//	FOR REAL TESTING ON DEVICE
	public Board(BasePlayActivity pa) {
		super(pa.getApplicationContext());
		this.pa = pa ;
		windowWeight = DimensionTools.countAndGetBoardWeight(pa);
		boardWeight = windowWeight-MARGIN*2;
		columnWeight = (boardWeight-(boardWeight%8))/8 ;
		mSound = new MSound(pa);
		invalidate();
	}

//	JUST FOR EDITING DEVELOPMENT MODE
	public Board(Context context, AttributeSet attrs) {
		super(context, attrs);
		windowWeight = 240;//DimensionTools.countAndGetBoardWeight(context);
		boardWeight = windowWeight-MARGIN*2;
		columnWeight = (boardWeight-(boardWeight%8))/8 ;
//		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int columnSelected = Util.getColumnLabel(event.getX(), event.getY(), boardWeight);
		
		if (JChezz.getInstance().isDemo()||columnSelected==0) {
			// BREAK JIKA SEDANG MODE DEMO COLUMN YANG DI KLIK TIDAK TERDEFINISI NAMANYA / DILUAR AREAN PAPAN
			return false ;
		}else{
			
			if (JChezz.getInstance().isSession()==JChezz.MY_SESSION) {
				
				if (pieceInColumn.get(columnSelected) != null) {
					if (pieceSelected==DEFAULT_LONG) {
						if (pieces.get(pieceInColumn.get(columnSelected)).pMINE==Piece.ME) {
							pieceSelected = pieceInColumn.get(columnSelected) ;
							possiblePlaced = Mobilitiez.getPossiblePlaced(this, pieces.get(pieceInColumn.get(columnSelected)));
							pieces.get(pieceInColumn.get(columnSelected)).initDesignPiece(Piece.SELECTED);
							onColumnSelected(possiblePlaced, pieceSelected, true);
						}
					}else{
						if (pieceSelected==pieceInColumn.get(columnSelected)) {
							pieceSelected = DEFAULT_LONG;
							pieces.get(this.pieceInColumn.get(columnSelected)).initDesignPiece(Piece.FREE);
							onColumnUnselected(true);
						
						}else if (pieceSelected!=pieceInColumn.get(columnSelected) && columns.get(columnSelected).readyToPlaced){
							addHistoryPlayerMove(pieceSelected, pieces.get(pieceSelected).loc, columnSelected, pieces.get(pieceInColumn.get(columnSelected)));
							movePieceTo(columnSelected, pieceSelected, true);
							pa.onChangeSession(JChezz.YOUR_SESSION);
							pieceSelected = DEFAULT_LONG;
						}
					}
					
				}else if (pieceInColumn.get(columnSelected) == null) {
					if (pieces.get(pieceSelected) != null) {
						if (columns.get(columnSelected).readyToPlaced) {
							addHistoryPlayerMove(pieceSelected, pieces.get(pieceSelected).loc, columnSelected, null);
							movePieceTo(columnSelected, pieceSelected, true);
							pa.onChangeSession(JChezz.YOUR_SESSION);
							pieceSelected = DEFAULT_LONG;
						}
					}
				}
				
			}else if (JChezz.getInstance().isSession()==JChezz.YOUR_SESSION) {
				
				if (JChezz.getInstance().isPlayingWithCom()) return false; // Disable Klik Jika melawan komputer 
				if (pieceInColumn.get(columnSelected) != null) {
					if (pieceSelected==DEFAULT_LONG) {
						if (pieces.get(this.pieceInColumn.get(columnSelected)).pMINE==Piece.YOU) {
							pieceSelected = pieceInColumn.get(columnSelected) ;
							possiblePlaced = Mobilitiez.getPossiblePlaced(this, pieces.get(pieceInColumn.get(columnSelected)));
							pieces.get(this.pieceInColumn.get(columnSelected)).initDesignPiece(Piece.SELECTED);
							onColumnSelected(possiblePlaced, pieceSelected, true);
						}
					
					}else{
						if (pieceSelected==pieceInColumn.get(columnSelected)) {
							pieceSelected = DEFAULT_LONG;
							pieces.get(pieceInColumn.get(columnSelected)).initDesignPiece(Piece.FREE);
							onColumnUnselected(true);
						}else if (pieceSelected!=pieceInColumn.get(columnSelected) && columns.get(columnSelected).readyToPlaced){
								addHistoryPlayerMove(pieceSelected, pieces.get(pieceSelected).loc, columnSelected, pieces.get(pieceInColumn.get(columnSelected)));
								movePieceTo(columnSelected, pieceSelected, true);
								pa.onChangeSession(JChezz.MY_SESSION);
								pieceSelected = DEFAULT_LONG;
						}
					}
					
				}else if (pieceInColumn.get(columnSelected) == null) {
					if (pieces.get(pieceSelected) != null) {
						if (columns.get(columnSelected).readyToPlaced) {
							addHistoryPlayerMove(pieceSelected, pieces.get(pieceSelected).loc, columnSelected, null);
							movePieceTo(columnSelected, pieceSelected, true);
							pa.onChangeSession(JChezz.MY_SESSION);
							pieceSelected = DEFAULT_LONG;
						}
					}
				}
			}
		}
		return super.onTouchEvent(event);
	}
	
	public void movePieceTo(int loc, long key, boolean draw){
		if (isEnemyReadyToEated(loc)) {
			eatEnemy(loc, draw);
		}
		if (draw) {
			if (pieces.get(key)!=null) {
				ViewPropertyAnimator.animate(pieces.get(key)).translationX(columns.get(loc).x).setDuration(400);
				ViewPropertyAnimator.animate(pieces.get(key)).translationY(columns.get(loc).y).setDuration(400);
				pieces.get(key).initDesignPiece(Piece.FREE);
				mSound.playClick();
			}
		}
		pieces.get(key).status=Piece.FREE;
		onColumnUnselected(draw);
		pieceInColumn.remove(pieces.get(key).loc);
		pieces.get(key).loc = loc ;
		pieceInColumn.put(loc, key);
	}
	
	public void onColumnSelected(int[] possiblePlaced, long pieceSelected, boolean draw){
		if (draw) {
			for (int i = 0; i < possiblePlaced.length; i++) {
				if (columns.get(possiblePlaced[i])!=null) {
					columns.get(possiblePlaced[i]).readyToPlaced=true;
							
				}
			}
			invalidate();
		}
		getPossiblePieceOfEnemyPreasured(possiblePlaced, pieceSelected, draw);
	}
	
	public void onColumnUnselected(boolean draw){
		for (int i = 0; i < possiblePlaced.length; i++) {
			if (draw) {
				Log.i("possiblePlaced", String.valueOf(possiblePlaced[i]));
				columns.get(possiblePlaced[i]).readyToPlaced=false;
				// RESET SEMUA BIDAK YANG TERANCAM SAAT SEBELUM BIDAK TERPILIH DIPINDAHKAN
			}
			if (pieces.get(pieceInColumn.get(possiblePlaced[i])) != null) {
				if (draw) {
					pieces.get(pieceInColumn.get(possiblePlaced[i])).initDesignPiece(Piece.FREE);
				}else{
					pieces.get(pieceInColumn.get(possiblePlaced[i])).status=Piece.FREE;
				}
			}
		}
		if (draw) invalidate();
		possiblePlaced = new int[0]; // remove semua kemungkinan jalan nya bidak
		
	}
	
	public void getPossiblePieceOfEnemyPreasured(int[] possiblePlaced, long pieceSelected, boolean draw){
		for (int i = 0; i < possiblePlaced.length; i++) {
			if (pieces.get(pieceInColumn.get(possiblePlaced[i]))!=null) {
				if (pieces.get(pieceSelected).pMINE==Piece.ME) {
					if (pieces.get(pieceInColumn.get(possiblePlaced[i])).pMINE==Piece.YOU) {
						if (draw) {
							pieces.get(pieceInColumn.get(possiblePlaced[i])).initDesignPiece(Piece.READY_TO_EATED);
						}else{
							pieces.get(pieceInColumn.get(possiblePlaced[i])).status=Piece.READY_TO_EATED;
						}
					}
				}else if (pieces.get(pieceSelected).pMINE==Piece.YOU) {
					if (pieces.get(pieceInColumn.get(possiblePlaced[i])).pMINE==Piece.ME) {
						if (draw) {
							pieces.get(pieceInColumn.get(possiblePlaced[i])).initDesignPiece(Piece.READY_TO_EATED);
						}else{
							pieces.get(pieceInColumn.get(possiblePlaced[i])).status=Piece.READY_TO_EATED;
						}
					}
				}
			}
		}
	}
	
	public void eatEnemy(int loc, boolean draw){
		if (draw) {
			pieces.get(pieceInColumn.get(loc)).setVisibility(Piece.GONE);
		}
		if (pieces.get(pieceInColumn.get(loc)).pMINE==Piece.ME) {
			Collectionz.removeMyPiece(pieceInColumn.get(loc));
			
		}else if (pieces.get(pieceInColumn.get(loc)).pMINE==Piece.YOU){
			Collectionz.removeYourPiece(pieceInColumn.get(loc));
			
		}
		pieces.remove(pieceInColumn.get(loc));
		pieceInColumn.remove(loc);

	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		int c = M_BLACK;
		int dotW = MARGIN+((boardWeight%8)/2);
		int dotH = MARGIN+((boardWeight%8)/2);
		canvas.drawColor(Color.parseColor("#4C423C"));
		paint.setColor(Color.parseColor("#2B2625"));
		paint.setStrokeWidth(2);
		canvas.drawLine(-1, -1, windowWeight+1, windowWeight+1, paint);
		canvas.drawLine(-1, windowWeight+1, windowWeight+1, -1, paint);
		for (int i = 1; i <=8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (c==M_BLACK) c = M_WHITE ;
				else if (c==M_WHITE) c = M_BLACK ;
				paint.setColor(c);
				canvas.drawRect(dotW, dotH, dotW+boardWeight/8, dotH+boardWeight/8, paint);
				if (!isInEditMode()) {
					int name = Util.matrixToInt(COLUMNS[j],ROWS[ROWS.length-i]);
					if (columns.get(name)!=null) {
						if (columns.get(name).readyToPlaced) {
							paint.setColor(Color.GREEN);
							int oWeight = columnWeight/3;
							rec.set(dotW+oWeight, dotH+oWeight, dotW+oWeight*2, dotH+oWeight*2);
							canvas.drawOval(rec, paint);
						}
					}
				}
				
				dotW = dotW+boardWeight/8 ;
			}
			if (c==M_BLACK) c = M_WHITE ;
			else if (c==M_WHITE) c = M_BLACK ;
			dotW = MARGIN+((boardWeight%8)/2);
			dotH = dotH+boardWeight/8;
		}
		super.onDraw(canvas);
	}
	
	public int initColumns(){
		int dotW = MARGIN+((boardWeight%8)/2);
		int dotH = MARGIN+((boardWeight%8)/2);
		for (int i = 1; i <=8; i++) {
			for (int j = 1; j <= 8; j++) {
				Column column = new Column();
				column.weight = columnWeight;
				column.x = dotW;
				column.y = dotH;
				int name = Util.matrixToInt(COLUMNS[j], ROWS[ROWS.length-i]);
//				column.setName(name);
				columns.put(name, column);
				dotW = dotW+boardWeight/8 ;
			}
			dotW = MARGIN+((boardWeight%8)/2);
			dotH = dotH+boardWeight/8;
		}
		return columnWeight ;
	}

	public long addPiece(Piece piece, int loc){
		this.pieceInColumn.put(loc, piece.id);
		this.pieces.put(piece.id, piece);
		return piece.id ;
	}
	
	public boolean isEnemyReadyToEated(int loc){
		boolean preasured = false ;
		if (pieces.get(pieceInColumn.get(loc))!=null) {
			if (pieces.get(pieceInColumn.get(loc)).status==Piece.READY_TO_EATED) {
				preasured = true ;
			}
		}
		return preasured ;
	}
	
	public void lastMove(History history, boolean draw){
		movePieceTo(history.from, history.piecesId, draw);
		if (history.eatedPiece!=null) {
			
			if (history.eatedPiece.pMINE==Piece.ME) {
				Collectionz.addMyPiece(history.eatedPiece.id);
			}else if (history.eatedPiece.pMINE==Piece.YOU) {
				Collectionz.addYourPiece(history.eatedPiece.id);
			}
			if (draw) {
				history.eatedPiece.setVisibility(Piece.VISIBLE);
				ViewHelper.setTranslationX(history.eatedPiece, columns.get(history.eatedPiece.loc).x);
				ViewHelper.setTranslationY(history.eatedPiece, columns.get(history.eatedPiece.loc).y);
			}
			addPiece(history.eatedPiece, history.to);
		}
	}
	
	
	public void undoLastHistoryPlayerMove(){
		if (JChezz.getInstance().isSession()==Piece.ME) {
			if (JChezz.getInstance().isPlayingWithCom()) {
				if (JChezz.getInstance().getMyColor()==Color.WHITE) {
					lastMove(JHistoriez.getInstance().getHistories().get(0), true);
					JHistoriez.getInstance().getHistories().remove(0);
					lastMove(JHistoriez.getInstance().getHistories().get(0), true);
					JHistoriez.getInstance().getHistories().remove(0);
				}else{
					lastMove(JHistoriez.getInstance().getHistories().get(0), true);
					JHistoriez.getInstance().getHistories().remove(0);
					pa.onChangeSession(Piece.YOU);
				}
			}else{
				lastMove(JHistoriez.getInstance().getHistories().get(0), true);
				JHistoriez.getInstance().getHistories().remove(0);
				pa.onChangeSession(Piece.YOU);
			}
		
		}else if (JChezz.getInstance().isSession()==Piece.YOU) {
			lastMove(JHistoriez.getInstance().getHistories().get(0), true);
			JHistoriez.getInstance().getHistories().remove(0);
			pa.onChangeSession(Piece.ME);
		}

	}
	
	public void addHistoryPlayerMove(long piecesId, int fromColumn, int toColumn, Piece eatedPiece){
		History history = null;
		if (eatedPiece!=null) {
			history = new History(piecesId, fromColumn, toColumn, eatedPiece);
			
		}else{
			history = new History(piecesId, fromColumn, toColumn);
		}
		JHistoriez.addHistory(history);
	}
		
	
	
}

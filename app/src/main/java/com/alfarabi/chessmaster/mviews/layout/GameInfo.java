package com.alfarabi.chessmaster.mviews.layout;

import com.alfarabi.chessmaster.tools.DimensionTools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameInfo extends ImageView{
	
	private int[] moveDuration = {0,0,0};
	private int[] totalDuration = {0,0,0};
	private final String TOTAL_DUR = "Total : ";
	private final String MOVE_DUR = "Move : ";
	private final int fontColor = Color.parseColor("#FFFFFF95");//Color.BLACK ; //Color.parseColor("#FFFFFF95");
	private String move = "", total = "";
	private int orientation = LinearLayout.VERTICAL ;
	private Context context ;
	private int fontSize = 12;
	private int padding = 0 ;
	private boolean countMove = false ;
	private int infoHeight = 0 ;
	private TextPaint textPaint ;
	
	public GameInfo(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initOrientation();
		textPaint = new TextPaint();
		textPaint.setTextSize(fontSize);
		textPaint.setColor(fontColor);
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		move = "";
		total = "";
		if (orientation==LinearLayout.VERTICAL) {
			move += moveDuration[2]>9?String.valueOf(moveDuration[2]):"0"+String.valueOf(moveDuration[2]);
			move += ":";
			move += moveDuration[1]>9?String.valueOf(moveDuration[1]):"0"+String.valueOf(moveDuration[1]);
			move += ":";
			move += moveDuration[0]>9?String.valueOf(moveDuration[0]):"0"+String.valueOf(moveDuration[0]);

			total += totalDuration[2]>9?String.valueOf(totalDuration[2]):"0"+String.valueOf(totalDuration[2]);
			total += ":";
			total += totalDuration[1]>9?String.valueOf(totalDuration[1]):"0"+String.valueOf(totalDuration[1]);
			total += ":";
			total += totalDuration[0]>9?String.valueOf(totalDuration[0]):"0"+String.valueOf(totalDuration[0]);

			
			canvas.drawText(MOVE_DUR+move, padding, fontSize, textPaint);
			canvas.drawText(TOTAL_DUR+total, padding, (fontSize*2)+10, textPaint);
			
				
		}else if (orientation==LinearLayout.HORIZONTAL) {
			move += moveDuration[2]>9?String.valueOf(moveDuration[2]):"0"+String.valueOf(moveDuration[2]);
			move += ":";
			move += moveDuration[1]>9?String.valueOf(moveDuration[1]):"0"+String.valueOf(moveDuration[1]);
			move += ":";
			move += moveDuration[0]>9?String.valueOf(moveDuration[0]):"0"+String.valueOf(moveDuration[0]);

			total += totalDuration[2]>9?String.valueOf(totalDuration[2]):"0"+String.valueOf(totalDuration[2]);
			total += ":";
			total += totalDuration[1]>9?String.valueOf(totalDuration[1]):"0"+String.valueOf(totalDuration[1]);
			total += ":";
			total += totalDuration[0]>9?String.valueOf(totalDuration[0]):"0"+String.valueOf(totalDuration[0]);

			canvas.drawText(MOVE_DUR+move, padding, fontSize, textPaint);
			canvas.drawText(TOTAL_DUR+total, padding+100, fontSize, textPaint);
		}
		
	}
	
	private void initOrientation(){
		if (isInEditMode()) {
			orientation = LinearLayout.HORIZONTAL;
			infoHeight = 26-10;
			fontSize = infoHeight/2 ;
			padding = 5;
			setMinimumHeight(infoHeight);
			setMaxHeight(infoHeight);
			
		}else{
			if (DimensionTools.getHeight(context)>320) {
				orientation = LinearLayout.VERTICAL ;
				infoHeight = (DimensionTools.getHeight(context)-DimensionTools.getWidth(context))/3;
				fontSize = infoHeight/4 ;
				padding = 5;
					
			}else{
				orientation = LinearLayout.HORIZONTAL;
				infoHeight = ((DimensionTools.getHeight(context)-DimensionTools.getWidth(context))/3)-10;
				fontSize= infoHeight/2 ;
				padding = 5;
			}
			setMinimumHeight(infoHeight);
			setMaxHeight(infoHeight);
			
		}
		
	}
	
	private final Runnable moveRunnable = new Runnable() {
		
		@Override
		public void run() {
			try {
				while (countMove) {
					moveDuration[0]++;
					if (moveDuration[0]>=59) {
						moveDuration[0] = 0;
						moveDuration[1]++;
						if (moveDuration[1]>=59) {
							moveDuration[1] = 0;
							moveDuration[2]++; 
						}
					}
					Thread.sleep(1000);
					postInvalidate();
				}
				synchronized (this) {
					new Thread(totalRunnable).start();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	private final Runnable totalRunnable = new Runnable() {
		
		@Override
		public void run() {
			try {
				int seconds = moveDuration[0]+moveDuration[1]*60+moveDuration[2]*60*60;
				if (seconds>0) {
					int i = 0 ;
					while (i<=seconds) {
						i++;
						totalDuration[0]++;
						if (totalDuration[0]>=59) {
							totalDuration[0] = 0 ;
							totalDuration[1] += 1;
							if (totalDuration[1]>=59) {
								totalDuration[1] = 0;
								totalDuration[2] += 1; 
							}
						}
						Thread.sleep(100);
						postInvalidate();
					}
					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};
	
	
	public void countMove(boolean countMove){
		this.countMove = countMove ;
		
		if (countMove) {
			moveDuration[0] = 0;
			moveDuration[1] = 0;
			moveDuration[2] = 0;
			new Thread(moveRunnable).start();
			
			}	
		}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		infoHeight = h ; 
	}

}

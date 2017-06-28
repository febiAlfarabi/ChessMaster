package com.alfarabi.chessmaster.mviews.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class MoveDuration extends TextView{

	private boolean played = false ;
	private int[] moveDuration = {0,0};
	private Context context ;
	
	public MoveDuration(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context ;
	}
	
	
	public void play(){
		if (!played) {
			setText("00:00");
			played = true ;
		}
	}
	public void stop(){
		if (played) {
			played = false ;
		}
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
//	private final Runnable runnable = new Runnable() {
//		
//		@Override
//		public void run() {
//			try {
//				while (true) {
//					while (played) {
//						sb = getText().toString();
//						int[] firstDur = {Integer.valueOf(sb.toString().split(":")[0]), Integer.valueOf(sb.toString().split(":")[1])};
//						firstDur[0] = Integer.valueOf(sb.toString().split(":")[0]);
//						firstDur[1] = Integer.valueOf(sb.toString().split(":")[1])+1;
//						if (firstDur[1]==60) {
//							firstDur[1]=0;
//							firstDur[0]+=1;
//						}
//						sb = firstDur[0]<10?"0"+String.valueOf(firstDur[0]):String.valueOf(firstDur[0]);
//						sb += ":" ;
//						sb += firstDur[1]<10?"0"+String.valueOf(firstDur[1]):String.valueOf(firstDur[1]);
//						((Activity)context).runOnUiThread(new Runnable() {
//							
//							@Override
//							public void run() {
//								setText(sb.toString());
//								
//							}
//						});
//						moveDuration = firstDur;
//						Thread.sleep(1000);
//					}
//				}
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	};
	
	public int[] getMoveDuration() {
		return moveDuration;
	}

}

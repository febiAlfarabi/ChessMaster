package com.alfarabi.chessmaster.mviews.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TotalDuration extends TextView{

	private int[] totalDur = {0,0};
	private Context context ;
	
	public TotalDuration(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context ;
	}
	
	public int[] getTotalDur() {
		return totalDur;
	}
	
	public void setTotalDur(int[] totalDur) {
		this.totalDur = totalDur;
	}
	
	public void setText(int[] totalDur) {
		this.totalDur[0] += totalDur[0] ;
		this.totalDur[1] += totalDur[1] ;
		
	}
	
//	private final Runnable runnable = new Runnable() {
//		
//		@Override
//		public void run() {
//			String first = TotalDuration.this.getText().toString();
//			int[] firstDur = {Integer.valueOf(first.split(":")[0]), Integer.valueOf(first.split(":")[1])};
//			
//			try {
//				while (firstDur[0]<totalDur[0]||firstDur[1]<totalDur[1]) {
//					runned = true ;
//					first = TotalDuration.this.getText().toString();
//					firstDur[0] = Integer.valueOf(first.split(":")[0]);
//					firstDur[1] = Integer.valueOf(first.split(":")[1])+1;
//					if (firstDur[1]==60) {
//						firstDur[1]=0;
//						firstDur[0]+=1;
//					}
//					text = firstDur[0]<10?"0"+String.valueOf(firstDur[0]):String.valueOf(firstDur[0]);
//					text += ":";
//					text+= firstDur[1]<10?"0"+String.valueOf(firstDur[1]):String.valueOf(firstDur[1]);
//					((Activity)context).runOnUiThread(new Runnable() {
//						
//						@Override
//						public void run() {
//							setText(text);
//							
//						}
//					});
//					if (firstDur[0]==totalDur[0]&&firstDur[1]==totalDur[1]) {
//						break ;
//					}
//					Thread.sleep(10);
//				}
//				runned = false ;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	};

}

package com.alfarabi.chessmaster.mviews.custom;

import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class SeekBarPanel extends LinearLayout implements MyCustomInitializer{

	private LevelSeekBar levelSeekBar ;
	private Context context ;
	public static final int MARGIN = 20 ;
	private float limitLevel = 0 ;
	private float width = 0, height = 0;
	private Paint paint = new Paint();
	private TextPaint tp = new TextPaint();
	
	public SeekBarPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context ;
		initObject();
		initUI();
		initCustomUI();
		setWillNotDraw(false);
		invalidate();
	}

	@Override
	public void initObject() {
		paint.setColor(Color.WHITE);
		tp.setColor(Color.WHITE);
	}

	@Override
	public void initUI() {
		levelSeekBar = new LevelSeekBar(context, this);
		addView(levelSeekBar);
		setPadding(0, 60, 0, 0);
	}

	@Override
	public void initCustomUI() {
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int p = 5;
		tp.setTextSize(18);
		tp.setTypeface(Typeface.SANS_SERIF);
		canvas.drawText("Level : "+String.valueOf(levelSeekBar.getLevel()), p, 20, tp);
		tp.setTextSize(12);
		tp.setTypeface(Typeface.DEFAULT_BOLD);
		for (int i = 1; i <= 10; i++) {
			canvas.drawText(String.valueOf(i), p, 40, tp);
			canvas.drawLine(p+4, 45, p+4, 60, paint);
			p += limitLevel ;
		}
		super.onDraw(canvas);
		
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
		limitLevel = (width-MARGIN)/9;
		levelSeekBar.setLimitLevel(limitLevel);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
}

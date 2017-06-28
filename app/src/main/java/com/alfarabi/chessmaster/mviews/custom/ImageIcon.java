package com.alfarabi.chessmaster.mviews.custom;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;
import com.alfarabi.chessmaster.media.theme.JDesign;
import com.alfarabi.chessmaster.media.theme.MThemes;
import com.alfarabi.chessmaster.memoriez.JChezz;
import com.alfarabi.chessmaster.mviews.Board;
import com.alfarabi.chessmaster.tools.DimensionTools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageIcon extends ImageView implements MyCustomInitializer{

	private int iconWidth, iconHeight = 0;
	private int weight = 0 ;
	private int heightOfDvc = 0;
	private boolean mine = false ;
	private Paint paint = new Paint();
	private boolean animate, draw = false ;
	private int animCount = 0;
	private Context context ;
	private AttributeSet attrs ;

	public ImageIcon(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context ;
		this.attrs = attrs ;
		
		if (isInEditMode()) {
			setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_android), 16, 16, true));

		}else{
			initObject();
			initUI();
			initCustomUI();
			initOrientation();
		}
		

	}
	
	@Override
	public void initObject() {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlayerInfo, 0, 0);
		try {
			mine = a.getBoolean(R.styleable.PlayerInfo_mine, false);
		} finally {
			a.recycle();
		}
		weight = DimensionTools.countAndGetBoardWeight(context);
		heightOfDvc = DimensionTools.getHeight(context);
		new Thread(runnable).start();
	}

	@Override
	public void initUI() {
		initThemes(MThemes.DEFAULT_THEME);
		setWillNotDraw(false);
		setPadding(5, 5, 5, 5);
		invalidate();

	}

	@Override
	public void initCustomUI() {

	}
	
	public void initThemes(char theme){
		if (theme==JDesign.getInstance().getMthemes().getCurrentTheme()) {
			
		}else{
			JDesign.getInstance().getMthemes().setCurrentTheme(theme);
		}
		
		setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_android),
				(heightOfDvc-(heightOfDvc/10)-weight)/3, (heightOfDvc-(heightOfDvc/10)-weight)/3, true));

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mine) {
			paint.setColor(JChezz.getInstance().getMyColor()==Color.WHITE?Board.M_WHITE:Board.M_BLACK);
			canvas.drawRect(0, 0, iconWidth, iconHeight, paint);
		
		}else{
			paint.setColor(JChezz.getInstance().getYourColor()==Color.WHITE?Board.M_WHITE:Board.M_BLACK);
			canvas.drawRect(0, 0, iconWidth, iconHeight, paint);
		
		}
		if (draw) {
			if (mine) {
				paint.setColor(JChezz.getInstance().getMyColor()==Color.WHITE?Board.M_BLACK:Board.M_WHITE);
				paint.setStrokeWidth(4);
				canvas.drawLine(2, 2, 2, iconHeight-2, paint);
				canvas.drawLine(2, iconHeight-2, iconWidth-2, iconHeight-2, paint);
				canvas.drawLine(iconWidth-2, iconHeight-2, iconWidth-2, 2, paint);
				canvas.drawLine(iconWidth-2, 2, 2, 2, paint);
			}else{
				paint.setColor(JChezz.getInstance().getYourColor()==Color.WHITE?Board.M_BLACK:Board.M_WHITE);
				paint.setStrokeWidth(4);
				canvas.drawLine(2, 2, 2, iconHeight-2, paint);
				canvas.drawLine(2, iconHeight-2, iconWidth-2, iconHeight-2, paint);
				canvas.drawLine(iconWidth-2, iconHeight-2, iconWidth-2, 2, paint);
				canvas.drawLine(iconWidth-2, 2, 2, 2, paint);
					
			}
		}
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.iconWidth = w ;
		this.iconHeight = h ;
	}
	
	public void myAnimate(boolean run){
		animCount = 0 ;
		this.animate = run ;
		this.draw = run ;
		postInvalidate();
	}
	
	private final Runnable runnable = new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					animCount++;
					Thread.sleep(500);
					if (animate) {
						if (draw) {
							draw = false ;
							postInvalidate();
						}else{
							draw = true;
							postInvalidate();
						}
					}
					if (animCount==10) {
						animCount+=1;
						animate = false ;
						draw = false ;
						postInvalidate();
						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	public int getIconHeight() {
		return iconHeight;
	}
	public int getIconWidth() {
		return iconWidth;
	}
	
	private void initOrientation(){
		if (DimensionTools.getHeight(context)>320) {
			setVisibility(VISIBLE);
		}else{
			setVisibility(GONE);
		}
	}
	
	
}

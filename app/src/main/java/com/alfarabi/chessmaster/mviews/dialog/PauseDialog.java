package com.alfarabi.chessmaster.mviews.dialog;

import com.alfarabi.chessmaster.R;
import com.alfarabi.chessmaster.activities.PlayActivity;
import com.alfarabi.chessmaster.interfazes.MyCustomInitializer;
import com.alfarabi.chessmaster.tools.Constant;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PauseDialog extends Dialog implements MyCustomInitializer, android.view.View.OnClickListener{

	private Context context ;
	public static final String REMIS = "Remis";
	public static final String P_ONE_LOOSE = "Player One Loose";
	public static final String P_TWO_LOOSE = "Player Two Loose";
	public static final String PAUSE = "Pause";
	
	private String title = "";
	
	private SharedPreferences sharedPreferences ;
	
	
	
	public PauseDialog(Context context, String title) {
		super(context);
		this.title = title;
		this.context = context ;
		sharedPreferences = ((PlayActivity)context).getSharedPreferences(Constant.MYSHAREDPREF, Context.MODE_PRIVATE);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_pause_dialog);
		getWindow().setGravity(Gravity.CENTER);
		getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		findViewById(R.id.resumeButton).setOnClickListener(this);
		findViewById(R.id.exitButton).setOnClickListener(this);
		((TextView)findViewById(R.id.notificationTitleTv)).setText(title);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
		if (!title.equals(PAUSE)) {
			findViewById(R.id.resumeButton).setVisibility(Button.INVISIBLE);
		}
		
	}

	@Override
	public void initObject() {
		
	}

	@Override
	public void initUI() {
		
	}

	@Override
	public void initCustomUI() {
		
	}

	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.resumeButton) {
			if (title.equals(PAUSE)) {
				this.dismiss();
			}else{
//				((PlayActivity)context).stopGamesAndNextToKuisioner();
				((PlayActivity)context).stopGamesAndBackToConfig();
				((PlayActivity)context).onBackPressed();
			}
		}else if (v.getId()==R.id.exitButton) {
			if (title.equals(PAUSE)) {
				((PlayActivity)context).stopGamesAndBackToConfig();
				((PlayActivity)context).onBackPressed();
			}else{
				if (sharedPreferences.contains(Constant.ANSWERED)) {
					boolean cek = sharedPreferences.getBoolean(Constant.ANSWERED, false);
					if (!cek) {
//						((PlayActivity)context).stopGamesAndNextToKuisioner();
						((PlayActivity)context).stopGamesAndBackToConfig();
						((PlayActivity)context).onBackPressed();						
					}else if (cek) {
						((PlayActivity)context).stopGamesAndBackToConfig();
						((PlayActivity)context).onBackPressed();
					}
				}else{
//					((PlayActivity)context).stopGamesAndNextToKuisioner();
					((PlayActivity)context).stopGamesAndBackToConfig();
					((PlayActivity)context).onBackPressed();
				}
			}
			
		}
	}

}

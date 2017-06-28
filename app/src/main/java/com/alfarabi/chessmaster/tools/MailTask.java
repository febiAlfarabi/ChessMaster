package com.alfarabi.chessmaster.tools;

import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

public class MailTask extends AsyncTask<Message, Void, Integer>{

	public static final int HTTP_ERROR = 0 ;
	public static final int SEND_MAIL_SUCCES = 1 ;
	public static final int SEND_MAIL_FAIL = 2 ;
	
	private ProgressDialog progressDialog ;
	private Timer timer ;
	private Context context ;
	
	public MailTask(Context context) {
		this.context = context ;
		this.timer = new Timer();
		
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("Please wait for submitting kuisioner..");
		progressDialog.setTitle("Thankyou");
		progressDialog.setInverseBackgroundForced(false);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				MailTask.this.onCancelled();
			}
		});
		progressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				timer.scheduleAtFixedRate(new TimerTask() {
					int y = 0 ;
					@Override
					public void run() {
						y++;
						if (y==10) {
							timer.cancel();
						}
					}
				}, 0, 1000);
			}
		});
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.show();
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		
	}
	
	@Override
	protected Integer doInBackground(Message... msg) {
		try {
			Transport.send(msg[0]);
		} catch (MessagingException e) {
			e.printStackTrace();
			return SEND_MAIL_FAIL ;
		}
		return SEND_MAIL_SUCCES;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		progressDialog.dismiss();
		super.onPostExecute(result);
	}
	
	@Override
	protected void onCancelled() {
		timer = null;
		super.onCancelled();
	}
	
}

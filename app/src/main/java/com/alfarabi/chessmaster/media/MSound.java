package com.alfarabi.chessmaster.media;

import com.alfarabi.chessmaster.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class MSound implements OnLoadCompleteListener{
	
	private SoundPool soundPool ;
	private Context context ;
	private boolean ready = false;
	private int clickSound = 0;
	private float actualVolume, maxVolume ;
	
	public MSound(Context context) {
		this.context = context ;
		this.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    this.soundPool.setOnLoadCompleteListener(this);
	    this.clickSound =  this.soundPool.load(context, R.raw.sound_move, 1);
	    AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	    actualVolume = (float) am.getStreamVolume(AudioManager.STREAM_MUSIC);
	    maxVolume = (float) am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    
	}

	@Override
	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		ready = true;
	}
	
	public void playClick(){
		if (ready) {
			this.soundPool.play(clickSound, maxVolume, maxVolume, 1, 0, 1f);
			
		}
	}
	
	
}

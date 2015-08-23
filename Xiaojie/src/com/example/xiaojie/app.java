package com.example.xiaojie;

import android.app.Application;
import android.media.MediaPlayer;

public class app extends Application {
	static MediaPlayer mediaPlayer;

	@Override
	public void onCreate() {

		mediaPlayer = MediaPlayer.create(this, R.raw.imyours);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
		super.onCreate();
	}
}

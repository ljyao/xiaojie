package com.example.xiaojie;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SwipeRefresh extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swiperefresh);
		final TextView textView = (TextView) findViewById(R.id.text);
		textView.setText(R.string.first);
		Animation anim_fade_in = AnimationUtils.loadAnimation(this,
				R.anim.rotate);
		textView.setAnimation(anim_fade_in);
		final SwipeRefreshLayout layout = (SwipeRefreshLayout) findViewById(R.id.id_swipe);
		final ImageView imageView = (ImageView) findViewById(R.id.image);
		layout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				layout.postDelayed(new Runnable() {

					@Override
					public void run() {
						imageView.setImageDrawable(null);
						textView.clearAnimation();
						textView.setTextSize(30);
						textView.setText(R.string.say);
						Animation anim_rotate = AnimationUtils.loadAnimation(
								SwipeRefresh.this, R.anim.fade_in);
						anim_rotate
								.setAnimationListener(new AnimationListener() {
									@Override
									public void onAnimationStart(
											Animation animation) {
									}

									@Override
									public void onAnimationRepeat(
											Animation animation) {
									}

									@Override
									public void onAnimationEnd(
											Animation animation) {
										try {
											Thread.sleep(500);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										Animation anim_in = AnimationUtils
												.loadAnimation(
														SwipeRefresh.this,
														R.anim.fade_in);
										imageView.setVisibility(View.VISIBLE);
										imageView
												.setImageResource(R.drawable.a1);
										imageView.setAnimation(anim_in);
										findViewById(R.id.note).setVisibility(
												View.VISIBLE);

									}
								});
						textView.setAnimation(anim_rotate);
						layout.setRefreshing(false);
					}
				}, 100);
			}
		});
	}

	@Override
	protected void onRestart() {
		app.mediaPlayer.start();
		super.onRestart();
	}

	@Override
	protected void onStop() {
		app.mediaPlayer.pause();
		super.onStop();
	}
}

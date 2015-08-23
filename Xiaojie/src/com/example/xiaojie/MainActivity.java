package com.example.xiaojie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
//
//���ȣ�����һ��ȫ�ֱ���a����onPageScrolled���������õ�һ��������ֵ����ֵ��a,��onPageScrollStateChanged�������  if  �жϣ�������a��ֵ��viewPager�����һ��ҳ���ֵ�����������Ļ����ж�onPageScrollStateChanged�Ĳ�����ֵ�Ƿ�Ϊ1�����Ϊ1�Ļ���ʾҳ���Ѿ��������һҳ�������ڻ�����Ȼ��������������������Լ���Ҫ�Ĳ���������˵�ر�activity

public class MainActivity extends Activity {
	ViewPager pager;
	static int pageStatu;
	int[] img = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
			R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7,
			R.drawable.img8, R.drawable.img9 };
	private View[] view = new View[9];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getview();
		pager = (ViewPager) findViewById(R.id.guidePages);
		PagerAdapter pageAdapter = new MyAdapter();
		pager.setAdapter(pageAdapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				if (arg0 == 8 && pageStatu == 1) {
					Intent intent = new Intent(MainActivity.this,
							SwipeRefresh.class);
					startActivity(intent);
					finish();
				}
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				pageStatu = arg0;
			}
		});
		pager.setCurrentItem(0);
	}

	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return 9;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(view[position]);

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {

			((ViewPager) container).addView(view[position]);
			return view[position];
		}
	}

	public void getview() {
		for (int i = 0; i < 9; i++) {

			Drawable drawable = getResources().getDrawable(img[i]);
			view[i] = getLayoutInflater().inflate(R.layout.viewpage, null);
			ImageView iv = (ImageView) view[i].findViewById(R.id.image);
			if (i == 0) {
				Animation animation = AnimationUtils.loadAnimation(this,
						R.anim.fade_in);
				iv.setAnimation(animation);
			} else if (i == 8) {
				Animation animation = AnimationUtils.loadAnimation(this,
						R.anim.fade_out);
				animation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						Intent intent = new Intent(MainActivity.this,
								SwipeRefresh.class);
						startActivity(intent);
						finish();
					}
				});
				iv.setAnimation(animation);
			}
			iv.setBackgroundDrawable(drawable);
		}
	}


}

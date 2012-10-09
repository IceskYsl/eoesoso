package com.eoesou.ui;

import com.eoesou.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// ȫ��Ļ��ʾ
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ����
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.splash);
		// ��ʾ3���Ӻ��Զ���ʧ
		ImageView iv01 = (ImageView) this.findViewById(R.id.logo_iv);
		// ����һ��3���ӵĽ��䶯��
		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(3000);
		iv01.startAnimation(aa);// ��ʼ���Ŷ���
		aa.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Splash.this, PageHomeActivity.class);
				Splash.this.startActivity(it);
				finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

		});
	}
}

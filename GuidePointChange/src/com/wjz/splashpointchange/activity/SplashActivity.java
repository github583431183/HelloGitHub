package com.wjz.splashpointchange.activity;

import com.wjz.splashpointchange.R;
import com.wjz.splashpointchange.utils.MyContains;
import com.wjz.splashpointchange.utils.SpTools;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	private ImageView sIcon;
	private AnimationSet as;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();// ��ʼ������
		startAnimation();// ���ö���
		initEvent();// ��ʼ���¼�

	}

	private void startAnimation() {

		as = new AnimationSet(false);// ���ö�������ÿ�ֶ���ʹ�ø��Ե�ʱ����

		/*
		 * ��ת���� 0 - 360 ê�㣬���ĵ�
		 */
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		ra.setDuration(2000);// ����ʱ��
		ra.setFillAfter(true);// ��������ͣ��
		as.addAnimation(ra);// ���붯����

		/*
		 * ���䶯�� 0 - 1
		 */
		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);// ����ʱ��
		aa.setFillAfter(true);// ��������ͣ��
		as.addAnimation(aa);// ���붯����

		/*
		 * ���Ŷ��� x��0 - 1 y��0 - 1 ê�㣬���ĵ�
		 */
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		sa.setDuration(2000);// ����ʱ��
		sa.setFillAfter(true);// ��������ͣ��
		as.addAnimation(sa);// ���붯����

		// ΪͼƬ���ö�����
		sIcon.startAnimation(as);

	}

	private void initEvent() {
		// ����as����������������������¼�
		// ������ɽ��� �򵼽����������
		as.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				// 1.���������жϽ���Ľ���
				// 2.��ȡ��ǣ��жϱ�ǣ�true�������棬false���򵼽���
				if (SpTools.getBoolean(SplashActivity.this, MyContains.ISSETUP,
						false)) {
					Intent mainIntent = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(mainIntent);
				} else {
					Intent guideIntent = new Intent(SplashActivity.this,
							GuideActivity.class);
					startActivity(guideIntent);
				}
				finish();
			}
		});
	}

	private void initView() {
		setContentView(R.layout.activity_splash);
		sIcon = (ImageView) findViewById(R.id.iv_splash_icon);
	}
}

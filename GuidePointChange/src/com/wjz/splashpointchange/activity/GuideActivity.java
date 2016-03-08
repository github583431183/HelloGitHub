package com.wjz.splashpointchange.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.wjz.splashpointchange.R;
import com.wjz.splashpointchange.utils.DensityUtil;
import com.wjz.splashpointchange.utils.MyContains;
import com.wjz.splashpointchange.utils.SpTools;

public class GuideActivity extends Activity {

	private ViewPager gViewPager;
	private View gRedPoint;
	private LinearLayout gLlGrayPointsContainer;
	private Button gButtonExp;
	private VpAdapter vpAdapter;
	private List<ImageView> gListDatas;
	private int distance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		setContentView(R.layout.activity_guide);
		// viewpager���
		gViewPager = (ViewPager) findViewById(R.id.vp_guide);
		// ���
		gRedPoint = findViewById(R.id.v_guide_redPoint);
		// �ҵ�����
		gLlGrayPointsContainer = (LinearLayout) findViewById(R.id.ll_gray_points);
		gButtonExp = (Button) findViewById(R.id.bt_guide_startexp);

		// ����viewPager��������
		vpAdapter = new VpAdapter();
		gViewPager.setAdapter(vpAdapter);

	}

	private class VpAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			if (gListDatas != null) {
				return gListDatas.size();
			}
			return 0;// �������ݵĸ���
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object; // ����
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			ImageView child = gListDatas.get(position);
			container.addView(child);

			return child;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);// �Ƴ�view
		}

	}

	private void initEvent() {

		// ����LinearLayout�¼����
		gLlGrayPointsContainer.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// ȡ������
						gLlGrayPointsContainer.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						// �������֮��ľ���
						distance = gLlGrayPointsContainer.getChildAt(1)
								.getLeft()
								- gLlGrayPointsContainer.getChildAt(0)
										.getLeft();
					}
				});

		// ��ť����¼�,���
		gButtonExp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent mainIntent = new Intent(GuideActivity.this,
						MainActivity.class);
				startActivity(mainIntent);
				SpTools.setBoolean(GuideActivity.this, MyContains.ISSETUP, true);
				finish();
			}
		});

		// ����ViewPagerҳ��ı䣬���ı䵽���һ������ʾ���鰴ť
		gViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// ��ʾ��ǰViewPager��ҳ�룬����ʾ�����һ������ʾ��ť
				if (position == gListDatas.size() - 1) {
					gButtonExp.setVisibility(View.VISIBLE);
				} else {
					gButtonExp.setVisibility(View.GONE);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

				float pointsMargin = distance * (position + positionOffset);
				RelativeLayout.LayoutParams marginParams = (RelativeLayout.LayoutParams) gRedPoint.getLayoutParams();
				marginParams.leftMargin = Math.round(pointsMargin);
				// �������ò���
				gRedPoint.setLayoutParams(marginParams);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initData() {

		int[] pics = new int[] { R.drawable.one, R.drawable.two,
				R.drawable.three };

		// ����ViewPage������
		gListDatas = new ArrayList<ImageView>();

		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(pics[i]);

			// ��ImageView���뼯��
			gListDatas.add(iv);

			// ��̬�ĸ�������ӻ�ɫ�ĵ�
			// 1.����view
			View gPoint = new View(this);
			// 2.ָ����ɫ
			gPoint.setBackgroundResource(R.drawable.gray_point);
			// 3.���ô�С
			int dp = 10;
			LayoutParams params = new LayoutParams(DensityUtil.dip2px(this, dp), DensityUtil.dip2px(this, dp));
			if (i != 0) {
				params.leftMargin = 10;
			}
			gPoint.setLayoutParams(params);

			gLlGrayPointsContainer.addView(gPoint);
		}

	}
}

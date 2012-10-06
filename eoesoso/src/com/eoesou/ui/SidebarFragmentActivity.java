package com.eoesou.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.eoesou.R;
import com.eoesou.ui.MyHorizontalScrollView.SizeCallback;

public abstract class SidebarFragmentActivity extends Activity {

	private boolean mSideBarOut = false;
	private View mSideBarView;
	private View mContentView;
	private View mSideBarResponseArea;
	protected LayoutInflater mLayoutInflater;
	private ExitApplication exitApplication = ExitApplication.getInstance();

	// 当前activity的布局
	public abstract int getLayoutResId();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(getLayoutResId());
		mLayoutInflater = this.getLayoutInflater();
		int resId = getLayoutResId();
		if (resId == 0) {
			// TODO 不允许为0
			return;
		}

		exitApplication.addActivity(this);
		View frameView = mLayoutInflater.inflate(R.layout.side_bar_frame, null);
		MyHorizontalScrollView scrollView = (MyHorizontalScrollView) frameView.findViewById(R.id.view_side_bar_frame);

		mSideBarView = mLayoutInflater.inflate(R.layout.side_bar, null);
		mContentView = mLayoutInflater.inflate(resId, null);

		mSideBarResponseArea = frameView.findViewById(R.id.view_side_bar_response_area);
		mSideBarResponseArea.setOnClickListener(new ClickListenerForScrolling(scrollView, mSideBarView));

		final View[] children = new View[] { mSideBarView, mContentView };

		// Scroll to app (view[1]) when layout finished.
		int scrollToViewIdx = 1;
		scrollView.initViews(children, scrollToViewIdx, new SizeCallbackForMenu(mSideBarResponseArea));
		setContentView(frameView);

		SideBarClickListener listener = new SideBarClickListener();
		Button buttonHome = (Button) mSideBarView.findViewById(R.id.button_home);
		Button buttonNotification = (Button) mSideBarView.findViewById(R.id.button_notification);
		Button buttonMylike = (Button) mSideBarView.findViewById(R.id.button_mylike);
		Button buttonMyhomepage = (Button) mSideBarView.findViewById(R.id.button_myhomepage);
		Button buttonSet = (Button) mSideBarView.findViewById(R.id.button_set);
		Button buttonLogout = (Button) mSideBarView.findViewById(R.id.button_logout);

		buttonHome.setOnClickListener(listener);
		buttonNotification.setOnClickListener(listener);
		buttonMylike.setOnClickListener(listener);
		buttonMyhomepage.setOnClickListener(listener);
		buttonSet.setOnClickListener(listener);
		buttonLogout.setOnClickListener(listener);

		// buildActionViews(true);

	}

	class ClickListenerForScrolling implements OnClickListener {
		HorizontalScrollView scrollView;
		View menu;

		public ClickListenerForScrolling(HorizontalScrollView scrollView, View menu) {
			super();
			this.scrollView = scrollView;
			this.menu = menu;
		}

		@Override
		public void onClick(View v) {
			int menuWidth = menu.getMeasuredWidth();

			// Ensure menu is visible
			menu.setVisibility(View.VISIBLE);

			if (!mSideBarOut) {
				// Scroll to 0 to reveal menu
				int left = 0;
				scrollView.smoothScrollTo(left, 0);
			} else {
				// Scroll to menuWidth so menu isn't on screen.
				int left = menuWidth;
				scrollView.smoothScrollTo(left, 0);
			}
			mSideBarOut = !mSideBarOut;
		}
	}

	class SizeCallbackForMenu implements SizeCallback {
		int btnWidth;
		View btnSlide;

		public SizeCallbackForMenu(View btnSlide) {
			super();
			this.btnSlide = btnSlide;
		}

		@Override
		public void onGlobalLayout() {
			btnWidth = btnSlide.getMeasuredWidth();
		}

		@Override
		public void getViewSize(int idx, int w, int h, int[] dims) {
			dims[0] = w;
			dims[1] = h;
			final int menuIdx = 0;
			if (idx == menuIdx) {
				dims[0] = mSideBarView.getMeasuredWidth();
			}
		}
	}

	class SideBarClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int index = 0;
			switch (v.getId()) {
			case R.id.button_home:
				// index = VIEW_ID_HOME;
				Intent button_homeIntent = new Intent(SidebarFragmentActivity.this, PageHomeActivity.class);
				startActivity(button_homeIntent);
				finish();
				break;
			case R.id.button_notification:
				Intent button_notification = new Intent(SidebarFragmentActivity.this, PageHomeActivity.class);
				startActivity(button_notification);
				finish();
				break;
			case R.id.button_mylike:
				Intent button_mylike = new Intent(SidebarFragmentActivity.this, PageHomeActivity.class);
				startActivity(button_mylike);
				break;
			case R.id.button_myhomepage:
				Intent button_myhomepage = new Intent(SidebarFragmentActivity.this, PageHomeActivity.class);
				startActivity(button_myhomepage);
				break;
			case R.id.button_set:
				Intent button_set = new Intent(SidebarFragmentActivity.this, PageHomeActivity.class);
				startActivity(button_set);
				break;
			case R.id.button_logout:

				break;
			}
		}
	}
}

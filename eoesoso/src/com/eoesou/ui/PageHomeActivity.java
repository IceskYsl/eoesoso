package com.eoesou.ui;

import com.eoesou.R;

import android.os.Bundle;
import android.widget.TextView;
public class PageHomeActivity extends SidebarFragmentActivity{

	TextView textview_title_current;
	@Override
	public int getLayoutResId() {
		return R.layout.home_page;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InitTextView();
		
	}
	private void InitTextView() {
		textview_title_current = (TextView) findViewById(R.id.textview_title_current);
		textview_title_current.setText("优亿搜搜");


	}
}

package com.eoesou.ui;

import com.eoesou.R;

import android.os.Bundle;
import android.widget.TextView;

public class AppInfoActivity extends SidebarFragmentActivity {
	TextView textview_title_current;
	@Override
	public int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.appinfo;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		InitTextView();
	}
	
	private void InitTextView() {
		textview_title_current = (TextView) findViewById(R.id.textview_title_current);
		textview_title_current.setText("”¶”√œÍ«È");
		
		


	}
}



package com.eoesou.ui;

import java.util.List;
import java.util.Map;

import com.eoesou.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class AppListActivity extends SidebarFragmentActivity implements
		OnItemClickListener {
	ListView listView;
	AppListAdapter adapter;
	TextView textview_title_current;
	List<Map<String, Object>> list;

	@Override
	public int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.applist;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {

		// list需要调用接口读取数据，交给你们啦
		listView = (ListView) findViewById(R.id.applist_lv);
		adapter = new AppListAdapter(this, list);
		listView.setAdapter(adapter);
		textview_title_current = (TextView) findViewById(R.id.textview_title_current);
		textview_title_current.setText("应用详情");
		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent intent = new Intent();
		intent.setClass(AppListActivity.this, AppInfoActivity.class);
		startActivity(intent);
	}

}

package com.eoesou.ui;

import java.util.List;
import java.util.Map;

import com.eoesou.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AppListAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> list;


	private ListView myListView;

	static class Viewholder {
		ImageView tagIcon;
		ImageView appIcon;
		TextView appName;
		TextView appSize;
		RatingBar ratingBar;
		TextView download;
		Button loadBtn;

	}

	public AppListAdapter(Context context,List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
	}

	// @Override
	public int getCount() {

		return list.size();
	}

	// @Override
	public Object getItem(int position) {

		return list.get(position);
	}

	// @Override
	public long getItemId(int position) {

		return position;
	}

	// @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Viewholder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_app_chk_m, null);
			holder = new Viewholder();
			holder.appIcon = (ImageView) convertView
					.findViewById(R.id.item_app_m_icon);
			holder.appName = (TextView) convertView
					.findViewById(R.id.item_app_m_title);

			holder.ratingBar = (RatingBar) convertView
					.findViewById(R.id.item_app_m_rating);
			holder.appSize = (TextView) convertView
					.findViewById(R.id.item_app_m_size);
			holder.loadBtn = (Button) convertView
					.findViewById(R.id.item_app_m_operate);
			holder.download = (TextView) convertView
					.findViewById(R.id.item_app_m_state);

			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}
		return convertView;
	}
}

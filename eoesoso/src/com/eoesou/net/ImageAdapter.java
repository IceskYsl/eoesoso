package com.eoesou.net;

import java.io.File;
import java.util.List;

import com.eoesou.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	protected static final int SUCCESS_GET_IMAGE = 0;
	private Context context;
	private List<Contact> contacts;
	private File cache;
	private LayoutInflater mInflater;

	// 自己定义的构造函数
	public ImageAdapter(Context context, List<Contact> contacts, File cache) {
		this.context = context;
		this.contacts = contacts;
		this.cache = cache;

		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 1获取item,再得到控件
		// 2 获取数据
		// 3绑定数据到item
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = mInflater.inflate(R.layout.item, null);
		}

		ImageView iv_header = (ImageView) view.findViewById(R.id.imageView);
		TextView tv_name = (TextView) view.findViewById(R.id.textView);

		Contact contact = contacts.get(position);
		
		// 异步的加载图片 (线程池 + Handler ) ---> AsyncTask
		asyncloadImage(iv_header, contact.image);
		
		tv_name.setText(contact.name);

		return view;
	}
	
	private void asyncloadImage(ImageView iv_header, String path) {
		ContactService service = new ContactService();
		AsyncImageTask task = new AsyncImageTask(service, iv_header);
		task.execute(path);
	}

	private final class AsyncImageTask extends AsyncTask<String, Integer, Uri> {

		private ContactService service;
		private ImageView iv_header;

		public AsyncImageTask(ContactService service, ImageView iv_header) {
			this.service = service;
			this.iv_header = iv_header;
		}

		// 后台运行的子线程子线程
		@Override
		protected Uri doInBackground(String... params) {
			try {
				return service.getImageURI(params[0], cache);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// 这个放在在ui线程中执行
		@Override
		protected void onPostExecute(Uri result) {
			super.onPostExecute(result);
			// 完成图片的绑定
			if (iv_header != null && result != null) {
				iv_header.setImageURI(result);
			}
		}
	}
}
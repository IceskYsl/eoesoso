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

	// �Լ�����Ĺ��캯��
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
		// 1��ȡitem,�ٵõ��ؼ�
		// 2 ��ȡ����
		// 3�����ݵ�item
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = mInflater.inflate(R.layout.item, null);
		}

		ImageView iv_header = (ImageView) view.findViewById(R.id.imageView);
		TextView tv_name = (TextView) view.findViewById(R.id.textView);

		Contact contact = contacts.get(position);
		
		// �첽�ļ���ͼƬ (�̳߳� + Handler ) ---> AsyncTask
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

		// ��̨���е����߳����߳�
		@Override
		protected Uri doInBackground(String... params) {
			try {
				return service.getImageURI(params[0], cache);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// ���������ui�߳���ִ��
		@Override
		protected void onPostExecute(Uri result) {
			super.onPostExecute(result);
			// ���ͼƬ�İ�
			if (iv_header != null && result != null) {
				iv_header.setImageURI(result);
			}
		}
	}
}
package com.example.gallerykit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {
	private Gallery gallery;
	private ImageAdapter adapter;
	private RelativeLayout layout;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new ImageAdapter(this);
		gallery = (Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(adapter);
		/*
		 * gallery.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * public void onItemClick(AdapterView parent, View v, int position,
		 * long id) { //Toast.makeText(MainActivity.this, "" + position,
		 * //Toast.LENGTH_SHORT).show(); } }) (;
		 */

		gallery.setOnItemLongClickListener(new GalleryItemLongClickListener());
	}

	class DialogClickListener implements DialogInterface.OnClickListener {

		int selectedIndex;

		public DialogClickListener(int index) {
			selectedIndex = index;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			switch (which) {
			case 0:
				Intent intent = new Intent(MainActivity.this,
						EditPhotoActivity.class);
				intent.putExtra("IMAGEID", selectedIndex);
				MainActivity.this.startActivity(intent);
				break;
			case 1:
				break;
			}
		}
	}

	class GalleryItemLongClickListener implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(final AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {

			if (adapter == null) {
				Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT)
						.show();
			} else {
				Integer id = (int) adapter.GetId(arg2);

				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
						.setTitle("请选择")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setSingleChoiceItems(new String[] { "编辑", "删除" }, 0,
								new DialogClickListener(id))
						.setNegativeButton("取消", null).show();

			}

			return true;
		}
	}

	/*
	 * class ImageAdapter is used to control gallery source and operation.
	 */
	private class ImageAdapter extends BaseAdapter {
		private Context context;
		private Integer[] images = { R.drawable.r2, R.drawable.r1,
				R.drawable.r3 };

		public ImageAdapter(Context c) {
			context = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub

			return images.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub

			return images[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub

			return images[position];
		}

		public int GetId(int position) {
			return images[position];

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			ImageView i = new ImageView(context);
			Bitmap map = BitmapFactory.decodeResource(context.getResources(),
					images[position]);

			i.setImageResource(images[position]);
			i.setScaleType(ImageView.ScaleType.FIT_XY);
			i.setLayoutParams(new Gallery.LayoutParams(map.getWidth(), map
					.getHeight()));

			return i;
		}

	};
}

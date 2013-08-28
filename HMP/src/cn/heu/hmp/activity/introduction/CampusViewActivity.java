package cn.heu.hmp.activity.introduction;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.introduction.Data;
import cn.heu.hmp.util.introduction.GalleryFlow;

public class CampusViewActivity extends Activity
{
	private ProgressDialog progressDialog;

	private Object[] allPic;

	private Object[] allPicName;

	private IImageAdapter adapter;

	private TextView textView;

	// 数据源
	private ArrayList<Bean> beans = new ArrayList<Bean>();

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campusview);
		this.progress();
		textView = (TextView) findViewById(R.id.text_view);
		// 声明adapter
		adapter = new IImageAdapter();
		// 异步加载数据和adapter
		new ViewAsyncLoadTask(this, adapter).execute();
		// 声明布局文件
		GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.Gallery01);
		// 添加布局文件
		galleryFlow.setSelection(200);
		galleryFlow.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)
			{
				textView.setText(allPicName[arg2].toString());
			}

			public void onNothingSelected(AdapterView<?> arg0)
			{

			}

		});

		galleryFlow.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				Intent intent = new Intent();
				intent.setClass(CampusViewActivity.this, CampusViewSubActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("picURL", allPic[arg2].toString());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		galleryFlow.setAdapter(adapter);
	}

	public void progress()
	{
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("数据加载中，请稍等");
	}

	@Override
	protected void onPause()
	{
		if (progressDialog.isShowing())
		{
			progressDialog.dismiss();
		}
		super.onPause();
	}

	public int getImageId(String pic)
	{
		if (pic == null || pic.trim().equals(""))
		{
			return R.drawable.icon;
		}

		@SuppressWarnings("rawtypes")
		Class draw = R.drawable.class;

		try
		{
			Field field = draw.getDeclaredField(pic);
			return field.getInt(pic);
		}
		catch (SecurityException e)
		{
			return R.drawable.icon;
		}
		catch (NoSuchFieldException e)
		{
			return R.drawable.icon;
		}
		catch (IllegalArgumentException e)
		{
			return R.drawable.icon;
		}
		catch (IllegalAccessException e)
		{
			return R.drawable.icon;
		}
	}

	public class Bean
	{
		private Bitmap image;

		public Bitmap getImage()
		{
			return image;
		}

		public void setImage(Bitmap image)
		{
			this.image = image;
		}
	}

	class IImageAdapter extends BaseAdapter
	{
		public int getCount()
		{
			return beans.size();
		}

		public Bean getItem(int position)
		{
			return beans.get(position);
		}

		public long getItemId(int position)
		{
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			ImageView i = new ImageView(getApplicationContext());
			i.setLayoutParams(new GalleryFlow.LayoutParams(240, 320));
			Bean b = beans.get(position);
			if (b.getImage() != null)
			{
				Bitmap bitmap = b.getImage();

				i.setImageBitmap(bitmap);
			}

			Bitmap bitmap = b.getImage();
			
			
			
			reflectionView(i, bitmap);

			return i;
		}

		public void reflectionView(ImageView i, Bitmap bitmap)
		{
			final int reflectionGap = 4;
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);
			Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
					width, height / 2, matrix, false);
			Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
					(height + height / 2), Config.ARGB_4444);
			Canvas canvas = new Canvas(bitmapWithReflection);
			canvas.drawBitmap(bitmap, 0, 0, null);
			Paint deafaultPaint = new Paint();
			canvas.drawRect(0, height, width, height + reflectionGap,
					deafaultPaint);
			canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
			Paint paint = new Paint();
			LinearGradient shader = new LinearGradient(0, bitmap.getHeight(),
					0, bitmapWithReflection.getHeight() + reflectionGap,
					0x70ffffff, 0x00ffffff, TileMode.CLAMP);
			paint.setShader(shader);
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
					+ reflectionGap, paint);
			i.setImageBitmap(bitmapWithReflection);
		}

		public float getScale(boolean focused, int offset)
		{
			return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
		}

	}

	public class ViewAsyncLoadTask extends AsyncTask<Void, Void, Map<String, Object[]>>
	{
		private IImageAdapter adapter;

		public ViewAsyncLoadTask(Context context, IImageAdapter adapter)
		{
			this.adapter = adapter;
		}

		@Override
		protected void onPreExecute()
		{
			progressDialog.show();
		}

		@Override
		protected Map<String, Object[]> doInBackground(Void... params)
		{
			Map<String, Object[]> result = new HashMap<String, Object[]>();

			Object[] allPic = new Data().getData_campusView();
			Object[] allPicName = new Data().getData_campusViewName();
			result.put("pic", allPic);
			result.put("name", allPicName);
			return result;
		}

		@Override
		protected void onPostExecute(Map<String, Object[]> result)
		{
			if (progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
			allPic = result.get("pic");
			allPicName = result.get("name");
			for (int i = 0; i < allPic.length; i++)
			{
				Bean b = new Bean();

				b.setImage(BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher));

				beans.add(b);
			}

			for (int i = 0; i < adapter.getCount(); i++)
			{
				Bean bean = adapter.getItem(i);
				Bitmap bitmap = BitmapFactory.decodeStream(Request
						.HandlerData(allPic[i].toString()));
				bean.setImage(bitmap);
				publishProgress(); // 通知UI去更新
			}
		}

		@Override
		protected void onProgressUpdate(Void... voids)
		{
			if (isCancelled())
			{
				return;
			}
			// 更新UI
			adapter.notifyDataSetChanged();
		}
	}

	static class Request
	{
		public static InputStream HandlerData(String url)
		{
			InputStream inStream = null;
			try
			{
				URL feedUrl = new URL(url);
				URLConnection conn = feedUrl.openConnection();
				conn.setConnectTimeout(10 * 1000);
				inStream = conn.getInputStream();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return inStream;
		}
	}
}
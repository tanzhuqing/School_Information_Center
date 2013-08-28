package cn.heu.hmp.util.introduction;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class LoadImage extends Activity
{
	private AsyncImageLoader loader = new AsyncImageLoader();
	
	public void load(final String url, ImageView imageView)
	{
		CallbackImpl callbackImpl = new CallbackImpl(imageView);

		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);

		if (cacheImage != null)
		{
			imageView.setImageDrawable(cacheImage);
		}
	}
}

class CallbackImpl implements AsyncImageLoader.ImageCallback
{
	private ImageView imageView;
	
	public CallbackImpl(ImageView imageView)
	{
		super();
		
		this.imageView = imageView;
	}

	public void imageLoaded(Drawable imageDrawable)
	{
		imageView.setImageDrawable(imageDrawable);
	}
}

class AsyncImageLoader
{
	//ͼƬ�������
	//����ͼƬ��URL��ֵ��һ��SoftReference���󣬸ö���ָ��һ��Drawable����
	private Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
	
	//ʵ��ͼƬ���첽����
	public Drawable loadDrawable(final String imageUrl, final ImageCallback callback)
	{
		
		//��ѯ���棬�鿴��ǰ��Ҫ���ص�ͼƬ�Ƿ��Ѿ������ڻ��浱��
		if(imageCache.containsKey(imageUrl))
		{
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			
			if(softReference.get() != null)
			{
				return softReference.get();
			}
		}
		
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg)
			{
				callback.imageLoaded((Drawable)msg.obj);
			}
		};
		//�½�һ���̣߳����߳����ڽ���ͼƬ������
		new Thread(){
			@Override
			public void run()
			{
				Drawable drawable = loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();
		
		return null;
	}
	//�÷������ڸ���ͼƬ��URL��������������ͼƬ
	protected Drawable loadImageFromUrl(String imageUrl)
	{
		try
		{
			//����ͼƬ��URL������ͼƬ��������һ��Drawable����
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "src");
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	//�ص��ӿ�
	public interface ImageCallback
	{
		public void imageLoaded(Drawable imageDrawable);
	}
}

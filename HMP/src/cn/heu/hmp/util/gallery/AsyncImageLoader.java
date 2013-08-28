package cn.heu.hmp.util.gallery;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;

public class AsyncImageLoader {
	// Ϊ�˼ӿ��ٶȣ����ڴ��п������棨��ҪӦ�����ظ�ͼƬ�϶�ʱ������ͬһ��ͼƬҪ��α����ʣ�������ListViewʱ���ع�����
	public static Map<String, SoftReference<Drawable>> myimageCache = new HashMap<String, SoftReference<Drawable>>();

	private ExecutorService executorService = Executors.newFixedThreadPool(3);
	private final Handler handler = new Handler();

	/**
	 * 
	 * @param imageUrl  ͼ��url��ַ
	 * @param imageCallback  �ص��ӿ�
	 * @return �����ڴ��л����ͼ�񣬵�һ�μ��ط���null
	 */
	public Drawable loadDrawable(final Context context, final String imageUrl,final ImageCallback imageCallback)
	{
		// ���������ʹӻ�����ȡ������
		if (myimageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = myimageCache.get(imageUrl);
			if (softReference.get() != null) {
				return softReference.get();
			}
		}
		if (HttpUtils.is_Intent(context)) {
			executorService.submit(new Runnable() {
				public void run() {
					try {
						final Drawable drawable = HttpUtils.loadImageFromUrl(imageUrl);
						if (drawable != null) {
							myimageCache.put(imageUrl,new SoftReference<Drawable>(drawable));
							handler.post(new Runnable() {
								public void run() {
									imageCallback.imageLoaded(drawable,imageUrl);
								}
							});
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});
		}
		return null;
	}

	//����翪�ŵĻص��ӿ�
	public interface ImageCallback {
		//ע�� �˷�������������Ŀ������ͼ����Դ
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}
}

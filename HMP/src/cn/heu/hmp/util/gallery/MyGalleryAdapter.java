package cn.heu.hmp.util.gallery;

import java.util.List;

import cn.heu.hmp.util.AsyncLoad.AsyncImageLoader;
import cn.heu.hmp.util.AsyncLoad.AsyncImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;

public class MyGalleryAdapter extends BaseAdapter {
	Context context = null;
	Gallery gallery = null;
	public List<String> imgURL = null;

	   
	public MyGalleryAdapter(Context context,List<String> imgURL,Gallery currGallery){
		this.context = context;
		this.imgURL = imgURL;
		this.gallery = currGallery;
	}

	public int getCount() {
		return Integer.MAX_VALUE;
	}

	public Object getItem(int position) {
		return imgURL.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView imageView = new ImageView(context);  
		String curr_URL = imgURL.get(position%imgURL.size());
		imageView.setTag(curr_URL);
		//��һ���첽��ʽ
		 if(AsyncImageLoader.imageCache.containsKey(curr_URL)){
			 imageView.setImageDrawable(AsyncImageLoader.imageCache.get(curr_URL));
		 }else{
			 //img_view.setImageResource(R.drawable.loading);
			 //�첽����ͼƬ
			new AsyncImageLoader().loadDrawable(curr_URL, imageView, new ImageCallback(){
				public void imageLoaded(Drawable imageDrawable,ImageView imageView, String imageUrl) {
					//��ͼƬ����ͼƬview��
					 imageView.setImageDrawable(imageDrawable);
				}
			});
		 }
		//�ڶ����첽����
		/* Drawable cachedImage = asyncImageLoader.loadDrawable(context,curr_URL,new ImageCallback() {
			public void imageLoaded(Drawable imageDrawable, String imageUrl) {
			    ImageView imageViewByTag = (ImageView) gallery.findViewWithTag(imageUrl);
			    if (imageViewByTag != null && imageDrawable != null ) { 
			        imageViewByTag.setImageDrawable(imageDrawable);
			        notifyDataSetChanged();
			    }
			}
		 });
		// Log.d("cachedImage", ""+cachedImage);
		if (cachedImage != null) {
	    	  imageView.setImageDrawable(cachedImage);
	    }else{
	    	imageView.setImageResource(R.drawable.loading);
	    }*/
		// ���ñ߽����
		 imageView.setAdjustViewBounds(true);
		 imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		 //���ñ�������  
		 imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		 //����
		 AnimationSet aniset = new AnimationSet(true);
		 AlphaAnimation alphanimation = new AlphaAnimation(0,1);
		 alphanimation.setDuration(2000);
		 aniset.addAnimation(alphanimation);
		 imageView.setAnimation(aniset);
		return imageView;
	}
}

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
		//第一种异步方式
		 if(AsyncImageLoader.imageCache.containsKey(curr_URL)){
			 imageView.setImageDrawable(AsyncImageLoader.imageCache.get(curr_URL));
		 }else{
			 //img_view.setImageResource(R.drawable.loading);
			 //异步加载图片
			new AsyncImageLoader().loadDrawable(curr_URL, imageView, new ImageCallback(){
				public void imageLoaded(Drawable imageDrawable,ImageView imageView, String imageUrl) {
					//将图片放入图片view中
					 imageView.setImageDrawable(imageDrawable);
				}
			});
		 }
		//第二种异步方法
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
		// 设置边界对齐
		 imageView.setAdjustViewBounds(true);
		 imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		 //设置比例类型  
		 imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		 //动画
		 AnimationSet aniset = new AnimationSet(true);
		 AlphaAnimation alphanimation = new AlphaAnimation(0,1);
		 alphanimation.setDuration(2000);
		 aniset.addAnimation(alphanimation);
		 imageView.setAnimation(aniset);
		return imageView;
	}
}

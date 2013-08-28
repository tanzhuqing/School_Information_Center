package cn.heu.hmp.entity.meijing;

import java.util.ArrayList;

import cn.heu.hmp.activity.R;

import cn.heu.hmp.util.AsyncLoad.AsyncImageLoader;
import cn.heu.hmp.util.AsyncLoad.AsyncImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MeiJingGridviewAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<MeiJingBean> mData;
	private Context context;
	
	public MeiJingGridviewAdapter(Context context, ArrayList<MeiJingBean> data) {
		this.context=context;
		this.mData = data;
		this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return mData.size();
	}

	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	public long getItemId(int position) {
		
		return 0;
	}
	public void addData(ArrayList<MeiJingBean> data){
		mData.addAll(data);
		notifyDataSetChanged();
	}
	public void addItem(final MeiJingBean item) {
		mData.add(item);
		notifyDataSetChanged();
	} 
	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}
	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount();
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView img_view;
		 if(convertView==null){
			 convertView = mInflater.inflate(R.layout.xyfg_list,null);
		 }

		 img_view=(ImageView) convertView.findViewById(R.id.xyfg_small_img);
		 //img_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
		 //xyfg_progress
		 MeiJingBean item=(MeiJingBean) getItem(position);
		 
		 if(AsyncImageLoader.imageCache.containsKey(item.getTp_smallImgurl())){
			 img_view.setImageDrawable(AsyncImageLoader.imageCache.get(item.getTp_smallImgurl()));
		 }else{
			 Log.d("异步获取图片", ""+item.getTp_smallImgurl());
			 //img_view.setImageResource(R.drawable.loading);
			 //异步加载图片
			new AsyncImageLoader().loadDrawable(item.getTp_smallImgurl(), img_view, new ImageCallback(){
				public void imageLoaded(Drawable imageDrawable,ImageView imageView, String imageUrl) {
					//将图片放入图片view中
					imageView.setImageDrawable(imageDrawable);
					//imageView.setb
				}
			});
		 }
			//动画
//		 AnimationSet aniset = new AnimationSet(true);
//		 AlphaAnimation alphanimation = new AlphaAnimation(0,1);
//		 alphanimation.setDuration(2000);
//		 aniset.addAnimation(alphanimation);
//		 img_view.setAnimation(aniset);
		 Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
		 img_view.setAnimation(anim);
		 return convertView;
	}
	
}

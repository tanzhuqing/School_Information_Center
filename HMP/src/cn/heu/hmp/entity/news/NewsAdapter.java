package cn.heu.hmp.entity.news;

import java.util.ArrayList;


import cn.heu.hmp.util.AsyncLoad.AsyncImageLoader;
import cn.heu.hmp.util.AsyncLoad.AsyncImageLoader.ImageCallback;

import cn.heu.hmp.activity.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<NewsInfo> mData;
	
    public NewsAdapter(Context context, ArrayList<NewsInfo> data,
    		int resource, String[] from, int[] to) {
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public NewsAdapter(Context context, ArrayList<NewsInfo> data,int resource) {
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /**
     * 向数据中添加数据
     * @param data
     */
    public void addData(ArrayList<NewsInfo> data){
    	mData.addAll(data);
    	notifyDataSetChanged();
    }
	public int getCount() {
		return mData.size();
	}
    public void addItem(final NewsInfo item) {
    	mData.add(item);
    	notifyDataSetChanged();
    } 
	public Object getItem(int position) {
		return mData.get(position);
	}
	public long getItemId(int position) {
		 return position;
	}
	
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return super.getViewTypeCount();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView title_view ;
		TextView desc_view ;
		ImageView img_view ;
		TextView fbr_view ;
		TextView fbrq_view ;

		 if(convertView==null){
			 convertView = mInflater.inflate(R.layout.newslist,null);
		 }
		 
		 title_view = (TextView) convertView.findViewById(R.id.news_title);
		 //desc_view = (TextView) convertView.findViewById(R.id.news_desc);
		 fbr_view= (TextView) convertView.findViewById(R.id.news_fbr);
		 fbrq_view= (TextView) convertView.findViewById(R.id.news_fbrq);
		 img_view = (ImageView) convertView.findViewById(R.id.news_img);
		 
		 NewsInfo item=(NewsInfo) getItem(position);
		 title_view.setText(item.getNew_title());
		 //desc_view.setText(item.getNew_desc());
		 fbr_view.setText(item.getNew_fbr());
		 fbrq_view.setText(item.getNew_fbrq());
		 
		 //新闻标题图  查询缓存中是否有图片 如果有显示，如果没有暂时放一个临时图否则异步加载
		 //Log.d("dddddddddd", item.getNew_imgurl()+"");
		 //Log.d("imageCache", AsyncImageLoader.imageCache+"");
		 
		 if(AsyncImageLoader.imageCache.containsKey(item.getNew_imgurl())){
			 img_view.setImageDrawable(AsyncImageLoader.imageCache.get(item.getNew_imgurl()));
		 }else{
			 //img_view.setImageResource(R.drawable.newsloadingimg);
			 //异步加载图片
			new AsyncImageLoader().loadDrawable(item.getNew_imgurl(), img_view, new ImageCallback(){
				public void imageLoaded(Drawable imageDrawable,ImageView imageView, String imageUrl) {
					//将图片放入view中
					if(imageDrawable==null){
						//imageView.setImageResource(R.drawable.newsloadingimg);
					}else{
						imageView.setImageDrawable(imageDrawable);
					}
				}
			});
		 }

		 return convertView;
	}
}

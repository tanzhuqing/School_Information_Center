package cn.heu.hmp.util.AsyncLoad;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import cn.heu.hmp.activity.R;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
/**
 * 实现图片的异步载入显示
 * @author MaRui
 */
public class AsyncImageLoader {

    /**
     * 软引用对象，在响应内存需要时，由垃圾回收器决定是否清除此对象。软引用对象最常用于实现内存敏感的缓存。
     */
   public static HashMap<String, Drawable> imageCache = new HashMap<String, Drawable>();
    
    public AsyncImageLoader(){

    }
    
    public  void loadDrawable(final String imageUrl,final ImageView imageView,final ImageCallback imagecallback){
        
    	final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                imagecallback.imageLoaded((Drawable)msg.obj, imageView, imageUrl);
            }
        };
        
    	if(imageCache.containsKey(imageUrl)){
            //从缓存中读取人人
            Drawable drawable = (Drawable)imageCache.get(imageUrl);
            if(drawable != null){
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }else{
            new Thread(){
                public void run() {
                    Drawable drawable = loadImageFromUrl(imageUrl);
                    imageCache.put(imageUrl, drawable);
                    Message message = handler.obtainMessage(0, drawable);
                    handler.sendMessage(message);
                }
            }.start();
        } 
    }
    
    public static Drawable loadImageFromUrl(String urlPath){
        URL url;
        InputStream is = null;
        Drawable drawable =null;
        try{
            url = new URL(urlPath);
            is = (InputStream)url.getContent();
            drawable = Drawable.createFromStream(is, "src");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return drawable;
    }
    public interface ImageCallback 
    {
        public void imageLoaded(Drawable imageDrawable,ImageView imageView, String imageUrl);
    }
}


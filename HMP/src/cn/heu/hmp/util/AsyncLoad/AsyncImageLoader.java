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
 * ʵ��ͼƬ���첽������ʾ
 * @author MaRui
 */
public class AsyncImageLoader {

    /**
     * �����ö�������Ӧ�ڴ���Ҫʱ�������������������Ƿ�����˶��������ö��������ʵ���ڴ����еĻ��档
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
            //�ӻ����ж�ȡ����
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


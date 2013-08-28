package cn.heu.hmp.util.AsyncLoad;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.Handler;
import android.os.Message;
import android.view.View;
/**
 * Achieve asynchronous load data
 * @author MaRui
 */
public class AsyncHttpResultLoader {
	
    private HashMap<String, SoftReference<String>> resultCache;
    
    public AsyncHttpResultLoader(){
    	resultCache = new HashMap<String, SoftReference<String>>();
    }
    
    public  String loadHttpResult(final String httpUrl,final View view,final HttpResultCallback httpresultcallback){
        if(resultCache.containsKey(httpUrl)){
            //从缓存中读取
            SoftReference<String> softReference = resultCache.get(httpUrl);
            String str = softReference.get();
            if(str != null){
                return str;
            }
        }
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                httpresultcallback.resultLoaded((String)msg.obj, view, httpUrl);
            }
        };
        
        new Thread(){
            public void run() {
                String xmlResult = loadResultFromUrl(httpUrl);
                resultCache.put(httpUrl, new SoftReference<String>(xmlResult));
                Message message = handler.obtainMessage(0, xmlResult);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }
    /**
     * Access to data sources
     * @param urlPath
     * @return
     */
    private String loadResultFromUrl(String urlPath){
    	String httpResult=GetHttpResult(urlPath);
		if(!httpResult.equals("err:200")){
			return httpResult;
		}else{
			return "-1";
		}
    }
    /**
     * 获得数据源
     * @param url
     * @return
     */
	private String GetHttpResult(String url){
		String strResult="";
		HttpGet httpRequest  = new HttpGet(url);
		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				strResult = EntityUtils.toString(httpResponse.getEntity());
			}else{
				strResult="err:200";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strResult;
	}
	public interface HttpResultCallback 
	{
	    public void resultLoaded(String result,View view, String httpUrl);
	}
    
}

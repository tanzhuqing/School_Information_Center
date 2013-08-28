package cn.heu.hmp.util.gallery;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtils {

	 public static Drawable loadImageFromUrl(String url) {
         URL m;
         InputStream i = null;
         try {
             m = new URL(url);
             i = (InputStream) m.getContent();
         } catch (MalformedURLException e1) {
             e1.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         Drawable d = Drawable.createFromStream(i, "src");
         return d;
     }


	/**
	 * ������������
	 * 
	 * @return
	 */
	public static boolean is_Intent(Context context) {
		ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = con.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			// ��ǰ���粻����
			return false;
		}
		return true;

	}
}

package cn.heu.hmp.activity.news;

import java.util.ArrayList;
import java.util.List;
import cn.heu.hmp.util.app.MyApplication;
import cn.heu.hmp.activity.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;
/**
 * News Info Activity
 * @author MaRui
 * @version 1.0
 */
public class NewsInfoActivity extends Activity {
	
	private WebView webView;
	
	final String mimeType = "text/html";
	final String encoding = "utf-8"; 
	
	public List<String> imgURLs = new ArrayList<String>();
	
	MyApplication myapp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myapp=(MyApplication) getApplicationContext();
		myapp.loadProperties(this);
		//==========Custom title============
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.newsinfomain);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
		TextView app_name = (TextView)findViewById(R.id.textTile);
		app_name.setText(R.string.news_name);
		
		 //获得传入值
		 Intent intent = getIntent();
		 String newsid = intent.getStringExtra("id");

		 //Webview
		 //webView = (WebView) findViewById(R.id.infowebview);
		 webView.getSettings().setJavaScriptEnabled(true);  
		 webView.requestFocus();
		 //缩放
		 webView.getSettings().setSupportZoom(true);
		 webView.getSettings().setBuiltInZoomControls(true);
		 //取消滚动条
	     //this.setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
		 
		try {   
			webView.loadUrl("http://125.223.113.81:8080/HMPS/news/newsinfo.jsp?id="+newsid);
		} catch (Exception ex) {   
			//ex.printStackTrace();
			
		}
		webView.setWebViewClient(new MyWebViewClient(this));
		 
		//end creat
	}
	//重写返回事件
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK){  
//			webView.goBack();   
//			//goBack()表示返回webView的上一页面   
//			return true;  
//		}  
//		return false;  
//	}

	


}

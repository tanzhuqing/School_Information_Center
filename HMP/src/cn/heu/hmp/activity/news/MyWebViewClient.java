package cn.heu.hmp.activity.news;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

	private ProgressDialog mypDialog;
	
	public MyWebViewClient(NewsInfoActivity context) {
		mypDialog=new ProgressDialog(context);
	}
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		
		return true;
	}


	@Override
	public void onPageFinished(WebView view, String url) {
		mypDialog.hide();
		super.onPageFinished(view, url);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		
		mypDialog.setMessage("数据加载中......");
		mypDialog.show();

		super.onPageStarted(view, url, favicon);
	}
	
	
	

}

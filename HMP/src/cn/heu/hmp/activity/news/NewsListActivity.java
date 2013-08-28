package cn.heu.hmp.activity.news;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.heu.hmp.entity.news.NewsAdapter;
import cn.heu.hmp.entity.news.NewsInfo;
import cn.heu.hmp.util.AsyncLoad.AsyncHttpResultLoader;
import cn.heu.hmp.util.AsyncLoad.AsyncHttpResultLoader.HttpResultCallback;
import cn.heu.hmp.util.app.MyApplication;

import cn.heu.hmp.activity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * �����б�activity
 * @author Administrator
 */
public class NewsListActivity extends Activity implements OnItemClickListener {

	private ArrayList<NewsInfo> newsInfos = null;
	//private String anews_url="http://192.168.81.198:8080/heumobile/menu/newslist/newslist_json.jsp?lmid=1";
	private NewsAdapter myAdapter;
	List<HashMap<String,Object>> list;
	ListView listview=null;
	private ProgressDialog mypDialog;
	
	MyApplication myapp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		myapp=(MyApplication) getApplicationContext();
		myapp.loadProperties(this);
		
		//==========Custom title============
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.newsmain);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
		TextView app_name = (TextView)findViewById(R.id.textTile);
		app_name.setText(R.string.news_name);

		//Asynchronous load list
		listview = (ListView) findViewById(R.id.MyListView);
		
		//Initialization listview 
		newsInfos = new ArrayList<NewsInfo>();
		myAdapter=new NewsAdapter(this, newsInfos, R.layout.newslist);
		listview.setAdapter(myAdapter);
		
		//Ϊlist����¼�
		listview.setOnItemClickListener(this);
		
		//��ʼ��������
		mypDialog=new ProgressDialog(this);
		mypDialog.setMessage("���ݼ��������Ժ�...");
		mypDialog.show();
		//�첽��������
		new AsyncHttpResultLoader().loadHttpResult("http://125.223.113.81:8080/HMPS/news/newslist_json.jsp", listview, new HttpResultCallback() {
			public void resultLoaded(String result, View view, String httpUrl) {
				//parse JSON
				mypDialog.setMessage("���ڽ�������...");
				newsInfos = parseJSON(result);
				//Organization adapter
				myAdapter.addData(newsInfos);
				//�رս�����
				mypDialog.hide();
			}
		});
		
	}

    /**
     * parse JSON date
     * @param jsonStr
     * @return List<NewsInfo>
     */
    private ArrayList<NewsInfo> parseJSON(String jsonStr){
    	ArrayList<NewsInfo> infoLists = new ArrayList<NewsInfo>();
    	JSONArray array;
		try {
		 array = new JSONObject(jsonStr).getJSONArray("newslist");
	    	for ( int  i= 0 ; i<array.length(); i++){   
	    	    JSONObject obj = array.getJSONObject(i);
	    	    NewsInfo  newinfo=new NewsInfo();
	    	    newinfo.setNew_ID(obj.get("ID").toString());
	    	    newinfo.setNew_title(obj.get("Title").toString());
	    	    newinfo.setNew_desc("---");
	    	    newinfo.setNew_fbr("������:"+obj.get("Fbr").toString());
	    	    newinfo.setNew_fbrq("��������:"+obj.get("Fbrq").toString());
	    	    
	    	    newinfo.setNew_imgurl("http://news.color5164.com/"+obj.get("ImgUrl").toString());
	    	    System.out.println(newinfo.getNew_imgurl());
	    	    infoLists.add(newinfo);
	    	} 
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return infoLists;
    }

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//����һ��Intent����
		Intent intent = new Intent();
		//��Intent���������һ����ֵ��
		intent.putExtra("id", newsInfos.get(arg2).getNew_ID());
		//����Intent����Ҫ������Activity
		intent.setClass(this, NewsInfoActivity.class);
		//ͨ��Intent������������һ��Activity
		this.startActivity(intent);
	}
}

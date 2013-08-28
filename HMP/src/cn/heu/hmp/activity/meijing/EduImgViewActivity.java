package cn.heu.hmp.activity.meijing;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.AsyncLoad.AsyncHttpResultLoader;
import cn.heu.hmp.util.AsyncLoad.AsyncHttpResultLoader.HttpResultCallback;
import cn.heu.hmp.util.app.MyApplication;
import cn.heu.hmp.util.gallery.MyGallery;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;

public class EduImgViewActivity extends Activity {

	private List<String> imgURLs = new ArrayList<String>();
	private List<String> names = new ArrayList<String>();
	int imgid=0;
	
	private MyGallery myGallery = null;
	private EduGalleryAdapter advGalleryAdapter = null;
	private TextView nameView;
	private TextView pageView;
	
	MyApplication myapp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myapp=(MyApplication) getApplicationContext();
		myapp.loadProperties(this);
		//��������
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.xyfg_view); 
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
		TextView app_name = (TextView)findViewById(R.id.textTile);
		app_name.setText(R.string.xyfg);

		 //��ô���ֵ
		 Intent intent = getIntent();
		 //imgid = intent.getStringExtra("imgid");
		 imgid =intent.getIntExtra("imgid", 0);
		 
		//�첽��ȡ��ҳ����ͼƬ·��
		//String imgurl="http://192.168.81.196/hrbedu/meijing_bigimglist_json.jsp";
		new AsyncHttpResultLoader().loadHttpResult(myapp.getXYMJ_bigURL(), findViewById(R.id.newsinfo_tpz), new HttpResultCallback() {
			public void resultLoaded(String result, View view, String httpUrl) {
				//parse JSON
				parseJSONtoList(result);
				
				//���������ͼƬ��Ϊ0����ʾgallery
				if(imgURLs.size()>0&&imgURLs.size()==names.size()){
					showImgGallery();
				}
			}
		});
		
	}
	/**
	 * ͼƬ��ʾ
	 */
	private void showImgGallery(){
		 //ͼƬ����
        nameView = (TextView) findViewById(R.id.xymj_name);
        pageView = (TextView) findViewById(R.id.xymj_page);
		//Gallery
        myGallery = (MyGallery) findViewById(R.id.xymj_gallery);
        
        advGalleryAdapter = new EduGalleryAdapter(this,imgURLs,myGallery);
        
        myGallery.setAdapter(advGalleryAdapter);
        //ѡ��ڼ���
        myGallery.setSelection(Integer.MAX_VALUE >> 1);
        myGallery.setSelection(imgid);
        Log.d("id", ""+imgid);
		//ת�� Event 
        myGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				//��ʾ��ǰͼ�����
				nameView.setText(names.get(arg2%imgURLs.size()));
				pageView.setText("( "+(arg2%imgURLs.size()+1)+"/"+imgURLs.size()+" )");
			}
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
        
        
	}
	 /**
     * parse JSON date
     * @param jsonStr
     * @return List<Stirng>
     */
    private void parseJSONtoList(String jsonStr){
    	JSONArray array;
		try {
		 array = new JSONObject(jsonStr).getJSONArray("xymj_bigimg");
		 //Log.d("array", ""+array);
	    	for ( int  i= 0 ; i<array.length(); i++){   
	    	    JSONObject obj = array.getJSONObject(i);
	    	    imgURLs.add(obj.get("bigimgurl").toString());
	    	    names.add(obj.get("imgname").toString());
	    	} 
		} catch (JSONException e) {
			e.printStackTrace();
		}
    }
	

}

package cn.heu.hmp.activity.meijing;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.heu.hmp.activity.R;

import cn.heu.hmp.entity.meijing.MeiJingBean;
import cn.heu.hmp.entity.meijing.MeiJingGridviewAdapter;
import cn.heu.hmp.util.AsyncLoad.AsyncHttpResultLoader;
import cn.heu.hmp.util.AsyncLoad.AsyncHttpResultLoader.HttpResultCallback;
import cn.heu.hmp.util.app.MyApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class EduImagesActivity extends Activity {

	private GridView gridview;
	private ArrayList<MeiJingBean> meijingList;
	private MeiJingGridviewAdapter tpItemAdapter;
	MyApplication myapp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myapp=(MyApplication) getApplicationContext();
		myapp.loadProperties(this);
		//��������
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.xyfg_main); 
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
		TextView app_name = (TextView)findViewById(R.id.textTile);
		app_name.setText(R.string.xyfg);
		
		//XYFG_GridView
		gridview = (GridView) findViewById(R.id.XYFG_GridView);
		
		meijingList = new ArrayList<MeiJingBean>();
		//meijingList = getMTList();
		//adapter
		tpItemAdapter = new MeiJingGridviewAdapter(this,meijingList);

		//���Item�������� 
		gridview.setAdapter(tpItemAdapter);
		//�����¼� ���ͼƬȥչʾҳ��
		gridview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				//
				//����һ��Intent����
				Intent intent = new Intent();
				intent.putExtra("imgid", arg2);
				//ȥ��ͼƬչʾҳ
				intent.setClass(EduImagesActivity.this, EduImgViewActivity.class);
				EduImagesActivity.this.startActivity(intent);
			}
		});
		//�첽��ȡ����ͼ
		asySLT();
	}
	
	public void asySLT(){
		
		//�첽��ȡ��ҳ����ͼƬ·��
		new AsyncHttpResultLoader().loadHttpResult(myapp.getXYMJ_URL(), gridview, new HttpResultCallback() {
			public void resultLoaded(String result, View view, String httpUrl) {
				//parse JSON
				ArrayList<MeiJingBean> alist = parseJSONtoList(result);
				//���������ͼƬ��Ϊ0����ʾgallery
				if(alist.size()>0){
					tpItemAdapter.addData(alist);
					for(int i=0;i>alist.size();i++){
						tpItemAdapter.addItem(alist.remove(i));
					}
				}
			}
		});
	}
	   /**
     * parse JSON date
     * @param jsonStr
     * @return List<Stirng>
     */
    private ArrayList<MeiJingBean> parseJSONtoList(String jsonStr){

    	JSONArray array;
    	ArrayList<MeiJingBean> alist=new ArrayList<MeiJingBean>();
		try {
		 array = new JSONObject(jsonStr).getJSONArray("meijingst");
	    	for ( int  i= 0 ; i<array.length(); i++){  
	    		MeiJingBean st=new MeiJingBean();
	    	    JSONObject obj = array.getJSONObject(i);
	    	    st.setTp_smallImgurl(obj.get("imgurl").toString());
	    	    alist.add(st);
	    	} 
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return alist;
    }
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	
}

package cn.heu.hmp.activity.myapps;

import java.util.ArrayList;
import java.util.HashMap;

import cn.heu.hmp.activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MyappsMainActivity extends Activity {
	
	private String[] names=new String[]{"һ��ͨ","�ҵĲ���","�ҵĿα�","�ҵĳɼ�"};
	private Object[] pngs=new Object[]{R.drawable.aa,R.drawable.bb,R.drawable.cc,R.drawable.dd};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��������
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.myappsmain); 
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
		TextView app_name = (TextView)findViewById(R.id.textTile);
		app_name.setText(R.string.myspps);
		
		//����Ӧ�þŹ���ʵ��
		GridView gridview = (GridView) findViewById(R.id.MyappsGridView); 
		ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>(); 
		
		for(int i = 0;i <names.length;i++) { 
			HashMap<String, Object> map = new HashMap<String, Object>(); 
			map.put("ItemImage", pngs[i]); 
			map.put("ItemText", names[i]); 
			meumList.add(map); 
		} 
		//adapter
		SimpleAdapter saMenuItem = new SimpleAdapter(
				this,
				meumList,
				R.layout.myappsitem,
				new String[]{"ItemImage","ItemText"},
				new int[]{R.id.myapps_Item_Image,R.id.myapps_Item_Text});

		//���Item�������� 
		gridview.setAdapter(saMenuItem); 
		//���ø���Ӧ�õ���¼�
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3){
				if(arg3==0){
					//intent.setClass(HMPActivity.this, NewsListActivity.class);
					//HMPActivity.this.startActivity(intent);
					return;
				}
				
				
			} 
		});
		
		//creatend
	}
	
}

package cn.heu.hmp.activity.around;
import java.util.List;

import cn.heu.hmp.activity.R;
import cn.heu.hmp.activity.info.InfoListActivity;
import cn.heu.hmp.entity.info.Info;
import cn.heu.hmp.util.gallery.HttpHelper;
import cn.heu.hmp.util.gallery.HttpUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Aroundy extends Activity {

	private ListView mylist = null;
	String[] alistArray=new String[HttpUtil.delheadandback(query().toString()).split(",").length/2];
	String[] blistArray=new String[HttpUtil.delheadandback(query().toString()).split(",").length/2];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aroundy);
		//��ʼ������
		JSONArray jsona;
		try {
			jsona = new JSONArray(query());
			 for (int i = 0; i < jsona.length(); i++)
	         {   
	    	   JSONObject jsono = jsona.getJSONObject(i); 
	    	   alistArray[i]=jsono.get("id").toString();
	    	   blistArray[i]=jsono.get("name").toString();
		     }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mylist = (ListView) findViewById(R.id.myListView);
		InfoAdapter infoAdapter = new InfoAdapter(blistArray);
		mylist.setAdapter(infoAdapter);
		//ArrayAdapter<String> arrayA=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, blistArray);
		//mylist.setAdapter(arrayA);

		OnItemClickListener listener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
			    //�����ѡ����ʾ
				Intent it = new Intent(Aroundy.this, Aroundinfo.class);  
				Bundle bundle = new Bundle();
			    bundle.putString("data", alistArray[position].toString());
			    /* ��Bundle ����assign ��Intent */
			    it.putExtras(bundle);
				startActivity(it); 
			}
		};
		mylist.setOnItemClickListener(listener);
	}
	  private class InfoAdapter extends ArrayAdapter<String> {

	        public InfoAdapter(String[] mlistArray) {
	            super(Aroundy.this, R.layout.around_tel, mlistArray);
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            if (convertView == null) {
	                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                convertView = inflater.inflate(R.layout.around_tel, parent,
	                        false);
	            }
	            TextView title = (TextView) convertView.findViewById(R.id.idTo);  
	            title.setText(blistArray[position].toString());
	            return convertView;

	        }
	    }
	private String query(){
		// ��ѯ����[{"id":1,"name":"�Ļ�����"},{"id":2,"name":" �۳�"}]
		String queryString = "type=002";
		String url = HttpHelper.BASE_URL+"surrounding/surroundingAction_listSurroundingJSON.action?"+queryString;
		return HttpHelper.queryStringForPost(url);
    }
}
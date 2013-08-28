package cn.heu.hmp.activity.tel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.gallery.HttpHelper;
import cn.heu.hmp.util.gallery.HttpUtil;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Telsearch extends Activity {

	private ListView mylist = null;
	String[] blistArray=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.telsearch);
		Bundle bundled=this.getIntent().getExtras();
		String ttdata=bundled.getString("data");
		blistArray=new String[HttpUtil.delheadandback(query(ttdata).toString()).split(",").length/2];
		final String[] listArray=new String[HttpUtil.delheadandback(query(ttdata).toString()).split(",").length/2];
		//初始化数据
		JSONArray jsona;
		try {
			jsona = new JSONArray(query(ttdata));
			 for (int i = 0; i < jsona.length(); i++)
	         {   
	    	   JSONObject jsono = jsona.getJSONObject(i); 
	    	   listArray[i]=jsono.get("name").toString()+":"+jsono.get("telephone").toString();
	    	   blistArray[i]=jsono.get("name").toString()+":"+jsono.get("telephone").toString();
		     }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mylist = (ListView) findViewById(R.id.myListView);
		InfoAdapter infoAdapter = new InfoAdapter(blistArray);
		mylist.setAdapter(infoAdapter);
//		ArrayAdapter<String> arrayA=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);
//		mylist.setAdapter(arrayA);
		OnItemClickListener listener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
			    //标题的选择显示
				String[] arytel = listArray[position].split(":");
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel://"+arytel[1].toString()));  
	            startActivity(intent); 
			}
		};
		mylist.setOnItemClickListener(listener);
	}
	private class InfoAdapter extends ArrayAdapter<String> {
        public InfoAdapter(String[] mlistArray) {
            super(Telsearch.this, R.layout.around_tel, mlistArray);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.around_tel, parent,false);
            }
            TextView title = (TextView) convertView.findViewById(R.id.idTo);  
            title.setText(blistArray[position].toString());
            return convertView;
        }
    }
	private String query(String search){
		// 查询参数
		String queryString = "subDepartment="+search;
		String url = HttpHelper.BASE_URL+"department/departmentAction_queryTelephoneJSON.action?"+queryString;
		//http://125.223.112.44:8080/hmps/department/departmentAction_listSubDepartmentJson.action?mainDeptCode=1004000000
		// 查询返回结果
		return HttpHelper.queryStringForPost(url);
    }
}

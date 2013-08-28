package cn.heu.hmp.activity.tel;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.gallery.HttpHelper;
import cn.heu.hmp.util.gallery.HttpUtil;
import android.app.Activity;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Telinfo extends Activity {
	private ListView mylist = null;
	String[] blistArray=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.telinfo);
		Bundle bundled=this.getIntent().getExtras();
		String ttdata=bundled.getString("data");
		if(query(ttdata)=="Error"){
	     System.exit(0);
		}
		blistArray=new String[HttpUtil.delheadandback(query(ttdata).toString()).split(",").length/2];
		final String[] clistArray=new String[HttpUtil.delheadandback(query(ttdata).toString()).split(",").length/2];			
		//初始化数据
		JSONArray jsona;
		try {
			jsona = new JSONArray(query(ttdata));
			 for (int i = 0; i < jsona.length(); i++)
	         {   
	    	   JSONObject jsono = jsona.getJSONObject(i); 
	    	   clistArray[i]=jsono.get("name").toString()+":"+jsono.get("telephone").toString();
	    	   blistArray[i]=jsono.get("name").toString()+":"+jsono.get("telephone").toString();
	    	   //System.out.println(jsono.get("name").toString()+":"+jsono.get("telephone").toString());
		     }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mylist = (ListView) findViewById(R.id.myListView);
		InfoAdapter infoAdapter = new InfoAdapter(blistArray);
		mylist.setAdapter(infoAdapter);
//		ArrayAdapter<String> arrayA=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clistArray);
//		mylist.setAdapter(arrayA);
		OnItemClickListener listener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
			//	标题的选择显示
				String[] arytel = clistArray[position].split(":");
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel://"+arytel[1].toString()));  
	            startActivity(intent); 
			}
		};
		mylist.setOnItemClickListener(listener);
	}
	private class InfoAdapter extends ArrayAdapter<String> {
        public InfoAdapter(String[] mlistArray) {
            super(Telinfo.this, R.layout.around_tel, mlistArray);
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
	private String query(String depname){
	// 查询参数
	try{
		String queryString = "MainDepartment="+depname;
		String url = HttpHelper.BASE_URL+"department/departmentAction_listSubDepartmentJSON.action?"+queryString;		
		// 查询返回结果
		return HttpHelper.queryStringForPost(url);
	}
	catch(Exception ex){
		return "Error";
	}
    }
}


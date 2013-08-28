package cn.heu.hmp.activity.tel;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.activity.around.Aroundy;
import cn.heu.hmp.util.gallery.HttpHelper;
import cn.heu.hmp.util.gallery.HttpUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Telj extends Activity {

	private ListView mylist = null;
//	String[] alistArray=new String[HttpUtil.delheadandback(query().toString()).split(",").length/2];
	String[] blistArray=new String[HttpUtil.delheadandback(query().toString()).split(",").length];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telz);
		//初始化数据
		JSONArray jsona;
		try {
			jsona = new JSONArray(query());
			 for (int i = 0; i < jsona.length(); i++)
	         {   
	    	   JSONObject jsono = jsona.getJSONObject(i); 
//	    	   alistArray[i]=jsono.get("id").toString();
	    	   blistArray[i]=jsono.get("name").toString();
		     }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		final EditText edittex = (EditText) findViewById(R.id.userEditText);
		ImageButton imagebtn = (ImageButton) findViewById(R.id.imageButton1);
		mylist = (ListView) findViewById(R.id.myListView);
		InfoAdapter infoAdapter = new InfoAdapter(blistArray);
		mylist.setAdapter(infoAdapter);
//		ArrayAdapter<String> arrayA=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, blistArray);
//		mylist.setAdapter(arrayA);
		OnItemClickListener listener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
			    //标题的选择显示 打电话教学院系行政机构
				Intent it = new Intent(Telj.this, Telinfo.class); 
				Bundle bundle = new Bundle();
			    bundle.putString("data", blistArray[position].toString());
			    /* 将Bundle 对象assign 给Intent */
			    it.putExtras(bundle);
				startActivity(it); 
			}
		};
		mylist.setOnItemClickListener(listener);
		imagebtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String datas=edittex.getText().toString(); 				
				Intent intent = new Intent();
		        intent.setClass(Telj.this, Telsearch.class);
		        /* new 一个Bundle对象，并将要传递的数据传入*/
		        Bundle bundle = new Bundle();
		        bundle.putString("data", datas);
		        /* 将Bundle 对象assign 给Intent */
		        intent.putExtras(bundle);
		        edittex.setText("");
		        startActivity(intent);//s.replaceAll("a", "")
			}
		});
	}
	 private class InfoAdapter extends ArrayAdapter<String> {

	        public InfoAdapter(String[] mlistArray) {
	            super(Telj.this, R.layout.around_tel, mlistArray);
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
		// 查询参数
		String queryString = "departmentCode=001";
		String url = HttpHelper.BASE_URL+"department/departmentAction_listMainDepartmentJSON.action?"+queryString;
		//http://125.223.112.44:8080/hmps/department/departmentAction_listSubDepartmentJson.action?mainDeptCode=1004000000
		// 查询返回结果
		return HttpHelper.queryStringForPost(url);
    }
}

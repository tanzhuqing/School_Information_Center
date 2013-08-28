package cn.heu.hmp.activity.around;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.gallery.HttpHelper;
import cn.heu.hmp.util.gallery.HttpUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Aroundg extends Activity {
	private ListView mylist = null;
	private ProgressDialog progressDialog;
	String[] alistArray=new String[HttpUtil.delheadandback(query().toString()).split(",").length/2];
	String[] blistArray=new String[HttpUtil.delheadandback(query().toString()).split(",").length/2];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aroundg);	
		this.findViews();
        this.fillViews();
          
	}
	 @Override
	    public void onPause() {
	        if (progressDialog.isShowing()) {
	            progressDialog.dismiss();
	        }
	        super.onPause();
	    }
	 private void findViews() {
		    mylist = (ListView) findViewById(R.id.myListView);
			OnItemClickListener listener = new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
				    //标题的选择显示
					Intent it = new Intent(Aroundg.this, Aroundinfo.class);  
					Bundle bundle = new Bundle();
				    bundle.putString("data", alistArray[position].toString());
				    /* 将Bundle 对象assign 给Intent */
				    it.putExtras(bundle);
					startActivity(it); 
				}
			};
			mylist.setOnItemClickListener(listener);
	    }
	 private class InfoAdapter extends ArrayAdapter<String> {

	        public InfoAdapter(String[] mlistArray) {
	            super(Aroundg.this, R.layout.around_tel, mlistArray);
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
	    private void fillViews() {
	        progressDialog = new ProgressDialog(this);
	        progressDialog.setCancelable(false);
	        progressDialog.setMessage(getString(R.string.retrieving_data));
	        new Thread(queryInfoDetail, "queryInfoDetailThread").start();
	    }
	    private Runnable queryInfoDetail = new Runnable() {

	        private void sendMessage(String response) {
	            Bundle bundle = new Bundle();
	            bundle.putString("response", response);
	            Message message = new Message();
	            message.setData(bundle);
	            handler.sendMessage(message);
	        }

	        public void run() {
	        	String queryString = "type=001";
	    		String url = HttpHelper.BASE_URL+"surrounding/surroundingAction_listSurroundingJSON.action?"+queryString;
	            this.sendMessage(HttpHelper.queryStringForPost(url));
	        }
	    };
	    private final Handler handler = new Handler() {
	        @Override
	        public void handleMessage(final Message msg) {
	            progressDialog.dismiss();
	            JSONArray jsona;
	    		try {
	    			 jsona = new JSONArray(query());
	    			 for (int i = 0; i < jsona.length(); i++)
	    	         {   
	    	    	   JSONObject jsono = jsona.getJSONObject(i); 
	    	    	   alistArray[i]=jsono.get("id").toString();
	    	    	   blistArray[i]=jsono.get("name").toString();
	    		     }
	    			 InfoAdapter infoAdapter = new InfoAdapter(blistArray);
	    			 mylist.setAdapter(infoAdapter);
//	    			 ArrayAdapter<String> arrayA=new ArrayAdapter<String>(Aroundm.this, android.R.layout.simple_list_item_1, blistArray);
//	    			 mylist.setAdapter(arrayA);
	    		} catch (JSONException e) {
	    			e.printStackTrace();
	    		}
	        }
	    };
	private String query(){
		// 查询参数[{"id":1,"name":"文化餐厅"},{"id":2,"name":" 港城"}]
		String queryString = "type=003";
		String url = HttpHelper.BASE_URL+"surrounding/surroundingAction_listSurroundingJSON.action?"+queryString;
		return HttpHelper.queryStringForPost(url);
    }
}
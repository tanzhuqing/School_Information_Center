package cn.heu.hmp.activity.kxjs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.app.MyApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class KxjsJxlActivity extends Activity implements OnItemClickListener
{
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	private MyApplication myapp;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.kxjs_jxl);
        
        myapp = (MyApplication) this.getApplicationContext();
		myapp.loadProperties(this);
		
        this.getData();
        
        ListView lv = (ListView) this.findViewById(R.id.lv_jxl);
        SimpleAdapter adapter = new SimpleAdapter(this, this.data, R.layout.kxjs_jxl_item, new String[]{"name","count"}, new int[]{R.id.jxl_name,R.id.jxl_count});
        
        adapter.setViewBinder(new ViewBinder()
        {   
 	      public boolean setViewValue(View view, Object data, String textRepresentation)
          {   
 	        TextView tv = null;
 	        
 	        if (view.getId() == R.id.jxl_name)
  	        {
         	   tv = (TextView) view;   
  	           tv.setText("☆" + (String) data);   
  		  	   	  
  	           return true;   
  	        }
 	    	 
            if (view.getId() == R.id.jxl_count)
 	        {
        	   tv = (TextView) view;
 	           tv.setText("总共" + (String) data + "间  >");

 	           return true;
 	        }
           
            return false;
 	      }   
 	    });  
        
        lv.setAdapter(adapter);
 	    lv.setOnItemClickListener(this);
    }
	
	private void getData()
    {
		String data_url = this.myapp.getKXJS_JSLURL();
		
		try
        {
			URL url = new URL(data_url); 
			HttpURLConnection hurlc = (HttpURLConnection) url.openConnection(); 
			hurlc.setConnectTimeout(3000);
			hurlc.setReadTimeout(5000);
			BufferedReader br = new BufferedReader(new InputStreamReader(hurlc.getInputStream(), "GB2312"));
			StringBuffer sb = new StringBuffer();  
			String line = null;
			while ((line = br.readLine()) != null)
			  sb.append(line);  
			 
			br.close();
			
			HashMap<String, Object> map = null;
	    	JSONArray jsona = new JSONObject(sb.toString()).getJSONArray("list");
		     for (int i = 0; i < jsona.length(); i++)
		     {   
		    	  JSONObject jsono = jsona.getJSONObject(i);
		    	  map = new HashMap<String, Object>();
				  map.put("id", jsono.get("id").toString());
				  map.put("name", jsono.get("name").toString());
				  map.put("count", jsono.get("count").toString());
				    
			      this.data.add(map);	    
			  }
		} catch (Exception e) {
			Toast.makeText(this, "网络连接错误，请稍后再试", Toast.LENGTH_LONG).show();
			System.out.println(e.getMessage());
		}
    }
	public void onItemClick(AdapterView av, View v, int position, long id)
    {
		HashMap<String, Object> map = (HashMap) this.data.get(position);
		
        Intent intent = new Intent(this, KxjsActivity.class);
        intent.putExtra("id", (String) map.get("id"));
        intent.putExtra("name", (String) map.get("name"));
        startActivity(intent);
	}
}

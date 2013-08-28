package cn.heu.hmp.activity.kxjs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.app.MyApplication;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class KxjsActivity extends Activity implements OnClickListener, Runnable
{
	private String jxl_id = null;
	private String period = null;
	private StringBuffer sb = null;
	private MyApplication myapp;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.kxjs);
        
        this.myapp = (MyApplication) this.getApplicationContext();
		this.myapp.loadProperties(this);

        Intent intent = this.getIntent();
		this.jxl_id = intent.getStringExtra("id");
		String jxl_name = intent.getStringExtra("name");

		TextView tv = (TextView) this.findViewById(R.id.kxjs_jxl_name);
		tv.setText(jxl_name);
		
		ImageView iv = (ImageView) this.findViewById(R.id.kxjs_image0);
		iv.setOnClickListener(this);
        
		iv = (ImageView) this.findViewById(R.id.kxjs_image1);
		iv.setOnClickListener(this);
        
		iv = (ImageView) this.findViewById(R.id.kxjs_image2);
		iv.setOnClickListener(this);
    }
	
	@Override
	protected void onResume()
    {
		super.onResume();
		
		this.onClick((ImageView) this.findViewById(R.id.kxjs_image0));
	}

	private void getData(String period)
    {
		String data_url = this.myapp.getKXJS_URL() + this.jxl_id + "&period=" + period;
		this.sb = null;
		
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
			//System.out.println(sb.toString()); //调试用
  	
		    this.sb = sb; 
		} catch (Exception e) {
			Toast.makeText(this, "网络连接错误，请稍后再试", Toast.LENGTH_LONG).show();
			System.out.println(e.getMessage());
		}
    }
	
	private void getList()
    {
		try
        {
			LinearLayout ll_jxl= (LinearLayout) this.findViewById(R.id.kxjs);
			ll_jxl.removeAllViews();

            LinearLayout ll_js = null;
	    	TextView tv = null;
	    	
			JSONObject jsono_root = new JSONObject(this.sb.toString());
			JSONArray jsona = new JSONObject(this.sb.toString()).getJSONArray("list");
			JSONObject jsono = null;
	    	
		     for (int i = 0; i < jsona.length(); i++)
		     {   
		    	  jsono = jsona.getJSONObject(i);
		    	  
		    	  ll_js = new LinearLayout(this);
				  ll_js.setOrientation(LinearLayout.HORIZONTAL);
				  
		    	  tv = new TextView(this);
				  tv.setText(jsono.getString("id"));
				  tv.setHeight(40);
				  tv.setGravity(Gravity.CENTER);
				  tv.setTextColor(Color.rgb(0, 0, 0));
				  tv.setBackgroundColor(Color.rgb(153, 204, 153));
				  tv.setPadding(5, 5, 0, 0);
				  tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
				  ll_js.addView(tv);
					
				  for (int j = 1; j <= jsono_root.getInt("count"); j++)
				  { 			
				    tv = new TextView(this);
					tv.setText(jsono_root.getString(String.valueOf(j)));
					tv.setHeight(40);
					tv.setGravity(Gravity.CENTER);
					tv.setPadding(5, 5, 0, 0);
					tv.setTextColor(Color.rgb(0, 0, 0));
					tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
					
					if (jsono.getString(String.valueOf(j)).equals("N"))
					  tv.setBackgroundColor(Color.rgb(204, 255, 204));
					else
					  tv.setBackgroundColor(Color.rgb(102, 255, 0));
					
					ll_js.addView(tv);
				  }
				  
				  ll_jxl.addView(ll_js);
			  }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	private Handler handle = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if (KxjsActivity.this.sb != null)
			  KxjsActivity.this.getList();
		}
	};
	
	public void onClick(View v)
    {
		ImageView iv = (ImageView) this.findViewById(R.id.kxjs_image0);
		iv.setImageResource(R.drawable.morning_no);
		iv = (ImageView) this.findViewById(R.id.kxjs_image1);
		iv.setImageResource(R.drawable.afternoon_no);
		iv = (ImageView) this.findViewById(R.id.kxjs_image2);
		iv.setImageResource(R.drawable.night_no);
        
    	if (v.getId() == R.id.kxjs_image0)
        {
    		((ImageView) v).setImageResource(R.drawable.morning_yes);
    		
    		this.period = "0";
    		this.handle.post(this);
    		
			return;
		}
    	
    	if (v.getId() == R.id.kxjs_image1)
        {
    		((ImageView) v).setImageResource(R.drawable.afternoon_yes);
    		
    		this.period = "1";
    		this.handle.post(this);

			return;
		}
    	
    	if (v.getId() == R.id.kxjs_image2)
        {
    		((ImageView) v).setImageResource(R.drawable.night_yes);
    		
    		this.period = "2";
    		this.handle.post(this);
    		
			return;
		}
    }
	public void run()
	{
	  this.getData(this.period);
	  
	  Message msg = this.handle.obtainMessage();
	  this.handle.sendMessage(msg);
	}
}

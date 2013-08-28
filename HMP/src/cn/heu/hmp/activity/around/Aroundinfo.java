package cn.heu.hmp.activity.around;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.gallery.HttpHelper;
import cn.heu.hmp.util.gallery.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;
public class Aroundinfo extends Activity {
/** Called when the activity is first created. */
	String imageUrl = ""; //这就是你需要显示的网络图片
	String content = "";
	String telphone = "tel://";
	String lat = "";
	String lon = "";
	Bitmap bmImg; 
	ImageView imView;
    private RadioButton radioButton1;
	private RadioButton radioButton2;
    private RadioGroup radioGroup;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	/* 加载main.xml Layout */
	setContentView(R.layout.aroundinfo);
	Bundle bundled=this.getIntent().getExtras();
	String ttdata=bundled.getString("data");
	if(query(ttdata)=="Error"){
        System.exit(0);
    }
    if(query(ttdata)!="[]"&&query(ttdata)!="Error"){
        try
        {
            JSONObject jsona = new JSONObject(query(ttdata).toString());
            content= jsona.get("content").toString();
            lon = jsona.get("longitude").toString();
            telphone += jsona.get("tel").toString();
            lat = jsona.get("latitude").toString();
            imageUrl = "http://125.223.113.81:8080/HMPS/upload/"+jsona.get("imageName").toString();
        }
        catch (JSONException e) {
            return;
        }
    }
	TextView tv1 = (TextView) findViewById(R.id.text1);
	imView = (ImageView)findViewById(R.id.imageView1);
	imView.setImageBitmap(returnBitMap(imageUrl)); 
	tv1.setText("      "+content);
    radioButton1 = (RadioButton)findViewById(R.id.radio_button0);
	radioButton2 = (RadioButton)findViewById(R.id.radio_button1);
	this.radioGroup = (RadioGroup) findViewById(R.id.main_radio);
	radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
	{
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			if(checkedId==R.id.radio_button0)
			{
				radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundtel_02,0,0);
			    radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundadd_01,0,0);
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telphone));  
	            startActivity(intent); 		
			}
			else
			{
				radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundtel_01,0,0);
			    radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundadd_02,0,0);					
				Intent it = new Intent(Aroundinfo.this, Googlestreet.class);  
				Bundle bundle = new Bundle();
			    bundle.putString("plon", lon);
			    bundle.putString("plat", lat);
			    it.putExtras(bundle);
				startActivity(it);					
			}
		}
	});
	tv1.setMovementMethod(ScrollingMovementMethod.getInstance());
}
public Bitmap returnBitMap(String url) { 
	URL myFileUrl = null; 
	Bitmap bitmap = null; 
	try { 
		myFileUrl = new URL(url); 
		} catch (MalformedURLException e) { 
		e.printStackTrace(); 
	} 
	try { 
		HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection(); 
		conn.setDoInput(true); 
		conn.connect(); 
		InputStream is = conn.getInputStream(); 
		bitmap = BitmapFactory.decodeStream(is); 
		is.close(); 
		} catch (IOException e) { 
		e.printStackTrace(); 
	} 
	return bitmap; 
	} 
private String query(String id){
	// 查询参数
	try{
		String queryString = "id="+id;
		String url = HttpHelper.BASE_URL+"surrounding/surroundingAction_listSurroundingDetailJSON.action?"+queryString;		
		return HttpHelper.queryStringForPost(url);
	}
	catch(Exception ex){
		return "Error";
	}
}
}
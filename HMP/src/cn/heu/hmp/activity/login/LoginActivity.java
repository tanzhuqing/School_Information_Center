package cn.heu.hmp.activity.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.heu.hmp.activity.R;
import cn.heu.hmp.activity.myapps.MyappsMainActivity;
import cn.heu.hmp.util.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener, Runnable
{
	private MyApplication myapp;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.login);
	
		this.myapp = (MyApplication) this.getApplicationContext();
		this.myapp.loadProperties(this);
		
		Button login_ok = (Button) this.findViewById(R.id.login_ok);
		login_ok.setOnClickListener(this);
	    
		Button login_cancel = (Button) this.findViewById(R.id.login_cancel);
		login_cancel.setOnClickListener(this);
    }
    
    public void onClick(View v)
    {
        if (v.getId() == R.id.login_ok)
        {
        	this.handle.post(this);
        	
        	return;
		}
        
        if (v.getId() == R.id.login_cancel)
        {
        	this.finish();
			return;
		}
    }
    
    private Handler handle = new Handler()
	{
	  //在本功能中不需要处理反馈的消息,但该结构需要保留
	};
	
	public void run()
	{
		String user = ((EditText) this.findViewById(R.id.login_user)).getText().toString();
    	String password = ((EditText) this.findViewById(R.id.login_password)).getText().toString();
    	String data_url = this.myapp.getLOGIN_URL()+"?user=" + user + "&password=" + password;
    	TextView login_message = (TextView) this.findViewById(R.id.login_message);
    	login_message.setText("正在登录，请耐心等待。。。");

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
			
            if (sb.toString().trim().equals("yes"))
            {
              //将帐号和密码存进全局变量
              MyApplication myApplication = (MyApplication) this.getApplication();
              myApplication.setUser(user);
              myApplication.setPassword(password);
                
              login_message.setText("登录成功，等待进入后台页面");

              //进入后台页面
				LoginActivity.this.startActivity(new Intent(LoginActivity.this, MyappsMainActivity.class));
            }
            else
              login_message.setText("登录失败，帐号或密码错误");
		} catch (Exception e) {
			login_message.setText("登录失败，网络连接错误");
		}
	}
}

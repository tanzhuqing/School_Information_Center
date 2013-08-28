package cn.heu.hmp.util.app;

import java.util.Properties;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {

	private static final String LOGTAG = LogUtil.makeLogTag(MyApplication.class);
	
	private Properties props;
	
	private boolean isload=false;
	//主页调用新闻地址
	private String Main_NEWSURL="";
	//新闻页URL
	private String News_URL="";
	//新闻内容页URL
	private String News_INFO_URL="";
	//校园美景URL
	private String XYMJ_URL="";
	//校园美景大图URL
	private String XYMJ_bigURL="";
	//空闲教师URL
	private String KXJS_URL="";
	//空闲教师 教师楼 URL
	private String KXJS_JSLURL="";
	//登录
	private String User = "";
	private String Password = "";
	
	private String LOGIN_URL="";
	
	public MyApplication() {
		Log.d("", ""+System.currentTimeMillis());
	}
	/**
	 * load properties
	 * @return Properties
	 */
	public Properties loadProperties(Context context) {
		if(!isload){
	        props = new Properties();
	        Log.d(LOGTAG, "加载配置文件");
	        try {
	            int id = context.getResources().getIdentifier("syspro", "raw",context.getPackageName());
	            props.load(context.getResources().openRawResource(id));
	        } catch (Exception e) {
	            Log.e(LOGTAG, "Could not find the properties file.", e);
	        }
	        Main_NEWSURL=props.getProperty("Main_NEWSURL");
	        News_URL=props.getProperty("News_URL");
	        
	        KXJS_URL=props.getProperty("KXJS_URL");
	        KXJS_JSLURL=props.getProperty("KXJS_JSLURL");
	        
	        LOGIN_URL=props.getProperty("LOGIN_URL");
	        
	        News_INFO_URL=props.getProperty("News_INFO_URL");
	        XYMJ_URL=props.getProperty("XYMJ_URL");
	        isload=true;
		}
        return props;
    }
	
	public String getLOGIN_URL() {
		return LOGIN_URL;
	}
	public void setLOGIN_URL(String lOGIN_URL) {
		LOGIN_URL = lOGIN_URL;
	}
	public String getKXJS_JSLURL() {
		return KXJS_JSLURL;
	}
	public void setKXJS_JSLURL(String kXJS_JSLURL) {
		KXJS_JSLURL = kXJS_JSLURL;
	}
	public Properties getProps() {
		return props;
	}
	public void setProps(Properties props) {
		this.props = props;
	}
	public boolean isIsload() {
		return isload;
	}
	public void setIsload(boolean isload) {
		this.isload = isload;
	}
	public String getMain_NEWSURL() {
		return Main_NEWSURL;
	}
	public void setMain_NEWSURL(String main_NEWSURL) {
		Main_NEWSURL = main_NEWSURL;
	}
	public String getNews_URL() {
		return News_URL;
	}
	public void setNews_URL(String news_URL) {
		News_URL = news_URL;
	}
	public String getKXJS_URL() {
		return KXJS_URL;
	}
	public void setKXJS_URL(String kXJS_URL) {
		KXJS_URL = kXJS_URL;
	}
	public static String getLogtag() {
		return LOGTAG;
	}
	public String getNews_INFO_URL() {
		return News_INFO_URL;
	}
	public void setNews_INFO_URL(String news_INFO_URL) {
		News_INFO_URL = news_INFO_URL;
	}
	public String getXYMJ_URL() {
		return XYMJ_URL;
	}
	public void setXYMJ_URL(String xYMJ_URL) {
		XYMJ_URL = xYMJ_URL;
	}
	public String getXYMJ_bigURL() {
		return XYMJ_bigURL;
	}
	public void setXYMJ_bigURL(String xYMJ_bigURL) {
		XYMJ_bigURL = xYMJ_bigURL;
	}

	
	public String getUser()
    {
	  return this.User;
	}

	public void setUser(String User)
	{
	  this.User = User;
	}
	  
	public String getPassword()
    {
      return this.Password;
    }

    public void setPassword(String Password)
    {
      this.Password = Password;
    }
}

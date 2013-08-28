package cn.heu.hmp.activity.tel;
import cn.heu.hmp.activity.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
public class Teltab extends TabActivity {
    /**
     * TabHost控件
     */
    private TabHost tabHost;
    /**
     * TabWidget控件
     */
    private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioButton radioButton3;
    private static final String TAB_teljj = "直属";
	private static final String TAB_telzz = "机关";
	private static final String TAB_telyy = "院系";
    private TabWidget tabWidget;
    private RadioGroup radioGroup;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.teltab);
        tabHost = getTabHost(); 
        radioButton1 = (RadioButton)findViewById(R.id.radio_button0);
		radioButton2 = (RadioButton)findViewById(R.id.radio_button1);
		radioButton3 = (RadioButton)findViewById(R.id.radio_button2);
        //outTab1选项卡
        TabSpec ts1 = tabHost.newTabSpec(TAB_teljj).setIndicator(
        		TAB_teljj);
		ts1.setContent(new Intent(this, Telz.class));
		tabHost.addTab(ts1);

        TabSpec ts2 = tabHost.newTabSpec(TAB_telzz).setIndicator(
        		TAB_telzz);
		ts2.setContent(new Intent(this, Telj.class));
		tabHost.addTab(ts2);

        TabSpec ts3 = tabHost.newTabSpec(TAB_telyy).setIndicator(
        		TAB_telyy);
		ts3.setContent(new Intent(this, Tely.class));
		tabHost.addTab(ts3);

     // 声明包装底框radioGroup
 		this.radioGroup = (RadioGroup) findViewById(R.id.main_radio);
 		// 为radioGroup设置4个radio_button
 		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
 		{
 			public void onCheckedChanged(RadioGroup group, int checkedId)
 			{
 				switch (checkedId)
 				{
 				case R.id.radio_button0:
 					tabHost.setCurrentTabByTag(TAB_teljj);
 					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.telj_02,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.telz_01,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tely_01,0,0);
 					break;
 				case R.id.radio_button1:
 					tabHost.setCurrentTabByTag(TAB_telzz);
 					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.telj_01,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.telz_02,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tely_01,0,0);
 					break;
 				case R.id.radio_button2:
 					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.telj_01,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.telz_01,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tely_02,0,0);
 					tabHost.setCurrentTabByTag(TAB_telyy);
 					break;
 				}
 			}
 		});
    }
}

package cn.heu.hmp.activity.around;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.activity.tel.Telj;
import cn.heu.hmp.activity.tel.Tely;
import cn.heu.hmp.activity.tel.Telz;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;
public class Around extends TabActivity {
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
    private static final String TAB_aroundm = "美食";
	private static final String TAB_aroundy = "娱乐";
	private static final String TAB_aroundg = "购物";
    private TabWidget tabWidget;
    private RadioGroup radioGroup;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.around);
        tabHost = getTabHost(); 
        radioButton1 = (RadioButton)findViewById(R.id.radio_button0);
		radioButton2 = (RadioButton)findViewById(R.id.radio_button1);
		radioButton3 = (RadioButton)findViewById(R.id.radio_button2);
        //outTab1选项卡
        TabSpec ts1 = tabHost.newTabSpec(TAB_aroundm).setIndicator(
        		TAB_aroundm);
		ts1.setContent(new Intent(this, Aroundm.class));
		tabHost.addTab(ts1);

        TabSpec ts2 = tabHost.newTabSpec(TAB_aroundy).setIndicator(
        		TAB_aroundy);
		ts2.setContent(new Intent(this, Aroundy.class));
		tabHost.addTab(ts2);

        TabSpec ts3 = tabHost.newTabSpec(TAB_aroundg).setIndicator(
        		TAB_aroundg);
		ts3.setContent(new Intent(this, Aroundg.class));
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
 					tabHost.setCurrentTabByTag(TAB_aroundm);
 					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundm_02,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundy_01,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundg_01,0,0);
 					break;
 				case R.id.radio_button1:
 					tabHost.setCurrentTabByTag(TAB_aroundy);
 					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundm_01,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundy_02,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundg_01,0,0);
 					break;
 				case R.id.radio_button2:
 					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundm_01,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundy_01,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.aroundg_02,0,0);
 					tabHost.setCurrentTabByTag(TAB_aroundg);
 					break;
 				}
 			}
 		});
    }
}



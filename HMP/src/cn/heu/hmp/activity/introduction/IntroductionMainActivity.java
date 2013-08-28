package cn.heu.hmp.activity.introduction;

import java.io.Serializable;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import cn.heu.hmp.activity.R;

public class IntroductionMainActivity extends TabActivity implements
		Serializable
{
	private static final long serialVersionUID = 1L;
	private TabHost tabHost;
	private TextView title;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioButton radioButton3;
	private RadioButton radioButton4;
	private static final String TAB_INTRODUCE = "学校简介";
	private static final String TAB_HISTORY = "辉煌历史";
	private static final String TAB_ORGANIZATION = "组织机构";
	private static final String TAB_VIEW = "校园美景";
	private RadioGroup radioGroup;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.introductionmain);
		title = (TextView)findViewById(R.id.info_main_title);
		radioButton1 = (RadioButton)findViewById(R.id.radio_button0);
		radioButton2 = (RadioButton)findViewById(R.id.radio_button1);
		radioButton3 = (RadioButton)findViewById(R.id.radio_button2);
		radioButton4 = (RadioButton)findViewById(R.id.radio_button3);
		// 获取框架
		tabHost = getTabHost();

		// 声明4个子框架TabSpec s1,s2,s3,s4

		TabSpec ts1 = tabHost.newTabSpec(TAB_INTRODUCE).setIndicator(
				TAB_INTRODUCE);
		ts1.setContent(new Intent(this, CampusIntroductionActivity.class));
		tabHost.addTab(ts1);

		TabSpec ts2 = tabHost.newTabSpec(TAB_HISTORY).setIndicator(TAB_HISTORY);
		ts2.setContent(new Intent(this, CampusHistoryActivity.class));
		tabHost.addTab(ts2);

		TabSpec ts3 = tabHost.newTabSpec(TAB_ORGANIZATION).setIndicator(
				TAB_ORGANIZATION);
		ts3.setContent(new Intent(this, CampusOrganizationAcitvity.class));
		tabHost.addTab(ts3);

		TabSpec ts4 = tabHost.newTabSpec(TAB_VIEW).setIndicator(TAB_VIEW);
		ts4.setContent(new Intent(this, CampusViewActivity.class));
		tabHost.addTab(ts4);
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
					tabHost.setCurrentTabByTag(TAB_INTRODUCE);
					title.setBackgroundResource(R.drawable.top1);
					title.setText(TAB_INTRODUCE);
					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn1_2,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn2,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn3,0,0);
					radioButton4.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn4,0,0);
					break;
				case R.id.radio_button1:
					tabHost.setCurrentTabByTag(TAB_HISTORY);
					title.setBackgroundResource(R.drawable.top2);
					title.setText(TAB_HISTORY);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn2_2,0,0);
					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn1,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn3,0,0);
					radioButton4.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn4,0,0);
					break;
				case R.id.radio_button2:
					tabHost.setCurrentTabByTag(TAB_ORGANIZATION);
					title.setBackgroundResource(R.drawable.top3);
					title.setText(TAB_ORGANIZATION);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn3_3,0,0);
					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn1,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn2,0,0);
					radioButton4.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn4,0,0);
					break;
				case R.id.radio_button3:
					tabHost.setCurrentTabByTag(TAB_VIEW);
					title.setBackgroundResource(R.drawable.top4);
					title.setText(TAB_VIEW);
					radioButton4.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn4_4,0,0);
					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn1,0,0);
					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn2,0,0);
					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.btn3,0,0);
					break;
				}
			}
		});
		
		
	}
}
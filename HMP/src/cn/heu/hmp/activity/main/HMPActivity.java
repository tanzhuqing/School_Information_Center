package cn.heu.hmp.activity.main;

import cn.heu.hmp.activity.R;
import cn.heu.hmp.activity.info.InfoListActivity;
import cn.heu.hmp.activity.introduction.IntroductionMainActivity;
import cn.heu.hmp.activity.kxjs.KxjsJxlActivity;
import cn.heu.hmp.activity.login.LoginActivity;

import cn.heu.hmp.activity.news.NewsListActivity;
import cn.heu.hmp.activity.around.*;
import cn.heu.hmp.activity.tel.*;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;

public class HMPActivity extends Activity implements OnClickListener ,OnTouchListener{
	
	private Integer[] butids=new Integer[]{R.id.but_aa,R.id.but_bb,R.id.but_cc,R.id.but_dd,R.id.but_ee,R.id.but_ff,R.id.but_gg,R.id.but_hh,R.id.but_rr};
	private Integer[] bgs=new Integer[]{R.id.but_a,R.id.but_b,R.id.but_c,R.id.but_d,R.id.but_e,R.id.but_f,R.id.but_g,R.id.but_h,R.id.but_r};
	
	private Integer clickid=88;
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
		
		super.onCreate(savedInstanceState);
		//���ر���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_fw); 
		//���¼�
		for(int i=0;i<butids.length;i++){
				ImageView but= (ImageView) this.findViewById(butids[i]);
				but.setTag(i);
				but.setOnClickListener(this);
				but.setOnTouchListener(this);
		}

		//serviceManager
        //ServiceManager serviceManager = new ServiceManager(this);
        //serviceManager.setNotificationIcon(R.drawable.notification);
        //serviceManager.startService();
	}
	
	public void onClick(View v) {
		Log.e("img_clik", ""+v.getTag());

		
		Intent intent = new Intent();
		intent.putExtra("testIntent", "123");
		if(v.getId()!=R.id.but_aa && v.getId()!=R.id.but_dd  ){
			switch(v.getId()){
				case R.id.but_aa:
					//����
					//
					break;
				case R.id.but_bb:
					//֪ͨ����
					intent.setClass(HMPActivity.this, InfoListActivity.class);
					intent.setClass(HMPActivity.this, InfoListActivity.class);
					break;
				case R.id.but_cc:
					//�ܱ߻���
					intent.setClass(HMPActivity.this, Around.class);
					break;
				case R.id.but_dd:
					//��
					//
					break;
				case R.id.but_ee:
					//���н���
					intent.setClass(HMPActivity.this, KxjsJxlActivity.class);
					break;
				case R.id.but_ff:
					//У԰�绰
					intent.setClass(HMPActivity.this, Teltab.class);
					break;
				case R.id.but_gg:
					//�ȵ�����
					intent.setClass(HMPActivity.this, NewsListActivity.class);
					break;
				case R.id.but_hh:
					//У԰�ſ�
					intent.setClass(HMPActivity.this, IntroductionMainActivity.class);
					break;
				case R.id.but_rr:
					//��̨��¼
					intent.setClass(HMPActivity.this, LoginActivity.class);
					break;
			}
			
			HMPActivity.this.startActivity(intent);
		}
		
	}

	public boolean onTouch(View v, MotionEvent event) {
		//���ı���
		int check=Integer.parseInt(v.getTag().toString());
		int bgid=bgs[check];
		
		//Log.e("touch", ""+bgid);
		if(check!=0 && check!=3){
			if(clickid==88){
				//ֱ��Ϊ���¼����±���
				HMPActivity.this.findViewById(bgid).setBackgroundResource(R.drawable.huangbg);
				clickid=bgid;
			}else{
				//���ԭ�б�����Ϊ�µ���¼����±���
				HMPActivity.this.findViewById(clickid).setBackgroundResource(R.drawable.image_n1);
				HMPActivity.this.findViewById(bgid).setBackgroundResource(R.drawable.huangbg);
				clickid=bgid;
			}
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
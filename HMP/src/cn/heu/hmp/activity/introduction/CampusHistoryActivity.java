package cn.heu.hmp.activity.introduction;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.introduction.Data;
import cn.heu.hmp.util.introduction.LoadImage;
import cn.heu.hmp.util.introduction.bean.SchoolIntroduction;

public class CampusHistoryActivity extends Activity
{
	private TextView t3;
	private ImageView imageView3;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campushistory);
		t3 = (TextView) findViewById(R.id.text3);
		imageView3 = (ImageView) findViewById(R.id.image3);
		String baseurl = new Data().getloadimageurl();
		LinkedList<SchoolIntroduction> schoolIntroductions = new Data().getData_schoolIntroduction();
		
		if(schoolIntroductions.get(2).getMyText().equals(""))
		{
			schoolIntroductions.get(2).setMyText("您访问的文本未设置！");
		}
		
		if(schoolIntroductions.get(2).getImageName().equals(""))
		{
			schoolIntroductions.get(2).setImageName("NoPic2.jpg");
		}

		t3.setText(schoolIntroductions.get(2).getMyText());
		String resultUrl_m3 = baseurl + schoolIntroductions.get(2).getImageName();
		new LoadImage().load(resultUrl_m3, imageView3);

	}
}

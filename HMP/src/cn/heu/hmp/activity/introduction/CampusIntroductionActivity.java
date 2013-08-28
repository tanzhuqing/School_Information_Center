package cn.heu.hmp.activity.introduction;

import java.util.LinkedList;
import java.util.Vector;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.introduction.CYTextView;
import cn.heu.hmp.util.introduction.Data;
import cn.heu.hmp.util.introduction.LoadImage;
import cn.heu.hmp.util.introduction.bean.SchoolIntroduction;

public class CampusIntroductionActivity extends Activity
{
	private TextView t1;
	private TextView t2;
	private ImageView imageView1;
	private ImageView imageView2;
	private CYTextView mCYTextView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campusintroduction);

		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		imageView1 = (ImageView) findViewById(R.id.image1);
		imageView2 = (ImageView) findViewById(R.id.image2);
		// mCYTextView = (CYTextView)findViewById(R.id.mv);

		String baseurl = new Data().getloadimageurl();
		// 下载数据
		LinkedList<SchoolIntroduction> schoolIntroductions = new Data()
				.getData_schoolIntroduction();
		// String t1 = schoolIntroductions.get(0).getMyText();
		// System.out.println(t1);
		// mCYTextView.SetText(t1);
		// 获取文本数据
		t1.setText(schoolIntroductions.get(0).getMyText());
		t2.setText(schoolIntroductions.get(1).getMyText());
		// 拼接完整图片索引
		String resultUrl_m1 = baseurl
				+ schoolIntroductions.get(0).getImageName();
		String resultUrl_m2 = baseurl
				+ schoolIntroductions.get(1).getImageName();
		// 加载图片
		new LoadImage().load(resultUrl_m1, imageView1);
		new LoadImage().load(resultUrl_m2, imageView2);
	}
}

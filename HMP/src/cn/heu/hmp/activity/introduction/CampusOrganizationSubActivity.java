package cn.heu.hmp.activity.introduction;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.introduction.Data;
import cn.heu.hmp.util.introduction.LoadImage;
import cn.heu.hmp.util.introduction.bean.OrganizationSub;
public class CampusOrganizationSubActivity extends Activity
{
	String baseurl = new Data().getloadimageurl();
	private TextView text;
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.campusorganizationsub);
		text = (TextView)findViewById(R.id.text4);
		imageView = (ImageView)findViewById(R.id.image4);
		
		int groupPosition = (Integer)getIntent().getExtras().get("groupPosition");
		int childPosition = (Integer)getIntent().getExtras().get("childPosition");
		long id = (Long)getIntent().getExtras().get("id");
		//拼接索引
		String result = String.valueOf(groupPosition) + String.valueOf(childPosition) + String.valueOf(id);
		//获取数据
		OrganizationSub organizationSub = new Data().getData_num(result).get(0);
		//设置数据
		text.setText(organizationSub.getMyText());
		String url = baseurl + organizationSub.getImage();
		new LoadImage().load(url,imageView);
	}
}

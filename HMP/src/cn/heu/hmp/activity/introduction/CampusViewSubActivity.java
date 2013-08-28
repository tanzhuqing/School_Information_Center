package cn.heu.hmp.activity.introduction;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.introduction.LoadImage;

public class CampusViewSubActivity extends Activity
{
	ImageView imageView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.campusviewsub);
		imageView = (ImageView) findViewById(R.id.image);

		Bundle bunde = this.getIntent().getExtras();
		String picURL = bunde.getString("picURL");
		if (!picURL.equals(""))
		{
			new LoadImage().load(picURL, imageView);
		}
	}

}

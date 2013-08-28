package cn.heu.hmp.activity.info;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.gallery.HttpHelper;

public class InfoDetailActivity extends Activity {

    private TextView mainTitle;
    private TextView detailTitle;
    private TextView detailDate;
    private TextView detailContent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info_detail_test);

        this.findViews();

        this.fillViews();

    }

    @Override
    public void onPause() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onPause();
    }

    private void findViews() {
        this.mainTitle = (TextView) this.findViewById(R.id.info_detail_main_title);//主标题
        this.detailTitle = (TextView) this.findViewById(R.id.info_detail_title);//细节标题
        this.detailDate = (TextView) this.findViewById(R.id.info_detail_date);//日期
        this.detailContent = (TextView) this.findViewById(R.id.info_detail_content);//数据
    }

    private void fillViews() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.retrieving_data));

        new Thread(queryInfoDetail, "queryInfoDetailThread").start();
    }

    private Runnable queryInfoDetail = new Runnable() {

        private void sendMessage(String response) {
            Bundle bundle = new Bundle();
            bundle.putString("response", response);
            Message message = new Message();
            message.setData(bundle);
            handler.sendMessage(message);
        }

        public void run() {
            Intent intent = getIntent();
            String id = intent.getStringExtra("id");
            Map<String, String> param = new HashMap<String, String>();
            param.put("id", id);
            String url = HttpHelper.BASE_URL
                    + "info/infoAction_listInfoDetailJSON.action";
            this.sendMessage(HttpHelper.queryStringForPost(url, param));
        }
    };

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(final Message msg) {
            progressDialog.dismiss();
            String response = msg.getData().getString("response");
            try {
                JSONObject jsonObject = new JSONObject(response);
                InfoDetailActivity.this.mainTitle.setText("详细信息");
                InfoDetailActivity.this.detailTitle.setText(jsonObject.get("title").toString());
                InfoDetailActivity.this.detailDate.setText(jsonObject.get("publishDate").toString());
                InfoDetailActivity.this.detailContent.setText(jsonObject.get("content").toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


}

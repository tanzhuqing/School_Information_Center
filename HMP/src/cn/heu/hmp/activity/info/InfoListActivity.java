package cn.heu.hmp.activity.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.entity.info.Info;
import cn.heu.hmp.util.gallery.HttpHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InfoListActivity extends TabActivity {

    private TabHost mth;
    private static final String NOTICE = "重要通知";
    private static final String LECTURE = "会议讲座";
    private static final String INFO = "综合信息";
    private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioButton radioButton3;
	private TextView textViewTop;
    private ProgressDialog progressDialog;
    private RadioGroup radioGroup;
    private TextView title;
    private ListView infoListView;
    private List<Info> infoList;
    private InfoAdapter infoAdapter;
    private String infoType; //信息类型

    public InfoListActivity() {
        this.infoType = NOTICE;
        this.infoList = new ArrayList<Info>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.info_list);
        
        this.findViews();
        
        this.fillViews();

        this.addClickListener();
    }

    @Override
    public void onPause() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onPause();
    }

    private void findViews() {
        mth = this.getTabHost();
        this.radioGroup = (RadioGroup) findViewById(R.id.main_radio);
        this.title = (TextView) this.findViewById(R.id.info_main_title);
        this.infoListView = (ListView) this.findViewById(R.id.info_list);
        radioButton1 = (RadioButton)findViewById(R.id.info_notice_btn);
		radioButton2 = (RadioButton)findViewById(R.id.info_lecture_btn);
		radioButton3 = (RadioButton)findViewById(R.id.info_info_btn);
		textViewTop = (TextView)findViewById(R.id.info_main_title);
    }

    private void fillViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.retrieving_data));

        this.title.setText(infoType);
        this.infoList.clear();
        infoAdapter = new InfoAdapter(infoList);
        infoListView.setAdapter(infoAdapter);

        new QueryInfoTask().execute(infoType);
    }

    private void addClickListener() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.info_notice_btn:
                        InfoListActivity.this.infoType = InfoListActivity.NOTICE;
                        mth.setCurrentTabByTag(NOTICE);
                        fillViews();
                        radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_notice_btn,0,0);
    					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_lecture_btn_pressed,0,0);
    					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_info_btn_pressed,0,0);
    					textViewTop.setText(NOTICE);
    					textViewTop.setBackgroundResource(R.drawable.notice_top);
    					
                        break;
                    case R.id.info_lecture_btn:
                        InfoListActivity.this.infoType = InfoListActivity.LECTURE;
                        mth.setCurrentTabByTag(LECTURE);
                        fillViews();
                        textViewTop.setText(LECTURE);
                        textViewTop.setBackgroundResource(R.drawable.lecture_top);
                        radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_lecture_btn,0,0);
    					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_notice_btn_pressed,0,0);
    					radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_info_btn_pressed,0,0);
//    					LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    			        View view = inflater.inflate(R.layout.info_list_item, null);
//    			        image = (ImageView)view.findViewById(R.id.info_img);
//    					image.setBackgroundResource(R.drawable.info_lecture_btn);
    					
                        break;
                    case R.id.info_info_btn:
                        InfoListActivity.this.infoType = InfoListActivity.INFO;
                        mth.setCurrentTabByTag(INFO);
                        fillViews();
                        textViewTop.setText(INFO);
                        textViewTop.setBackgroundResource(R.drawable.info_top);
                        radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_info_btn,0,0);
    					radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_notice_btn_pressed,0,0);
    					radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.info_lecture_btn_pressed,0,0);
                        break;

                }
            }
        });

        this.infoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("id", infoList.get(i).getId());
                intent.setClass(InfoListActivity.this, InfoDetailActivity.class);
                InfoListActivity.this.startActivity(intent);

            }
        });
    }

    private void resetInfoItems(List<Info> infoItems) {
        infoList.clear();
        infoList.addAll(infoItems);
        infoAdapter.notifyDataSetChanged();
    }

    private class InfoAdapter extends ArrayAdapter<Info> {

        public InfoAdapter(List<Info> infos) {
            super(InfoListActivity.this, R.layout.info_list_item, infos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.info_list_item, parent,
                        false);
            }
            TextView title = (TextView) convertView.findViewById(R.id.info_title);
            TextView publishDate = (TextView) convertView.findViewById(R.id.info_date);
            ImageView image = (ImageView)convertView.findViewById(R.id.info_img);
            
            if(InfoListActivity.this.infoType == InfoListActivity.LECTURE){
            	image.setBackgroundResource(R.drawable.info_lecture_btn_pressed);
            }
            
            if(InfoListActivity.this.infoType == InfoListActivity.NOTICE){
            	image.setBackgroundResource(R.drawable.info_notice_btn_pressed);
            }
            
            if(InfoListActivity.this.infoType == InfoListActivity.INFO){
            	image.setBackgroundResource(R.drawable.info_info_btn_pressed);
            }
            
            Info info = getItem(position);

            if (info != null) {
                title.setText(info.getTitle());
                publishDate.setText(info.getPublishDate());
            }
            return convertView;

        }
    }

    private class QueryInfoTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... type) {
            Map<String, String> param = new HashMap<String, String>();
            param.put("infoType", type[0]);
            String url = HttpHelper.BASE_URL
                    + "info/infoAction_listInfoJSON.action";
            return HttpHelper.queryStringForPost(url, param);
        }

        @Override
        protected void onPostExecute(String result) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            try {
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() != 0) {
                    List<Info> infos = new ArrayList<Info>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Info info = new Info();
                        info.setId(jsonObject.getString("id"));
                        info.setTitle(jsonObject.getString("title"));
                        info.setPublishDate(jsonObject.getString("publishDate"));
                        infos.add(info);
                    }
                    resetInfoItems(infos);
                } else {
                    Toast.makeText(InfoListActivity.this,
                            getString(R.string.missing_data),
                            Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

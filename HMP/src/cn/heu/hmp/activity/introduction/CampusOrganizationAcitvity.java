package cn.heu.hmp.activity.introduction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.heu.hmp.activity.R;
import cn.heu.hmp.util.introduction.Data;
import cn.heu.hmp.util.introduction.bean.Organization;
import cn.heu.hmp.util.introduction.bean.OrganizationSub;

public class CampusOrganizationAcitvity extends Activity
{
	private ProgressDialog progressDialog;

	private List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
	private List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

	private ExAdapter adapter;
	private ExpandableListView exList;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.campusorganization);
		// 进度条
		this.progress();
		// 将context放到ExAdapter中
		adapter = new ExAdapter(CampusOrganizationAcitvity.this);
		// 异步加载数据
		new OrganizationAsyncLoadTask(this, adapter).execute();
		// 声明ExpandableListView
		exList = (ExpandableListView) findViewById(R.id.list);
		// 设置ExpandableListView
		this.exlist123(exList);
	}

	public void progress()
	{
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("数据加载中，请稍等");
	}

	public void exlist123(ExpandableListView exList)
	{
		exList.setAdapter(adapter);

		exList.setGroupIndicator(null);

		exList.setDivider(null);

		exList.setOnChildClickListener(new OnChildClickListener()
		{
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id)
			{
				Intent intent = new Intent();
				intent.setClass(CampusOrganizationAcitvity.this,
						CampusOrganizationSubActivity.class);
				intent.putExtra("groupPosition", groupPosition);
				intent.putExtra("childPosition", childPosition);
				intent.putExtra("id", id);
				startActivity(intent);
				return true;
			}
		});
	}

	@Override
	protected void onPause()
	{
		if (progressDialog.isShowing())
		{
			progressDialog.dismiss();
		}
		super.onPause();
	}

	class ExAdapter extends BaseExpandableListAdapter
	{
		CampusOrganizationAcitvity exlistview;

		public ExAdapter(CampusOrganizationAcitvity elv)
		{
			super();
			exlistview = elv;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent)
		{

			View view = convertView;
			
			if (view == null)
			{
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater
						.inflate(R.layout.campusorganizationparent, null);
			}

			TextView title = (TextView) view.findViewById(R.id.content_001);
			title.setText(getGroup(groupPosition).toString());

			ImageView image = (ImageView) view.findViewById(R.id.tubiao);
			if (isExpanded)
				image.setBackgroundResource(R.drawable.btn_browser2);
			else
				image.setBackgroundResource(R.drawable.btn_browser);

			return view;
		}

		public long getGroupId(int groupPosition)
		{
			return groupPosition;
		}

		public Object getGroup(int groupPosition)
		{
			return groups.get(groupPosition).get("group").toString();
		}

		public int getGroupCount()
		{
			return groups.size();

		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (view == null)
			{
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.campusorganizationchild, null);
			}
			final TextView title = (TextView) view
					.findViewById(R.id.child_text);
			title.setText(childs.get(groupPosition).get(childPosition)
					.get("child").toString());
			return view;
		}

		public long getChildId(int groupPosition, int childPosition)
		{
			return childPosition;
		}

		public Object getChild(int groupPosition, int childPosition)
		{
			return childs.get(groupPosition).get(childPosition).get("child")
					.toString();
		}

		public int getChildrenCount(int groupPosition)
		{
			return childs.get(groupPosition).size();
		}

		public boolean hasStableIds()
		{
			return true;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition)
		{
			return true;
		}

	}

	public class OrganizationAsyncLoadTask extends
			AsyncTask<Void, Void, Map<String, Object>>
	{
		private ExAdapter adapter;

		public OrganizationAsyncLoadTask(Context context, ExAdapter adapter)
		{
			this.adapter = adapter;
		}

		@Override
		protected void onPreExecute()
		{
			progressDialog.show();
		}

		@Override
		protected Map<String, Object> doInBackground(Void... params)
		{
			Map<String, Object> result = new HashMap<String, Object>();

			List<Organization> groupsWithTomcat = new Data()
					.getData_organization();
			List<OrganizationSub> childsWithTomcat = new Data()
					.getData_organizationSub();

			System.out.println(groupsWithTomcat);
			System.out.println(childsWithTomcat);

			List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
			List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

			// 父控件获取tomcat数据
			for (int i = 0; i < groupsWithTomcat.size(); i++)
			{
				String groupName = groupsWithTomcat.get(i).getGroupName();
				Map<String, String> group = new HashMap<String, String>();
				group.put("group", groupName);
				groups.add(group);
			}
			// 子控件获取tomcat数据
			for (int k = 0; k < groupsWithTomcat.size(); k++)
			{
				List<Map<String, String>> child_sub = new ArrayList<Map<String, String>>();
				String groupName = groupsWithTomcat.get(k).getGroupName();
				for (int j = 0; j < childsWithTomcat.size(); j++)
				{
					String tid = childsWithTomcat.get(j).getTid();
					String childName = childsWithTomcat.get(j).getChild();
					if (!childName.equals("") && !tid.equals("")
							&& tid.equals(groupName))
					{
						Map<String, String> child = new HashMap<String, String>();
						child.put("child", childName);
						child_sub.add(child);
					}
				}
				childs.add(child_sub);
			}

			System.out.println(groups);
			System.out.println(childs);

			result.put("groups", groups);
			result.put("childs", childs);

			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(Map<String, Object> result)
		{
			if (progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}

			System.out.println(result);
			groups = (List<Map<String, String>>) result.get("groups");
			childs = (List<List<Map<String, String>>>) result.get("childs");
			publishProgress();
		}

		@Override
		protected void onProgressUpdate(Void... voids)
		{
			if (isCancelled())
			{
				return;
			}
			// 更新UI
			adapter.notifyDataSetChanged();
		}
	}
}
package cn.heu.hmp.util.introduction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.heu.hmp.util.introduction.bean.CampusView;
import cn.heu.hmp.util.introduction.bean.Organization;
import cn.heu.hmp.util.introduction.bean.OrganizationSub;
import cn.heu.hmp.util.introduction.bean.SchoolIntroduction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Data
{
	private String basedownloadurl = "http://125.223.113.81:8080/HMPS/";
	private String baseloadimageurl = basedownloadurl + "upload/";
	private String blankImageName = "NoPic2.jpg";
	private String blankTextPromit = "对不起，您访问的资源不存在";

	public String getloadimageurl()
	{
		return baseloadimageurl;
	}

	// 下载
	public String download(String url)
	{
		HttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		InputStream inputStream = null;

		String result = "";

		try
		{
			httpResponse = httpClient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				result += line;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				inputStream.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	// 获取CampusOrganizationActivity的数据
	public LinkedList<Organization> getData_organization()
	{
		String url = basedownloadurl
				+ "introductionAction_organizationJson.action";
		String result = this.download(url);
		Type type = new TypeToken<LinkedList<Organization>>()
		{
		}.getType();
		LinkedList<Organization> organizations = new Gson().fromJson(result,
				type);
		return organizations;
	}

	// 获取CampusOrganizationSub的数据
	public LinkedList<OrganizationSub> getData_organizationSub()
	{
		String url = basedownloadurl
				+ "introductionAction_organizationSubJson.action";
		String result = this.download(url);
		Type type = new TypeToken<LinkedList<OrganizationSub>>()
		{
		}.getType();
		LinkedList<OrganizationSub> organizationSubs = new Gson().fromJson(
				result, type);
		return organizationSubs;
	}

	// 获取CampusOrganization_subActivity的数据
	public LinkedList<OrganizationSub> getData_num(String num)
	{
		String url = basedownloadurl
				+ "introductionAction_querySingleField.action?organizationSub.num=";
		String resultUrl = url + num;
		String result = download(resultUrl);
		Type type = new TypeToken<LinkedList<OrganizationSub>>()
		{
		}.getType();
		LinkedList<OrganizationSub> organizationSubs = new Gson().fromJson(
				result, type);
		if (organizationSubs.get(0).getMyText().equals(""))
		{
			organizationSubs.get(0).setMyText(blankTextPromit);
		}
		if (organizationSubs.get(0).getImage().equals(""))
		{
			organizationSubs.get(0).setImage(blankImageName);
		}
		return organizationSubs;
	}

	// 获取CampusIntroductionActivity和CampusHistoryActivity的数据
	public LinkedList<SchoolIntroduction> getData_schoolIntroduction()
	{
		String url = basedownloadurl
				+ "introductionAction_schoolIntroductionJson.action";
		String result = download(url);
		Type type = new TypeToken<LinkedList<SchoolIntroduction>>()
		{
		}.getType();
		LinkedList<SchoolIntroduction> schoolIntroductions = new Gson()
				.fromJson(result, type);
		for (int i = 0; i < schoolIntroductions.size(); i++)
		{
			if (schoolIntroductions.get(i).getMyText().equals(""))
			{
				schoolIntroductions.get(i).setMyText(blankTextPromit);
			}
			if (schoolIntroductions.get(i).getImageName().equals(""))
			{
				schoolIntroductions.get(i).setImageName(blankImageName);
			}
		}
		return schoolIntroductions;
	}

	// 获取CampusViewActivity的图片
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public Object[] getData_campusView()
	{
		String downUrl = basedownloadurl
				+ "introductionAction_campusViewJson.action";
		String result = download(downUrl);
		Gson gson = new Gson();
		Type type = new TypeToken<LinkedList<CampusView>>()
		{
		}.getType();
		LinkedList<CampusView> campusViews = new Gson().fromJson(result, type);

		String[] str = null;
		List list = new ArrayList();
		for (int i = 0; i < campusViews.size(); i++)
		{
			if (campusViews != null)
			{
				if (campusViews.get(i).getImageName().equals(""))
				{
					campusViews.get(i).setImageName(blankImageName);
					String image_url = baseloadimageurl
							+ campusViews.get(i).getImageName();
					list.add(image_url);
				}
				else
				{
					String image_url = baseloadimageurl
							+ campusViews.get(i).getImageName();
					list.add(image_url);
				}
			}
		}
		return list.toArray();
	}

	// 获取CampusViewActivity的数据

	public Object[] getData_campusViewName()
	{
		String downUrl = basedownloadurl
				+ "introductionAction_campusViewJson.action";
		String result = download(downUrl);

		Gson gson = new Gson();
		Type type = new TypeToken<LinkedList<CampusView>>()
		{
		}.getType();
		LinkedList<CampusView> campusViewNames = new Gson().fromJson(result,
				type);
		List list = new ArrayList();
		for (int i = 0; i < campusViewNames.size(); i++)
		{
			if (campusViewNames != null)
			{
				String name = "";
				if (campusViewNames.get(i).getContent().equals(""))
				{
					campusViewNames.get(i).setContent(blankTextPromit);
					name = campusViewNames.get(i).getContent();
					list.add(name);
				}
				else
				{
					name = campusViewNames.get(i).getContent();
					list.add(name);
				}
			}
		}

		return list.toArray();
	}
}

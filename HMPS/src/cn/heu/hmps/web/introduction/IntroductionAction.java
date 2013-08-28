package cn.heu.hmps.web.introduction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.heu.hmps.core.web.BaseAction;
import cn.heu.hmps.entity.Introduction.CampusView;
import cn.heu.hmps.entity.Introduction.Organization;
import cn.heu.hmps.entity.Introduction.OrganizationSub;
import cn.heu.hmps.entity.Introduction.SchoolIntroduction;
import cn.heu.hmps.service.introduction.IIntroductionService;
import cn.heu.hmps.util.web.Struts2Utils;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@Scope("prototype")
@Component("introductionAction")
public class IntroductionAction extends BaseAction
{
	// --依赖注入--//
	private IIntroductionService introductionService;

	@Resource(name = "introductionService")
	public void setIntroductionService(IIntroductionService introductionService)
	{
		this.introductionService = introductionService;
	}

	public IIntroductionService getIntroductionService()
	{
		return introductionService;
	}

	private OrganizationSub organizationSub;

	public OrganizationSub getOrganizationSub()
	{
		return organizationSub;
	}

	public void setOrganizationSub(OrganizationSub organizationSub)
	{
		this.organizationSub = organizationSub;
	}

	

	// 学校简介,辉煌校史json
	public String schoolIntroductionJson()
	{
		List<SchoolIntroduction> list = this.introductionService
				.findAll_SchoolIntroduction();

		for (int i = 0; i < list.size(); i++)
		{
			String str = list.get(i).getImageName();
			if (!str.equals(""))
			{
				String newImage = str.substring(13);
				list.get(i).setImageName(newImage);
			}
		}

		Gson gson = new Gson();
		Struts2Utils.renderText(gson.toJson(list));
		return null;
	}

	// 院系介绍 一级菜单json
	public String organizationJson()
	{
		List<Organization> list = this.introductionService
				.findAll_Organization();

		Gson gson = new Gson();
		Struts2Utils.renderText(gson.toJson(list));
		return null;
	}

	// 院系介绍 二级菜单json
	public String organizationSubJson()
	{
		List<OrganizationSub> list = this.introductionService
				.findAll_OrganizationSub();
		for (int i = 0; i < list.size(); i++)
		{
			String str = list.get(i).getImage();
			if (!str.equals(""))
			{
				String newImage = str.substring(13);
				list.get(i).setImage(newImage);
				System.out.println(list.get(i).getImage());
			}
		}
		Gson gson = new Gson();
		Struts2Utils.renderText(gson.toJson(list));
		return null;
	}

	// 组织机构子页面 由索引sum查询对应对象
	public String querySingleField()
	{
		String num = organizationSub.getNum();
		List<OrganizationSub> list = this.introductionService.findByField(num);
		Gson gson = new Gson();
		Struts2Utils.renderText(gson.toJson(list));
		return null;
	}

	// 校园风光json
	public String campusViewJson()
	{
		List<CampusView> list = this.introductionService.findAll_CampusView();
		list.toString().replace("<p>", "");
		for (int i = 0; i < list.size(); i++)
		{
			String str = list.get(i).getImageName();
			if (!str.equals(""))
			{

				String newImage = str.substring(13);
				list.get(i).setImageName(newImage);
			}
		}
		Gson gson = new Gson();
		Struts2Utils.renderText(gson.toJson(list));
		return null;
	}
	
}

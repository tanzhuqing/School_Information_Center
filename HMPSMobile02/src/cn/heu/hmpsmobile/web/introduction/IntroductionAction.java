package cn.heu.hmpsmobile.web.introduction;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.heu.hmpsmobile.service.introduction.IIntroductionService;
import cn.heu.hmpsmobile.util.web.Struts2Utils;
import cn.heu.hmpsmobile.core.web.BaseAction;
import cn.heu.hmpsmobile.entity.Introduction.CampusView;
import cn.heu.hmpsmobile.entity.Introduction.Organization;
import cn.heu.hmpsmobile.entity.Introduction.OrganizationSub;
import cn.heu.hmpsmobile.entity.Introduction.SchoolIntroduction;

/**
 * User: Yuanyuan.L
 * Date: 12-06-19
 * Time: 14:47
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Component("introductionAction")
public class IntroductionAction extends BaseAction
{
	// 学校简介,辉煌校史json
	public String schoolIntroductionJson()
	{
		this.schIntroList = this.introductionService.findAll_SchoolIntroduction();
		 JSONArray jsonArray = new JSONArray();
		 for (SchoolIntroduction schIntro : schIntroList) {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("id", schIntro.getId());
	            jsonObject.put("text", schIntro.getMyText());
	            jsonObject.put("imageName", schIntro.getImageName());
	            jsonObject.put("flag", schIntro.getFlag());
	            jsonArray.add(jsonObject);
	        }
	        
	        System.out.println(jsonArray.toString());
	        Struts2Utils.renderText(jsonArray.toString());
	        return null;	 
	}

	// 院系介绍 一级菜单json
	public String organizationJson()
	{
		this.organList = this.introductionService.findAll_Organization();
		JSONArray jsonArray = new JSONArray();
        for (Organization organ : organList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", organ.getId());
            jsonObject.put("groupName", organ.getGroupName());
            jsonObject.put("level", organ.getWhichLevel());
            jsonArray.add(jsonObject);
        }
        
        System.out.println(jsonArray.toString());
        Struts2Utils.renderText(jsonArray.toString());
        return null;
	}

	// 院系介绍 二级菜单json
	public String organizationSubJson()
	{
		//this.organSubList = this.introductionService.findAll_OrganizationSub();
		this.organSubList = this.introductionService.getOrganizationSubByTid(this.tid);
		JSONArray jsonArray = new JSONArray();
        for (OrganizationSub organSub : organSubList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", organSub.getId());
            jsonObject.put("tid", organSub.getTid());
            jsonObject.put("child", organSub.getChild());
            jsonObject.put("text", organSub.getMyText());
            jsonObject.put("image", organSub.getImage());
            jsonObject.put("num", organSub.getNum());
            jsonArray.add(jsonObject);
        }
        
        System.out.println(jsonArray.toString());
        Struts2Utils.renderText(jsonArray.toString());
        return null;		
	}

	// 组织机构子页面 由索引id查询对应对象
	public String listOrganizationSubDetailJSON()
	{
		this.organSub = this.introductionService.findByID(this.id);
		if (organSub != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", organSub.getId());
            jsonObject.put("tid", organSub.getTid());
            jsonObject.put("child", organSub.getChild());
            jsonObject.put("text", organSub.getMyText());
            jsonObject.put("image", organSub.getImage());
            jsonObject.put("num", organSub.getNum());
            
            System.out.println(jsonObject.toString());
            Struts2Utils.renderText(jsonObject.toString());
        } else {
            Struts2Utils.renderText("");
        }
        return null;	 
	}

	// 校园风光json
	public String campusViewJson()
	{
		this.camViewList = this.introductionService.findAll_CampusView();
		JSONArray jsonArray = new JSONArray();
        for (CampusView camView : camViewList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", camView.getId());
            jsonObject.put("imageName", camView.getImageName());
            jsonObject.put("flag", camView.getFlag());
            jsonObject.put("content", camView.getContent());
            jsonArray.add(jsonObject);
        }
        
        System.out.println(jsonArray.toString());
        Struts2Utils.renderText(jsonArray.toString());
        return null;
	}
	private List<SchoolIntroduction> schIntroList;
	private List<Organization> organList;
	private List<OrganizationSub> organSubList;
	private OrganizationSub organSub;
	private List<CampusView> camViewList; 
	private int id;
	private String tid;
			
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
	public List<SchoolIntroduction> getSchIntroList() {
		return schIntroList;
	}

	public void setSchIntroList(List<SchoolIntroduction> schIntroList) {
		this.schIntroList = schIntroList;
	}

	public List<Organization> getOrganList() {
		return organList;
	}

	public void setOrganList(List<Organization> organList) {
		this.organList = organList;
	}

	public List<OrganizationSub> getOrganSubList() {
		return organSubList;
	}

	public void setOrganSubList(List<OrganizationSub> organSubList) {
		this.organSubList = organSubList;
	}

	public OrganizationSub getOrganSub() {
		return organSub;
	}

	public void setOrganSub(OrganizationSub organSub) {
		this.organSub = organSub;
	}

	public List<CampusView> getCamViewList() {
		return camViewList;
	}

	public void setCamViewList(List<CampusView> camViewList) {
		this.camViewList = camViewList;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
}

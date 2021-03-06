package cn.heu.hmpsmobile.service.introduction;

import java.util.List;

import cn.heu.hmpsmobile.entity.Introduction.CampusView;
import cn.heu.hmpsmobile.entity.Introduction.Organization;
import cn.heu.hmpsmobile.entity.Introduction.OrganizationSub;
import cn.heu.hmpsmobile.entity.Introduction.SchoolIntroduction;


public interface IIntroductionService
{
	//学校简介 
	public List<SchoolIntroduction> findAll_SchoolIntroduction();
	//组织机构一级字段 
	public List<Organization> findAll_Organization();
	//组织机构二级字段
	//public List<OrganizationSub> findAll_OrganizationSub();
	public List<OrganizationSub> getOrganizationSubByTid(String tid);
	//校园风光
	public List<CampusView> findAll_CampusView();
	//组织机构子页面文本图片对象
	//public List<OrganizationSub> findByField(String num);
	public OrganizationSub findByID(int id);
}

package cn.heu.hmps.service.introduction;

import java.util.List;

import cn.heu.hmps.entity.Introduction.CampusView;
import cn.heu.hmps.entity.Introduction.Organization;
import cn.heu.hmps.entity.Introduction.OrganizationSub;
import cn.heu.hmps.entity.Introduction.SchoolIntroduction;


public interface IIntroductionService
{
	//学校简介 
	public List<SchoolIntroduction> findAll_SchoolIntroduction();
	//组织机构一级字段 
	public List<Organization> findAll_Organization();
	//组织机构二级字段
	public List<OrganizationSub> findAll_OrganizationSub();
	//校园风光
	public List<CampusView> findAll_CampusView();
	//组织机构子页面文本图片对象
	public List<OrganizationSub> findByField(String num);
}

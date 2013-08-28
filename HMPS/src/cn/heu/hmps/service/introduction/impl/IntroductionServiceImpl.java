package cn.heu.hmps.service.introduction.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.heu.hmps.dao.introduction.CampusviewDao;
import cn.heu.hmps.dao.introduction.OrganizationDao;
import cn.heu.hmps.dao.introduction.OrganizationSubDao;
import cn.heu.hmps.dao.introduction.SchoolIntroductionDao;
import cn.heu.hmps.entity.Introduction.CampusView;
import cn.heu.hmps.entity.Introduction.Organization;
import cn.heu.hmps.entity.Introduction.OrganizationSub;
import cn.heu.hmps.entity.Introduction.SchoolIntroduction;
import cn.heu.hmps.service.introduction.IIntroductionService;

@Service("introductionService")
public class IntroductionServiceImpl implements IIntroductionService
{
	//依赖注入
	private SchoolIntroductionDao schoolIntroductionDao;
	
	private OrganizationDao organizationDao;
	
	private OrganizationSubDao organizationSubDao;
	
	private CampusviewDao campusviewDao;
	
	@Resource(name = "schoolIntroductionDao")
	public void setSchoolIntroductionDao(SchoolIntroductionDao schoolIntroductionDao)
	{
		this.schoolIntroductionDao = schoolIntroductionDao;
	}
	@Resource(name = "organizationDao")
	public void setOrganizationDao(OrganizationDao organizationDao)
	{
		this.organizationDao = organizationDao;
	}
	@Resource(name = "organizationSubDao")
	public void setOrganizationSubDao(OrganizationSubDao organizationSubDao)
	{
		this.organizationSubDao = organizationSubDao;
	}
	@Resource(name = "campusviewDao")
	public void setCampusviewDao(CampusviewDao campusviewDao)
	{
		this.campusviewDao = campusviewDao;
	}
	//heu_schoolintroduction 查询所有列表
	public List<SchoolIntroduction> findAll_SchoolIntroduction()
	{
		return this.schoolIntroductionDao.getAll("id", true);
	}
	//heu_organization 查询所有列表
	public List<Organization> findAll_Organization()
	{
		return this.organizationDao.getAll("id", true);
	}
	//heu_organization_sub 查询所有列表
	public List<OrganizationSub> findAll_OrganizationSub()
	{
		return this.organizationSubDao.getAll("id", true);
	}
	//heu_campusview 查询所有列表
	public List<CampusView> findAll_CampusView()
	{
		return this.campusviewDao.getAll("id", true);
	}
	
	public List<OrganizationSub> findByField(String num)
	{
		String hql = "from OrganizationSub as o where o.num=?";
		List<OrganizationSub> list = this.organizationSubDao.find(hql,num);
		return list;
	}
}

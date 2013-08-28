package cn.heu.hmps.service.department.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.heu.hmps.dao.department.TelephoneDao;
import cn.heu.hmps.entity.department.Telephone;
import cn.heu.hmps.service.department.ITelephoneService;
@Service("telephoneService")
public class TelephoneServiceImpl implements ITelephoneService
{
	
    //--依赖注入--//
    private TelephoneDao telephoneDao;

    @Resource(name = "telephoneDao")
	public void setTelephoneDao(TelephoneDao telephoneDao)
	{
		this.telephoneDao = telephoneDao;
	}

	public List<Telephone> findAll()
	{
		return this.telephoneDao.getAll("id",true);
	}

	public List<String> findByFieldWithDepartmentType(String str)
	{
		String hql = "SELECT DISTINCT t.mainDepartment FROM Telephone t WHERE t.departmentCode = ?";
		
		List<String> list = this.telephoneDao.find(hql,str);
		return list;
	}

	public List<Telephone> findByFieldWithMainDepartment(String str)
	{
		String hql = "from Telephone t where t.mainDepartment = ?";
		List<Telephone> list = this.telephoneDao.find(hql,str);
		return list;
	}
	
	public List<Telephone> findByDepartmentType(String str)
	{
		String hql = "SELECT DISTINCT t.MainDepartment FROM Telephone t WHERE t.DepartmentCode = ?";
		return this.telephoneDao.find(hql, str);
	}

	public List<Telephone> findSingleObjectBySubDepartment(String str)
	{
		String hql = "FROM Telephone t WHERE t.subDepartment LIKE '%" + str + "%'";
		
		return this.telephoneDao.find(hql);
	}
	
}

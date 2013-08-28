package cn.heu.hmps.service.department;

import java.util.List;

import cn.heu.hmps.entity.department.Telephone;

public interface ITelephoneService
{
	public List<Telephone> findAll();
	
	public List<String> findByFieldWithDepartmentType(String str);
	
	public List<Telephone> findByFieldWithMainDepartment(String str);
	
	public List<Telephone> findByDepartmentType(String str);
	
	public List<Telephone> findSingleObjectBySubDepartment(String str);
}

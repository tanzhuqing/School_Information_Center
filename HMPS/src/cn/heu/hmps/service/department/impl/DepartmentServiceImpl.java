package cn.heu.hmps.service.department.impl;

import cn.heu.hmps.dao.department.DepartmentDao;
import cn.heu.hmps.entity.department.Department;
import cn.heu.hmps.service.department.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q Date: 12-3-6 Time: 上午11:22
 */
@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService
{

	public List<Department> getMainDepartment(String deptTypeName)
	{
		String hql = "FROM Department d where d.deptTypeName = ? and d.deptLevel = 1";
		return this.departmentDao.find(hql, deptTypeName);
	}

	public List<Department> getSubDepartment(String mainDeptCode)
	{
		String hql = "FROM Department d where d.deptParentCode = ? ORDER BY d.deptName";
		return this.departmentDao.find(hql, mainDeptCode);
	}

	// --依赖注入--//
	private DepartmentDao departmentDao;

	@Resource(name = "departmentDao")
	public void setDepartmentDao(DepartmentDao departmentDao)
	{
		this.departmentDao = departmentDao;
	}
}

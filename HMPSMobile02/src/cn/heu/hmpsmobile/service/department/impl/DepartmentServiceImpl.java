package cn.heu.hmpsmobile.service.department.impl;

import cn.heu.hmpsmobile.dao.department.DepartmentDao;
import cn.heu.hmpsmobile.entity.department.Department;
import cn.heu.hmpsmobile.service.department.IDepartmentService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-3-6
 * Time: 上午11:22
 */
@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService{

    public List<Department> getMainDepartment(String deptTypeName) {
    	/*List<Department> departments = departmentDao.getAll();
    	for(Department department : departments)
    	{
    		if(String.valueOf(department.getCode()).equals(""))
    		{
    			department.setCode(0);
    		}
    			
    	}*/
        String hql = "FROM Department d where d.deptTypeName = ? and d.deptLevel = 1 ORDER BY d.code";
        return this.departmentDao.find(hql, deptTypeName);
    }
    
    public List<Department> getSubDepartment(String mainDeptCode){
        String hql = "FROM Department d where d.deptParentCode = ? and d.deptLevel = 2 ORDER BY d.deptName";
        return this.departmentDao.find(hql, mainDeptCode);
    }
     
    public Department getDepartmentByCode(String deptCode){
    	String hql = "FROM Department d where d.deptCode = ?";
    	return this.departmentDao.findUnique(hql, deptCode);
    }
    //--依赖注入--//
    private DepartmentDao departmentDao;

    @Resource(name = "departmentDao")
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}

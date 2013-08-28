package cn.heu.hmpsmobile.service.department;

import cn.heu.hmpsmobile.entity.department.Department;

import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-3-6
 * Time: 上午11:22
 */
public interface IDepartmentService {
    
    public List<Department> getMainDepartment(String deptTypeName);
    
    //public Department getSubDepartment(String mainDeptcode);
    public List<Department> getSubDepartment(String mainDeptCode);
    
      
    public Department getDepartmentByCode(String deptCode);
}

package cn.heu.hmps.service.department;

import cn.heu.hmps.entity.department.Department;

import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-3-6
 * Time: 上午11:22
 */
public interface IDepartmentService {
    
    public List<Department> getMainDepartment(String deptTypeName);

    public List<Department> getSubDepartment(String mainDeptCode);
}

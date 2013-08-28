package cn.heu.hmpsmobile.dao.department;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.department.Department;

import org.springframework.stereotype.Repository;

/**
 * User: Yingtao.Q
 * Date: 12-3-6
 * Time: 上午11:21
 */
@Repository("departmentDao")
public class DepartmentDao extends HibernateDao<Department, Integer> {
	
}

package cn.heu.hmps.dao.introduction;

import org.springframework.stereotype.Repository;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.Introduction.Organization;
@Repository("organizationDao")
public class OrganizationDao extends HibernateDao<Organization, Integer>
{
	
}

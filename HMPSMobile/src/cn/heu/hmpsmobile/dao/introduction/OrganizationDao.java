package cn.heu.hmpsmobile.dao.introduction;

import org.springframework.stereotype.Repository;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.Introduction.Organization;
@Repository("organizationDao")
public class OrganizationDao extends HibernateDao<Organization, Integer>
{
	
}

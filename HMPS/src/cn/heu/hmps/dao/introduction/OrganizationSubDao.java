package cn.heu.hmps.dao.introduction;

import org.springframework.stereotype.Repository;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.Introduction.OrganizationSub;
@Repository("organizationSubDao")
public class OrganizationSubDao extends HibernateDao<OrganizationSub, Integer>
{

}

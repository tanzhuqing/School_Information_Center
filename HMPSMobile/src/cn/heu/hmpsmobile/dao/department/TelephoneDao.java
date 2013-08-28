package cn.heu.hmpsmobile.dao.department;

import org.springframework.stereotype.Repository;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.department.Telephone;

@Repository("telephoneDao")
public class TelephoneDao extends HibernateDao<Telephone, Integer>
{

}

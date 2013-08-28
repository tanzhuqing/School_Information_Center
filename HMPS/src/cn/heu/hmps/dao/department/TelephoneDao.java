package cn.heu.hmps.dao.department;

import org.springframework.stereotype.Repository;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.department.Telephone;

@Repository("telephoneDao")
public class TelephoneDao extends HibernateDao<Telephone, Integer>
{

}

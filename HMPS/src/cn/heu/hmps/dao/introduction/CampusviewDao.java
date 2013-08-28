package cn.heu.hmps.dao.introduction;

import org.springframework.stereotype.Repository;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.Introduction.CampusView;
@Repository("campusviewDao")
public class CampusviewDao extends HibernateDao<CampusView, Integer>
{

}

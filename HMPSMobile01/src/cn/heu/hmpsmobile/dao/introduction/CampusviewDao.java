package cn.heu.hmpsmobile.dao.introduction;

import org.springframework.stereotype.Repository;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.Introduction.CampusView;
@Repository("campusviewDao")
public class CampusviewDao extends HibernateDao<CampusView, Integer>
{

}

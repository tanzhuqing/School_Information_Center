package cn.heu.hmpsmobile.dao.introduction;

import org.springframework.stereotype.Repository;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.Introduction.SchoolIntroduction;
@Repository("schoolIntroductionDao")
public class SchoolIntroductionDao extends HibernateDao<SchoolIntroduction,Integer>
{

}

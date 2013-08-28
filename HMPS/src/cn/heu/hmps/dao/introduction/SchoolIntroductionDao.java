package cn.heu.hmps.dao.introduction;

import org.springframework.stereotype.Repository;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.Introduction.SchoolIntroduction;
@Repository("schoolIntroductionDao")
public class SchoolIntroductionDao extends HibernateDao<SchoolIntroduction,Integer>
{

}

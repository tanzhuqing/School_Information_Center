package cn.heu.hmpsmobile.dao.info;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.info.Info;

import org.springframework.stereotype.Repository;

/**
 * User: Yingtao.Q
 * Date: 12-5-15
 */
@Repository("infoDao")
public class InfoDao extends HibernateDao<Info, Integer> {
}

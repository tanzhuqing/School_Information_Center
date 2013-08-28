package cn.heu.hmps.dao.info;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.info.Info;
import org.springframework.stereotype.Repository;

/**
 * User: Yingtao.Q
 * Date: 12-5-15
 */
@Repository("infoDao")
public class InfoDao extends HibernateDao<Info, Integer> {
}

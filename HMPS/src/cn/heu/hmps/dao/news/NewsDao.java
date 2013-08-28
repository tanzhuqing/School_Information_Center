package cn.heu.hmps.dao.news;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.news.News;
import org.springframework.stereotype.Repository;

/**
 * User: Yingtao.Q
 * Date: 11-12-24
 * Time: 下午8:25
 */
@Repository("newsDao")
public class NewsDao extends HibernateDao<News, Integer>{
}

package cn.heu.hmpsmobile.dao.news;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.news.NewsImage;

import org.springframework.stereotype.Repository;

/**
 * User: Yuanyuan.L
 * Date: 12-05-27
 * Time: ����11:15
 */

@Repository("newsImageDao")
public class NewsImageDao extends HibernateDao<NewsImage, Integer>{
}

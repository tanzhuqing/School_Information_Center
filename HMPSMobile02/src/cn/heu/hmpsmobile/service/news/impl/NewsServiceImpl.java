package cn.heu.hmpsmobile.service.news.impl;

import cn.heu.hmpsmobile.service.news.INewsService;
import cn.heu.hmpsmobile.dao.news.NewsDao;
import cn.heu.hmpsmobile.entity.info.Info;
import cn.heu.hmpsmobile.entity.news.News;
import cn.heu.hmpsmobile.entity.news.NewsImage;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q Date: 11-12-24 Time: 下午8:27
 */
@Service("newsService")
public class NewsServiceImpl implements INewsService
{

	// --依赖注入--//
	private NewsDao newsDao;

	@Resource(name = "newsDao")
	public void setNewsDao(NewsDao newsDao)
	{
		this.newsDao = newsDao;
	}

	public List<News> getAllNews()
	{
		Query query = this.newsDao.createQuery("from News as n order by n.id desc");
		query.setFirstResult(0);
		query.setMaxResults(8);
		return (List<News>) query.list();
	}

	public News getNewsById(int id)
	{
		return this.newsDao.get(id);
	}

	public void saveNews(News news)
	{
		this.newsDao.save(news);
	}

	public void saveNewsImage(NewsImage newsImage)
	{
		this.newsDao.getSession().save(newsImage);
	}
}

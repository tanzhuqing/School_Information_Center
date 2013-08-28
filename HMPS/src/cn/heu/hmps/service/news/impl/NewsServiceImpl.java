package cn.heu.hmps.service.news.impl;

import cn.heu.hmps.dao.news.NewsDao;
import cn.heu.hmps.entity.info.Info;
import cn.heu.hmps.entity.news.News;
import cn.heu.hmps.entity.news.NewsImage;
import cn.heu.hmps.service.news.INewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 11-12-24
 * Time: 下午8:27
 */
@Service("newsService")
public class NewsServiceImpl implements INewsService{

    //--依赖注入--//
    private NewsDao newsDao;

    @Resource(name = "newsDao")
    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public List<News> getAllNews() {
        return this.newsDao.getAll();
    }

    public News getNewsById(int id) {
        return this.newsDao.get(id);
    }
    public void saveNews(News news) {
        this.newsDao.save(news);
    }

    public void saveNewsImage(NewsImage newsImage) {
        this.newsDao.getSession().save(newsImage);
    }
}

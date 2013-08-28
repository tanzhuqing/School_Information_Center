package cn.heu.hmps.service.news;

import cn.heu.hmps.entity.news.News;
import cn.heu.hmps.entity.news.NewsImage;

import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 11-12-24
 * Time: 下午8:27
 */
public interface INewsService {

    public List<News> getAllNews();
    
    public News getNewsById(int id);

    public void saveNews(News news);

    public void saveNewsImage(NewsImage newsImage);
}

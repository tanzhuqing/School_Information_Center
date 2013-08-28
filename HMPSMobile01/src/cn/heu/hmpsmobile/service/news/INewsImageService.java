package cn.heu.hmpsmobile.service.news;

import java.util.List;

import cn.heu.hmpsmobile.entity.news.NewsImage;

/**
 * User: Yuanyuan.L
 * Date: 12-05-27
 * Time: ����11:15
 */


public interface INewsImageService {

    public NewsImage getNewsId(int newsid);

    public List<NewsImage> getNewsImageById(int newsid);
}

package cn.heu.hmpsmobile.service.news.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.heu.hmpsmobile.service.news.INewsImageService;
import cn.heu.hmpsmobile.dao.news.NewsImageDao;
import cn.heu.hmpsmobile.entity.news.NewsImage;

/**
 * User: Yuanyuan.L
 * Date: 12-05-27
 * Time: 15:45
 */

@Service("newsImageService")
public class NewsImageServiceImpl implements INewsImageService {
	
	//--����ע��--//
	private NewsImageDao newsImagedao;
	
	@Resource(name = "newsImageDao")
    public void setNewsImageDao(NewsImageDao newsImagedao) {
        this.newsImagedao = newsImagedao;
    }
	public NewsImage getNewsId(int newsid) {
		// TODO Auto-generated method stub
		return this.newsImagedao.get(newsid);
	}
	public List<NewsImage> getNewsImageById(int newsid) {
		// TODO Auto-generated method stub
		
		String hql = "FROM NewsImage h WHERE h.newsid = ? ";
		return this.newsImagedao.find(hql,newsid);
	}
	

}

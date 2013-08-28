package cn.heu.hmpsmobile.web.news;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.heu.hmpsmobile.core.web.BaseAction;
import cn.heu.hmpsmobile.entity.news.News;
import cn.heu.hmpsmobile.entity.news.NewsImage;
import cn.heu.hmpsmobile.service.news.INewsImageService;
import cn.heu.hmpsmobile.service.news.INewsService;
import cn.heu.hmpsmobile.util.web.Struts2Utils;

/**
 * User: Yuanyuan.L
 * Date: 12-05-27
 * Time: 17:47
 */
@Scope("prototype")
@Component("newsAction")
public class NewsAction extends BaseAction {

	
    public String listNewsJson() {
        this.newsList = this.newsService.getAllNews();
        JSONArray jsonArray = new JSONArray();
        for (News news : newsList) 
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", news.getNewsId());
            jsonObject.put("title", news.getNewsTitle());
            jsonObject.put("smallImage", news.getNewsPicUrl());
            jsonObject.put("content", news.getNewsContent());
            jsonObject.put("publishDate", news.getNewsDate());
            jsonArray.add(jsonObject);
            
            
        }
        
        
        System.out.println(jsonArray.toString());
        Struts2Utils.renderText(jsonArray.toString());
        return null;
    }
    public String listNewsDetailJSON() {
        this.news = this.newsService.getNewsById(this.id);
        String string = news.getNewsContent();
        string = string.replaceAll("HeuNewsPic/", "http://news.color5164.com/HeuNewsPic/");
        System.out.println(string);
        if (news != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", news.getNewsId());
            jsonObject.put("title", news.getNewsTitle());
            jsonObject.put("author", news.getNewsAuthor());
            jsonObject.put("content", string);
            jsonObject.put("publishDate", news.getNewsDate());       
           // this.newsImages = this.newsImageService.getNewsImageById(this.id);
           // JSONObject image = new JSONObject();
           // for(NewsImage newsImage:newsImages){	
           // image.put("id", newsImage.getId());
           // image.put("name", newsImage.getName());
          //  }
          //  jsonObject.put("image", image);
            System.out.println(jsonObject.toString());
            Struts2Utils.renderText(jsonObject.toString());
        } else {
            Struts2Utils.renderText("");
        }
        return null;
    }

    //--依赖注入--//
    private INewsService newsService;
    private INewsImageService newsImageService;

    @Resource(name = "newsService")
    public void setNewsService(INewsService newsService) {
        this.newsService = newsService;
    }
    @Resource(name = "newsImageService")
    public void setNewsImageService(INewsImageService newsImageService) {
        this.newsImageService = newsImageService;
    }

    //--页面属性--//
    private int id;
    private List<News> newsList;
    private News news;
    private List<NewsImage> newsImages;

    //--Set.Get--//

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<NewsImage> getNewsImageList(){
    	return newsImages;
    }
    public void setNewsImageList(List<NewsImage> newsImages){
    	this.newsImages =newsImages;
    }
    
    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

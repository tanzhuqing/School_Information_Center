package cn.heu.hmpsmobile.entity.news;

import javax.persistence.*;
import java.io.Serializable;
//import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 11-12-24
 * Time: 下午8:21
 */
@Entity
@Table(name = "Heu_News")
public class News implements Serializable {
    
    private int newsId;
    
    private String newsTitle;
    
    private String newsAuthor;
    
    private String newsContent;
    
    private String newsDate;   
    
    private String newsPicUrl = ""; //新闻列表显示的图片

  //  private List<NewsImage> newsImages;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

   // @OneToMany()
   // @JoinColumn(name = "NewsId")
   // public List<NewsImage> getNewsImages() {
   //     return newsImages;
   // }

   // public void setNewsImages(List<NewsImage> newsImages) {
   //     this.newsImages = newsImages;
   // }

    public String getNewsPicUrl() {
        return newsPicUrl;
    }

    public void setNewsPicUrl(String newsPicUrl) {
        this.newsPicUrl = newsPicUrl;
    }
}

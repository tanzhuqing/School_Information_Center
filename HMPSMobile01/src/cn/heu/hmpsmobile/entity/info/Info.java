package cn.heu.hmpsmobile.entity.info;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Yingtao.Q
 * Date: 12-5-15
 */
@Entity
@Table(name = "Heu_Info")
public class Info {

    private int id;

    private String title;

    private String content;

    private String type;

    private Date createTime; //创建时间

    private String publishDate;  //发布日期

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}

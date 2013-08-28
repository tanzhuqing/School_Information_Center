package cn.heu.hmps.entity.news;

import javax.persistence.*;

/**
 * User: Yingtao.Q
 * Date: 12-2-28
 * Time: 上午9:37
 */
@Entity
@Table(name = "Heu_NewsImg")
public class NewsImage {

    private int id;

    private String name;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

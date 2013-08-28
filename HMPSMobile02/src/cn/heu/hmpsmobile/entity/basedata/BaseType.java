package cn.heu.hmpsmobile.entity.basedata;

import javax.persistence.*;

/**
 * User: Yingtao.Q
 * Date: 12-3-13
 * Time: 上午10:59
 */
@Entity
@Table(name = "Heu_Department")
public class BaseType {
    
    private int id;
    
    private String name;
    
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package cn.heu.hmpsmobile.entity.system;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Yingtao.Q
 * Date: 11-8-9
 * Time: 上午10:48
 */
@Entity
@Table(name = "Heu_User")
@SuppressWarnings("serial")
public class User implements Serializable{

    private int id;

    private String userName;

    private String loginName;

    private String password;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

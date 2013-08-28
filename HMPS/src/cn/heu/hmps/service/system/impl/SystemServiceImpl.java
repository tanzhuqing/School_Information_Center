package cn.heu.hmps.service.system.impl;

import cn.heu.hmps.dao.system.UserDao;
import cn.heu.hmps.entity.system.User;
import cn.heu.hmps.service.system.ISystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 11-12-20
 * Time: 下午4:57
 */
@Service("systemService")
public class SystemServiceImpl implements ISystemService {
    public User checkUser(String loginName, String password) {
        String hql = "from User u where u.loginName = ? and u.password = ?";
        List<User> users = this.userDao.find(hql, loginName, password);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    //--注入DAO Begin
    private UserDao userDao;

    @Resource(name = "userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

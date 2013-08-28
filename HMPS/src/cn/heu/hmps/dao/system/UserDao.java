package cn.heu.hmps.dao.system;

import cn.heu.hmps.core.dao.HibernateDao;
import cn.heu.hmps.entity.system.User;
import org.springframework.stereotype.Repository;

/**
 * User: Yingtao.Q
 * Date: 11-8-9
 * Time: 下午4:24
 */

@Repository(value = "userDao")
public class UserDao extends HibernateDao<User,Integer> {
}

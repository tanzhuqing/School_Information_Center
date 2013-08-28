package cn.heu.hmpsmobile.dao.system;

import cn.heu.hmpsmobile.core.dao.HibernateDao;
import cn.heu.hmpsmobile.entity.system.User;

import org.springframework.stereotype.Repository;

/**
 * User: Yingtao.Q
 * Date: 11-8-9
 * Time: 下午4:24
 */

@Repository(value = "userDao")
public class UserDao extends HibernateDao<User,Integer> {
}

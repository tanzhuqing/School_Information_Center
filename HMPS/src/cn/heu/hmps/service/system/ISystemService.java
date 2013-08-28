package cn.heu.hmps.service.system;

import cn.heu.hmps.entity.system.User;

/**
 * User: Yingtao.Q
 * Date: 11-8-9
 * Time: 下午4:15
 */
public interface ISystemService {

    public User checkUser(String loginName, String password);



}

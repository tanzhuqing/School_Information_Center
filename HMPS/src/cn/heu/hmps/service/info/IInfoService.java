package cn.heu.hmps.service.info;

import cn.heu.hmps.entity.info.Info;

import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-5-15
 */
public interface IInfoService {

    public Info getInfoById(int id);

    public List<Info> getInfoByType(String type);
}

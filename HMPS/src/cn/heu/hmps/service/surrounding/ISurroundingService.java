package cn.heu.hmps.service.surrounding;

import cn.heu.hmps.entity.surrounding.Surrounding;

import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-3-13
 * Time: 上午11:01
 */
public interface ISurroundingService {

    public List<Surrounding> getSurroundingByType(String type);

    public Surrounding getSurroundingById(int id);
}

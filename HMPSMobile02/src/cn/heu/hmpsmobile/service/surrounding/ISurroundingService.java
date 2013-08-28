package cn.heu.hmpsmobile.service.surrounding;

import cn.heu.hmpsmobile.entity.surrounding.Surrounding;

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

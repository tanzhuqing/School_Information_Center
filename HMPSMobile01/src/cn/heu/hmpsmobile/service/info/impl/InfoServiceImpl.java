package cn.heu.hmpsmobile.service.info.impl;

import cn.heu.hmpsmobile.service.info.IInfoService;
import cn.heu.hmpsmobile.dao.info.InfoDao;
import cn.heu.hmpsmobile.entity.info.Info;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-5-15
 */
@Service("infoService")
public class InfoServiceImpl implements IInfoService {

    private InfoDao infoDao;

    @Resource(name = "infoDao")
    public void setInfoDao(InfoDao infoDao) {
        this.infoDao = infoDao;
    }


    public List<Info> getInfoByType(String type) {
        String hql = "FROM Info i WHERE i.type = ? order by i.publishDate desc ";
        return this.infoDao.find(hql, type);
    }

    public Info getInfoById(int id) {
        return this.infoDao.get(id);
    }
}

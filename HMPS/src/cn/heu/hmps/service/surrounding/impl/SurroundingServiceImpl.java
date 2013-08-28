package cn.heu.hmps.service.surrounding.impl;

import cn.heu.hmps.dao.surrounding.SurroundingDao;
import cn.heu.hmps.entity.surrounding.Surrounding;
import cn.heu.hmps.service.surrounding.ISurroundingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-3-13
 * Time: 上午11:02
 */
@Service("surroundingService")
public class SurroundingServiceImpl implements ISurroundingService{
    
    private SurroundingDao surroundingDao;

    @Resource(name = "surroundingDao")
    public void setSurroundingDao(SurroundingDao surroundingDao) {
        this.surroundingDao = surroundingDao;
    }
    
    public List<Surrounding> getSurroundingByType(String type){
        String hql = "FROM Surrounding s WHERE s.type = ? ";
        return this.surroundingDao.find(hql, type);
    }
    
    public Surrounding getSurroundingById(int id){
        return this.surroundingDao.get(id);
    }
    
}

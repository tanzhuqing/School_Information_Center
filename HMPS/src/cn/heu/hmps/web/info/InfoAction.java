package cn.heu.hmps.web.info;

import cn.heu.hmps.entity.info.Info;
import cn.heu.hmps.service.info.IInfoService;
import cn.heu.hmps.util.web.Struts2Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-5-15
 */
@Scope("prototype")
@Component("infoAction")
public class InfoAction {

    //Todo NULL 验证
    public String listInfoJSON() {
        this.infoList = this.infoService.getInfoByType(this.infoType);
        JSONArray jsonArray = new JSONArray();
        for (Info info : infoList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", info.getId());
            jsonObject.put("title", info.getTitle());
            jsonObject.put("content", info.getContent());
            jsonObject.put("publishDate", info.getPublishDate());
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray.toString());
        Struts2Utils.renderText(jsonArray.toString());
        return null;
    }

    public String listInfoDetailJSON() {
        this.info = this.infoService.getInfoById(this.id);
        if (info != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", info.getId());
            jsonObject.put("title", info.getTitle());
            jsonObject.put("content", info.getContent());
            jsonObject.put("publishDate", info.getPublishDate());
            Struts2Utils.renderText(jsonObject.toString());
        } else {
            Struts2Utils.renderText("");
        }
        return null;
    }

    private IInfoService infoService;

    @Resource(name = "infoService")
    public void setInfoService(IInfoService infoService) {
        this.infoService = infoService;
    }

    private List<Info> infoList;

    private Info info;

    private String infoType;    //讲座类型

    private int id; //讲座ID

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

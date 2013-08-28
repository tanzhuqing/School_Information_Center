package cn.heu.hmpsmobile.web.surrounding;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.heu.hmpsmobile.core.web.BaseAction;
import cn.heu.hmpsmobile.entity.surrounding.Surrounding;
import cn.heu.hmpsmobile.service.surrounding.ISurroundingService;
import cn.heu.hmpsmobile.util.web.Struts2Utils;

/**
 * User: Yingtao.Q Date: 12-3-13 Time: 上午11:05
 */
@Scope("prototype")
@Component("surroundingAction")
public class SurroundingAction extends BaseAction
{
	private static final long serialVersionUID = -2294251053123651780L;
	private String blankImageUrl = "http://125.223.113.81:8080/HMPSMobile/HeuNewsPic/NoPic2.jpg";

	public String listSurroundingJSON()
	{
		this.surroundingList = this.surroundingService.getSurroundingByType(type);
		JSONArray jSONArray = new JSONArray();
		for (Surrounding surrounding : surroundingList)
		{
			JSONObject jSONObject = new JSONObject();
			jSONObject.put("id", surrounding.getId());
			jSONObject.put("name", surrounding.getName());
			if (surrounding.getImageName().equals(""))
			{
				surrounding.setImageName(blankImageUrl);
			}
			jSONObject.put("imageNameURL", surrounding.getImageName());
			jSONArray.add(jSONObject);
		}
		System.out.println(jSONArray.toString());
		Struts2Utils.renderText(jSONArray.toString());

		return null;
	}

	public String listSurroundingDetailJSON()
	{
		surrounding = this.surroundingService.getSurroundingById(id);
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("content", surrounding.getContent());
		jSONObject.put("name", surrounding.getName());
		jSONObject.put("imageName", surrounding.getImageName().substring(surrounding.getImageName().lastIndexOf("/") + 1));
		jSONObject.put("address", surrounding.getAddress());
		jSONObject.put("tel", surrounding.getTel());
		jSONObject.put("latitude", surrounding.getLatitude());
		jSONObject.put("longitude", surrounding.getLongitude());
		if (surrounding.getWebsite().equals(""))
		{
			surrounding.setWebsite("http://www.google.com.cn");
		}
		jSONObject.put("website", surrounding.getWebsite());
		System.out.println(jSONObject.toString());
		Struts2Utils.renderText(jSONObject.toString());
		return null;
	}

	private ISurroundingService surroundingService;

	@Resource(name = "surroundingService")
	public void setSurroundingService(ISurroundingService surroundingService)
	{
		this.surroundingService = surroundingService;
	}

	// --页面属性--//
	private int id;
	private String type; // 周边类型：01--美食、02--娱乐、03--购物
	private String mainDeptName; // 一级部门名称
	private String mainDeptCode; // 一级部门代码
	private Surrounding surrounding;
	private List<Surrounding> surroundingList;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public List<Surrounding> getSurroundingList()
	{
		return surroundingList;
	}

	public void setSurroundingList(List<Surrounding> surroundingList)
	{
		this.surroundingList = surroundingList;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Surrounding getSurrounding()
	{
		return surrounding;
	}

	public void setSurrounding(Surrounding surrounding)
	{
		this.surrounding = surrounding;
	}
}

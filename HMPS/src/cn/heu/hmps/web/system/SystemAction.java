package cn.heu.hmps.web.system;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.heu.hmps.core.web.BaseAction;
import cn.heu.hmps.entity.system.User;
import cn.heu.hmps.service.system.ISystemService;
import cn.heu.hmps.util.web.Struts2Utils;

import com.google.gson.Gson;

/**
 * User: Yingtao.Q Date: 11-12-20 Time: 下午4:29
 */
@Scope("prototype")
@Component("systemAction")
public class SystemAction extends BaseAction
{
	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login()
	{
		String getCode = "";
		if(checkNumber != null)
		{
			getCode = this.checkNumber.trim();
		}
		
		String createCode = ((String)session.get("checkNumber")).trim();
		User user = null;
		if(loginName!= null &&password != null)
		{
			user = this.systemService.checkUser(this.loginName.trim(), this.password.trim());
		}
		if (user != null && getCode != "" && getCode.equals(createCode))
		{
			httpSession.setAttribute("userName", user.getUserName());
			httpSession.setAttribute("userID", user.getId());
			return "loginSuccess";
		}
		return "input";
	}
	
	/**
	 * 登出
	 * 
	 * @return
	 */
	public String logout()
	{
		this.session.clear();
		return "logout";
	}
	
	// --依赖--//
	private ISystemService systemService;

	@Resource(name = "systemService")
	public void setSystemService(ISystemService systemService)
	{
		this.systemService = systemService;
	}

	// --页面属性--//
	public String loginName;
	public String password;
	public String checkNumber;

	// --Set.Get方法--//
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}
}

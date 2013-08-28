package cn.heu.hmpsmobile.web.system;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.heu.hmpsmobile.service.system.ISystemService;
import cn.heu.hmpsmobile.util.web.Struts2Utils;
import cn.heu.hmpsmobile.core.web.BaseAction;
import cn.heu.hmpsmobile.entity.system.User;

import com.google.gson.Gson;

/**
 * User: Yingtao.Q Date: 11-12-20 Time: 下午4:29
 */
@Scope("prototype")
@Component("systemAction")
public class SystemAction extends BaseAction
{

	/**
	 * 验证用户
	 * 
	 * @return
	 */
//	public String checkUser()
//	{
//		Gson gson = new Gson();
//		User user = this.systemService.checkUser(this.loginName.trim(),
//				this.password.trim());
//		if (user != null)
//		{
//			httpSession.setAttribute("userName", user.getUserName());
//			httpSession.setAttribute("userID", user.getId());
//			Struts2Utils.renderText("1"); // 登录成功
//		}
//		else
//		{
//			Struts2Utils.renderText("0"); // 登录失败
//
//		}
//		return null;
//	}
	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login()
	{
		User user = this.systemService.checkUser(this.loginName.trim(),
				this.password.trim());
		if (user != null)
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

package cn.heu.hmps.web.department;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.heu.hmps.core.web.BaseAction;
import cn.heu.hmps.entity.department.Telephone;
import cn.heu.hmps.service.department.ITelephoneService;
import cn.heu.hmps.util.web.Struts2Utils;

/**
 * User: Yingtao.Q Date: 12-3-6 Time: 上午11:25
 */
@Scope("prototype")
@Component("departmentAction")
public class DepartmentAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public String listMainDepartmentJSON() throws UnsupportedEncodingException
//	{
//		deptTypeName = new String(deptTypeName.getBytes("ISO-8859-1"), "utf-8");
//		this.departmentList = this.departmentService.getMainDepartment(this.deptTypeName);
//		JSONArray jsonArray = new JSONArray();
//		for (Department department : departmentList)
//		{
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("id", department.getDeptCode());
//			jsonObject.put("name", department.getDeptName());
//			jsonArray.add(jsonObject);
//		}
//		System.out.println(jsonArray.toString());
//		Struts2Utils.renderText(jsonArray.toString());
//		return null;
//	}
//
//	public String listSubDepartmentJSON() throws UnsupportedEncodingException
//	{
//		this.mainDeptCode = new String(
//				this.mainDeptCode.getBytes("ISO-8859-1"), "utf-8");
//		this.departmentList = this.departmentService
//				.getSubDepartment(this.mainDeptCode);
//		JSONArray jsonArray = new JSONArray();
//		for (Department department : departmentList)
//		{
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("name", department.getDeptName());
//			jsonObject.put("telephone", department.getTelephone());
//			jsonArray.add(jsonObject);
//		}
//		Struts2Utils.renderText(jsonArray.toString());
//		return null;
//	}

	// --依赖注入--//
//	private IDepartmentService departmentService;
//
//	@Resource(name = "departmentService")
//	public void setDepartmentService(IDepartmentService departmentService)
//	{
//		this.departmentService = departmentService;
//	}
//
//	// --页面属性--//
//	private String deptTypeName; // 机构类别名称：直属部门、行政机构、教学院系
//	private String mainDeptName; // 一级部门名称
//	private String mainDeptCode; // 一级部门代码
//	private List<Department> departmentList;

//	public List<Department> getDepartmentList()
//	{
//		return departmentList;
//	}
//
//	public void setDepartmentList(List<Department> departmentList)
//	{
//		this.departmentList = departmentList;
//	}
//
//	public String getDeptTypeName()
//	{
//		return deptTypeName;
//	}
//
//	public void setDeptTypeName(String deptTypeName)
//	{
//		this.deptTypeName = deptTypeName;
//	}
//
//	public String getMainDeptName()
//	{
//		return mainDeptName;
//	}
//
//	public void setMainDeptName(String mainDeptName)
//	{
//		this.mainDeptName = mainDeptName;
//	}
//
//	public String getMainDeptCode()
//	{
//		return mainDeptCode;
//	}
//
//	public void setMainDeptCode(String mainDeptCode)
//	{
//		this.mainDeptCode = mainDeptCode;
//	}

	// 依赖注入
	private ITelephoneService telephoneService;

	@Resource(name = "telephoneService")
	public void setTelephoneService(ITelephoneService telephoneService)
	{
		this.telephoneService = telephoneService;
	}

	private Telephone telephone;

	public Telephone getTelephone()
	{
		return telephone;
	}

	public void setTelephone(Telephone telephone)
	{
		this.telephone = telephone;
	}

	// 页面属性
	public String mainDepartment;// 一级部门
	public String departmentCode;// 部门类型所属
	public String subDepartment;// 二级部门

	public String getMainDepartment()
	{
		return mainDepartment;
	}

	public void setMainDepartment(String mainDepartment)
	{
		this.mainDepartment = mainDepartment;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getSubDepartment()
	{
		return subDepartment;
	}

	public void setSubDepartment(String subDepartment)
	{
		this.subDepartment = subDepartment;
	}

	/*
	 * 获取所有一级部门(字段MainDepartment)
	 */
	public String listMainDepartmentJSON()
	{
		// http://localhost:8080/HMPS/department/departmentAction_listMainDepartmentJSON.action?departmentCode=001
		String str = this.getDepartmentCode();
		System.out.println(str);
		List<String> list = this.telephoneService.findByFieldWithDepartmentType(str);
		
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", list.get(i));
			jsonArray.add(jsonObject);
		}
		
		Struts2Utils.renderText(jsonArray.toString());
		return null;
	}

	/*
	 * 获取所有子部门对应的telephone对象
	 */
	public String listSubDepartmentJSON()
	{
		// http://localhost:8080/HMPS/department/departmentAction_listSubDepartmentJSON.action?MainDepartment=宣传部
		String str = this.getMainDepartment();

		// str进行字符串转码
		try
		{
			str = new String(str.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		System.out.println(str);
		List<Telephone> list = this.telephoneService.findByFieldWithMainDepartment(str);
		
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", list.get(i).getSubDepartment());
			jsonObject.put("telephone", list.get(i).getTelephone());
			jsonArray.add(jsonObject);
		}
		
		Struts2Utils.renderText(jsonArray.toString());
		return null;
	}

	/*
	 * 子部门缺省查询
	 */
	public String queryTelephoneJSON()
	{
		// http://localhost:8080/HMPS/department/departmentAction_queryTelephoneJSON.action?subDepartment=秘
		String str = this.getSubDepartment();
		// str进行字符串转码
		try
		{
			str = new String(str.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		System.out.println(str);
		List<Telephone> list = this.telephoneService.findSingleObjectBySubDepartment(str);
		
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", list.get(i).getSubDepartment());
			jsonObject.put("telephone", list.get(i).getTelephone());
			jsonArray.add(jsonObject);
		}
		
		Struts2Utils.renderText(jsonArray.toString());
		return null;
	}
}

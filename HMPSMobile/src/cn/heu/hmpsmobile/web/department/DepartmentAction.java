package cn.heu.hmpsmobile.web.department;

import cn.heu.hmpsmobile.util.web.Struts2Utils;
import cn.heu.hmpsmobile.core.web.BaseAction;
import cn.heu.hmpsmobile.entity.department.Department;
import cn.heu.hmpsmobile.service.department.IDepartmentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * User: Yingtao.Q
 * Date: 12-3-6
 * Time: 上午11:25
 */
@Scope("prototype")
@Component("departmentAction")
public class DepartmentAction extends BaseAction{
    
    public String listMainDepartmentJSON() throws UnsupportedEncodingException {
//        deptTypeName = new String(deptTypeName.getBytes("ISO-8859-1"),"utf-8");
        this.departmentList = this.departmentService.getMainDepartment(this.deptTypeName);
        JSONArray jsonArray = new JSONArray();
        for (Department department : departmentList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", department.getDeptCode());
            jsonObject.put("name", department.getDeptName());
            jsonObject.put("mainDeptCode", department.getDeptCode());
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray.toString());
        Struts2Utils.renderText(jsonArray.toString());

        return null;
    }
    
    public String listSubDepartmentJSON() throws UnsupportedEncodingException {
       // this.mainDeptCode = new String(this.mainDeptCode.getBytes("ISO-8859-1"),"utf-8");
        this.departmentList = this.departmentService.getSubDepartment(this.mainDeptCode);
        JSONArray jsonArray = new JSONArray();
           for (Department department : departmentList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", department.getDeptCode());
            jsonObject.put("name", department.getDeptName());
            jsonObject.put("telephone", department.getTelephone());
            jsonArray.add(jsonObject);
           }
          System.out.println(jsonArray.toString());
          Struts2Utils.renderText(jsonArray.toString());
        return null;
    }
    public String listDetailDepartmentJSON() throws UnsupportedEncodingException {
    	this.department = this.departmentService.getDepartmentByCode(deptCode);
    	if(department != null){
         JSONObject jSONObject = new JSONObject();
         jSONObject.put("name", department.getDeptName());
         jSONObject.put("tel", department.getTelephone());
         System.out.println(jSONObject.toString());
         Struts2Utils.renderText(jSONObject.toString());
    	}else
    	  Struts2Utils.renderText("null");
    	return null;
    
    }

    //--依赖注入--//
    private IDepartmentService departmentService;

    @Resource(name = "departmentService")
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    //--页面属性--//
    private int id;
    private String deptTypeName;  //机构类别名称：直属部门、行政机构、教学院系
    private String mainDeptName; //一级部门名称
    private String mainDeptCode; //一级部门代码
    private List<Department> departmentList;
    private Department department;
    private  String DeptParentCode;
    private String deptCode;

    public String getDeptParentCode() {
		return DeptParentCode;
	}

	public void setDeptParentCode(String deptParentCode) {
		DeptParentCode = deptParentCode;
	}

	public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public String getDeptTypeName() {
        return deptTypeName;
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }

    public String getMainDeptName() {
        return mainDeptName;
    }

    public void setMainDeptName(String mainDeptName) {
        this.mainDeptName = mainDeptName;
    }

    public String getMainDeptCode() {
        return mainDeptCode;
    }

    public void setMainDeptCode(String mainDeptCode) {
        this.mainDeptCode = mainDeptCode;
    }

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}

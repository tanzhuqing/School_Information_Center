package cn.heu.hmpsmobile.entity.department;

import javax.persistence.*;

/**
 * User: Yingtao.Q
 * Date: 12-3-6
 * Time: 上午11:04
 */
@Entity
@Table(name = "Heu_Department")
public class Department {
    
    private int id;
    
    private String deptCode; //部门代码
    
    private String deptName; //部门名称
    
    private int deptType;
    
    private String deptTypeName;  //部门类型名称:行政机构、直属部门、教学院系
    
    private int deptLevel;   //部门级别: 1、一级；2、二级

    private String deptParentCode;  //一级部门代码
    
    private String telephone;  //电话
    
    private int code;  //排序
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "JGDM")
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Column(name = "JGJB")
    public int getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(int deptLevel) {
        this.deptLevel = deptLevel;
    }

    @Column(name = "JGMC")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name = "PJGDM")
    public String getDeptParentCode() {
        return deptParentCode;
    }

    public void setDeptParentCode(String deptParentCode) {
        this.deptParentCode = deptParentCode;
    }

    @Column(name = "JGLB")
    public int getDeptType() {
        return deptType;
    }

    public void setDeptType(int deptType) {
        this.deptType = deptType;
    }

    @Column(name = "JGLBMC")
    public String getDeptTypeName() {
        return deptTypeName;
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }

    @Column(name = "Telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name = "code")
	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}
}

package cn.heu.hmpsmobile.entity.department;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "heu_telephone")
public class Telephone
{
	private int id;
	
	private String MainDepartment;
	
	private String SubDepartment;
	
	private String DepartmentCode;
	
	private String Telephone;
	
	private String DepartmentType;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMainDepartment()
	{
		return MainDepartment;
	}

	public void setMainDepartment(String mainDepartment)
	{
		MainDepartment = mainDepartment;
	}

	public String getSubDepartment()
	{
		return SubDepartment;
	}

	public void setSubDepartment(String subDepartment)
	{
		SubDepartment = subDepartment;
	}

	public String getDepartmentCode()
	{
		return DepartmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		DepartmentCode = departmentCode;
	}

	public String getTelephone()
	{
		return Telephone;
	}

	public void setTelephone(String telephone)
	{
		Telephone = telephone;
	}

	public String getDepartmentType()
	{
		return DepartmentType;
	}

	public void setDepartmentType(String departmentType)
	{
		DepartmentType = departmentType;
	}
}

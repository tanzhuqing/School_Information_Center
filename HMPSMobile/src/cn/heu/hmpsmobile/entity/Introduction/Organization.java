package cn.heu.hmpsmobile.entity.Introduction;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "heu_organization")
public class Organization implements Serializable
{
	private int id;

	private String groupName;

	private String whichLevel;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public String getWhichLevel()
	{
		return whichLevel;
	}

	public void setWhichLevel(String whichLevel)
	{
		this.whichLevel = whichLevel;
	}
}

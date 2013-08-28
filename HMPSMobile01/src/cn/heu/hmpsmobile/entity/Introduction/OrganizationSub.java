package cn.heu.hmpsmobile.entity.Introduction;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "heu_organization_sub")
public class OrganizationSub implements Serializable
{
	private int id;

	private String tid;

	private String child;

	private String myText;

	private String image;

	private String num;

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

	public String getTid()
	{
		return tid;
	}

	public void setTid(String tid)
	{
		this.tid = tid;
	}

	public String getChild()
	{
		return child;
	}

	public void setChild(String child)
	{
		this.child = child;
	}

	public String getMyText()
	{
		return myText;
	}

	public void setMyText(String myText)
	{
		this.myText = myText;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getNum()
	{
		return num;
	}

	public void setNum(String num)
	{
		this.num = num;
	}
}

<%@ page contentType="text/html;charset=gbk"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="xlh.Database"%>
<%@ page import="xlh.Global"%>
<%@ page import="xlh.*"%>

<%
//新闻列表
Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));
Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
String newsid = request.getParameter("newsid"); 
String bt = "";
String zrbj = "";
String sj = "";
String href = "http://125.223.113.81:8080/";
String zw = "";
String isImgNews = "";
ResultSet rs1 = null; //数据集
try
{
  database.open();
  rs1 = database.query("select newsid,zrbj,fbsj,bt,zw,isImgNews from xlh_newsinfo where newsid ='"+newsid+"'");
  while(rs1.next())
  {

    newsid = rs1.getString("newsid");
    bt =  rs1.getString("bt");
    sj = rs1.getString("fbsj").substring(0,10);
		zrbj =  rs1.getString("zrbj");
		zw =  rs1.getString("zw");      
		isImgNews =  rs1.getString("isImgNews");   
  }

}
catch(Exception e)
{

}
finally
{
  database.close();
}
%>
{"newsInfo":[
{
"ID":"<%=newsid%>",
"Title":"<%=bt%>",
"Desc":"",
"Fbr":"<%=zrbj%>",
"Fbrq":"<%=sj%>",
"Content":"<%=zw%>",
"isImgNews":"<%=isImgNews%>"
}
]
}

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
int length_bt = 40; //内容宽度
int length_ms=  60;
//String lmid = request.getParameter("lmid"); 
Vector v1 = null; //默认列表   变量声明页
Vector v2 = null; //默认列表   变量声明页
Properties p1 = null; //一条新闻
Properties p2 = null; //一条新闻
ResultSet rs1 = null; //数据集
String sj = "";
String href = "http://125.223.113.81:8080/";

try
{
  database.open();
  v1 = new Vector();
  v2 = new Vector();
  rs1 = database.query("select newsid,bt from xlh_newsinfo where sfqy = 1 and isImgNews='true' order by fbsj desc");
  while(rs1.next())
  {
    p1 = new Properties();
    p1.setProperty("newsid", rs1.getString("newsid"));
    p1.setProperty("bt", rs1.getString("bt"));
    v1.add(p1);
  }
  for (int ic = 0; ic < v1.size(); ic++) {
			rs1 = database.query("select top 1 filePath from t_pic_file where infoID ='"+((Properties) v1.get(ic)).getProperty("newsid")+"'");
									  while(rs1.next())
										  {

										    p2 = new Properties();
										    p2.setProperty("filePath", href+"HMPS/upload/xwtp/"+rs1.getString("filePath"));
										    p2.setProperty("bt",((Properties) v1.get(ic)).getProperty("bt"));
										    p2.setProperty("newsid",((Properties) v1.get(ic)).getProperty("newsid"));
										    v2.add(p2);
										  }
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
{"indeximglist":[
			        <%for (int i = 0; i < v2.size(); i++) {
			        		if(i>0){
			        		out.print(",");
			        		}
			        %>
{
 "ids":"<%=(((Properties) v2.get(i)).getProperty("newsid"))%>",
 "titles":"<%=(((Properties) v2.get(i)).getProperty("bt"))%>",
 "imgs":"<%=(((Properties) v2.get(i)).getProperty("filePath"))%>"
 }
 <%}%>
 ]}

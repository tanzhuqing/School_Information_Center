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
String lmid = request.getParameter("lmid"); 
Vector v1 = null; //默认列表   变量声明页
Vector v2 = null; //默认列表   变量声明页
Properties p1 = null; //一条新闻
Properties p2 = null; //一条新闻
ResultSet rs1 = null; //数据集
String sj = "";
String href = "http://125.223.113.81:8080/";
//以下处理分页列表显示
int rpage = 6; //每页记录数
int npage = 0; //总页数
int nrow = 0; //总记录数
int cpage = 1; //当前页数
int i = 0;

try
{
  database.open();
  v1 = new Vector();
  v2 = new Vector();
  rs1 = database.query("select newsid,tp,zrbj,fbsj,bt,ms,isImgNews from xlh_newsinfo where lmid ='"+lmid+"' and sfqy = 1  order by fbsj desc");
  while(rs1.next())
  {

    p1 = new Properties();
    
    p1.setProperty("newsid", rs1.getString("newsid"));
     
    if(rs1.getString("bt").length() <= length_bt)
      p1.setProperty("bt", rs1.getString("bt"));
    else
      p1.setProperty("bt", rs1.getString("bt").substring(0, length_bt) + "...");

    if(rs1.getString("ms").length() <= length_ms)
      p1.setProperty("ms", rs1.getString("ms"));
    else
      p1.setProperty("ms", rs1.getString("ms").substring(0, length_ms) + "...");

    if(rs1.getString("tp").equals(""))
      p1.setProperty("tp", href+"HMPS/upload/temp/temp.png");
    else
      p1.setProperty("tp", href+"HMPS/upload/xwtp/"+rs1.getString("tp"));
            
    sj = rs1.getString("fbsj").substring(0,10);

    p1.setProperty("sj", sj);
    p1.setProperty("zrbj", rs1.getString("zrbj"));  
    p1.setProperty("isImgNews", rs1.getString("isImgNews")); 
    v1.add(p1);
  }
  

if (request.getParameter("cpage") != null)
  cpage = Integer.parseInt(request.getParameter("cpage"));

nrow = v1.size();
i = 0;
    while (i < v1.size())
    {
        if (((cpage == 1) && (i < rpage)) || ((cpage != 1) && (i >= (cpage - 1) * rpage) && (i < cpage * rpage)))
            v2.add(v1.get(i));

        if (i >= cpage * rpage)
            break;
        i = i + 1;
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
{"newslist":[
			        <%for (int ic = 0; ic < v2.size(); ic++) {
			        		if(ic>0){
			        		out.print(",");
			        		}
			        %>
{
 "ID":"<%=(((Properties) v2.get(ic)).getProperty("newsid"))%>",
 "Title":"<%=(((Properties) v2.get(ic)).getProperty("bt"))%>",
 "Desc":"<%=(((Properties) v2.get(ic)).getProperty("ms"))%>",
 "Fbr":"<%=(((Properties) v2.get(ic)).getProperty("zrbj"))%>",
 "Fbrq":"<%=(((Properties) v2.get(ic)).getProperty("sj"))%>",
 "ImgUrl":"<%=(((Properties) v2.get(ic)).getProperty("tp"))%>",
 "isImgNews":"<%=(((Properties) v2.get(ic)).getProperty("isImgNews"))%>"
 }
 <%}%>
 ]}

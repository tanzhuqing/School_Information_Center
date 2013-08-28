<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="xlh.Global" %>
<%@ page import="xlh.Database" %>

<%
StringBuffer sb = new StringBuffer();
ResultSet rs = null;

SimpleDateFormat sdf = null;
java.util.Date current = new java.util.Date((Calendar.getInstance().getTime()).getTime() - 5 * 60 * 1000); //当前时间减去5分钟

sdf = new SimpleDateFormat("yyyy-MM-dd");
String current_date = sdf.format(current);

sdf = new SimpleDateFormat("HH:mm:ss");
//sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
String current_time = sdf.format(current);

Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));

Database database = new Database(Global.getInstance().getPath() + "//WEB-INF//config.xml");
  
try
{
  database.open();
  
  sb.append("{\"list\":[" + "\r\n");
 
  rs = database.query("select * from t_xxlq where rq = '" + current_date + "' and sj >= '" + current_time + "'");
  while (rs.next())
  {
    if (! (sb.toString()).equals("{\"list\":[" + "\r\n"))
      sb.append("," + "\r\n");
    
    sb.append("{\"id\":\"" + rs.getString("xxlq_id") + "\",\"title\":\"" + rs.getString("title") + "\",\"content\":\"" + rs.getString("content") + "\"}");
  }
  
  sb.append("\r\n" + "]}");
}
catch (Exception e)
{
  System.out.println(e.getMessage());
}
finally
{
  database.close();
}

out.println(sb.toString());

/*格式
{"list":[
{"id":"4","title":"4","content":"第4教学楼"},
{"id":"5","title":"5","content":"第5教学楼"},
{"id":"6","title":"6","content":"第6教学楼"}
]}
*/
%>

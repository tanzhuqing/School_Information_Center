<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Global" %>
<%@ page import="xlh.Database" %>

<%
StringBuffer sb = new StringBuffer();
ResultSet rs = null;
ResultSet rs1 = null;

Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));

Database database = new Database(Global.getInstance().getPath() + "//WEB-INF//config.xml");
  
try
{
  database.open();
  
  sb.append("{\"list\":[" + "\r\n");
  
  rs = database.query("select * from t_jxl");
  while (rs.next())
  {
    if (! (sb.toString()).equals("{\"list\":[" + "\r\n"))
      sb.append("," + "\r\n");
    
    sb.append("{\"id\":\"" + rs.getString("jxl_id") + "\",\"name\":\"" + rs.getString("jxl_name") + "\",");
      
    rs1 = database.query("select count(*) from t_js where jxl_id = '" + rs.getString("jxl_id") + "'");
    if (rs1.next())
      sb.append("\"count\":\"" + rs1.getString(1) + "\"}");
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

/*��ʽ
{"list":[
{"id":"1","name":"��1��ѧ¥","count":"32"},
{"id":"2","name":"��2��ѧ¥","count":"42"},
{"id":"3","name":"��3��ѧ¥","count":"52"},
{"id":"4","name":"��4��ѧ¥","count":"62"},
{"id":"5","name":"��5��ѧ¥","count":"12"},
{"id":"6","name":"��6��ѧ¥","count":"22"}
]}
*/
%>
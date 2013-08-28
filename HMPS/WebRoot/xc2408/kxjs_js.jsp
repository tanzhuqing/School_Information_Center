<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Global" %>
<%@ page import="xlh.Database" %>

<%
String jxl_id = request.getParameter("jxl_id");
String period = request.getParameter("period");
ResultSet rs = null;
ResultSet rs1 = null;
String rq = Global.getInstance().getDate();
String jxz = null;
String xq = null;
StringBuffer sb = new StringBuffer();
String kj = "";

Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));

Database database = new Database(Global.getInstance().getPath() + "//WEB-INF//config.xml");

try
{
  database.open();
  
  rs = database.query("select * from t_jxz where rq = '" + rq + "'");
  if (rs.next())
  {
    jxz = rs.getString("jxz");
    xq = rs.getString("xq");
  }
  
  if (period.equals("0")) //上午
  {
    sb.append("{\"list\":[" + "\r\n");
  
    rs = database.query("select * from t_js where jxl_id = '" + jxl_id + "'");
    while (rs.next())
    {
      if (! (sb.toString()).equals("{\"list\":[" + "\r\n"))
        sb.append("," + "\r\n");
      
      kj = "";
 
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#1#%'");
      if (! rs1.next())
        kj = "1, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#2#%'");
      if (! rs1.next())
        kj = kj + "2, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
        
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#3#%'");
      if (! rs1.next())
        kj = kj + "3, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#4#%'");
      if (! rs1.next())
        kj = kj + "4, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (kj.equals(""))
        kj = "无";
      
      sb.append("{\"js_name\":\"" + rs.getString("js_name") + "\",\"kj\":\"" + kj + "\"}");
    }
    
    sb.append("\r\n" + "]}");
  }
  
  if (period.equals("1")) //下午
  {
    sb.append("{\"list\":[" + "\r\n");
  
    rs = database.query("select * from t_js where jxl_id = '" + jxl_id + "'");
    while (rs.next())
    {
      if (! (sb.toString()).equals("{\"list\":[" + "\r\n"))
        sb.append("," + "\r\n");
      
      kj = "";
 
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#5#%'");
      if (! rs1.next())
        kj = "5, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
        
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#6#%'");
      if (! rs1.next())
        kj = kj + "6, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#7#%'");
      if (! rs1.next())
        kj = kj + "7, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
        
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#8#%'");
      if (! rs1.next())
        kj = kj + "8, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (kj.equals(""))
        kj = "无";
        
      sb.append("{\"js_name\":\"" + rs.getString("js_name") + "\",\"kj\":\"" + kj + "\"}");
    }
    
    sb.append("\r\n" + "]}");
  }
  
  if (period.equals("2")) //晚上
  {
    sb.append("{\"list\":[" + "\r\n");
  
    rs = database.query("select * from t_js where jxl_id = '" + jxl_id + "'");
    while (rs.next())
    {
      if (! (sb.toString()).equals("{\"list\":[" + "\r\n"))
        sb.append("," + "\r\n");
      
      kj = "";
 
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#9#%'");
      if (! rs1.next())
        kj = "9, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
        
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#10#%'");
      if (! rs1.next())
        kj = kj + "10, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      rs1 = database.query("select * from t_kb where js_id = '" + rs.getString("js_id") + "' and jxz like '%#" + jxz + "#%' and xq = '" + xq + "' and kj like '%#11#%'");
      if (! rs1.next())
        kj = kj + "11, ";
      rs1.getStatement().close(); //该句有的数据库可能用不上
        
      if (kj.equals(""))
        kj = "无";
        
      sb.append("{\"js_name\":\"" + rs.getString("js_name") + "\",\"kj\":\"" + kj + "\"}");
    }
    
    sb.append("\r\n" + "]}");
  }
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
{
    "list":
    [
    {"js_name":"H2107","kj":"1, 2, 3, 4"},
    {"js_name":"H2108","kj":"1, 2, 3, 4"},
    {"js_name":"H2109","kj":"1, 2, 3, 4"},
    {"js_name":"H2110","kj":"1, 2, 3, 4"},
    {"js_name":"H2111","kj":"1, 2, 3, 4"},
    {"js_name":"H2112","kj":"1, 2, 3, 4"}
    ]
}
*/
%>
<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Global" %>
<%@ page import="xlh.Database" %>

<%
//研究生课表生成维护
String qsrq = request.getParameter("qsrq");
String jzrq = request.getParameter("jzrq");
String message = null;
ResultSet rs = null;
ResultSet rs1 = null;
String time = null;
String jxz = null;
String kb_id = null;
int i = 0;

if (qsrq.substring(0, 4) == jzrq.substring(0, 4))
  time = qsrq.substring(0, 4) + "2";
else
  time = qsrq.substring(0, 4) + "1";

Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));

Database database = new Database(Global.getInstance().getPath() + "//WEB-INF//config.xml");

try
{
  database.open();
  
  //删除旧数据
  database.update("delete from t_kb where bz = '1'");
  
  //增加新数据
  rs = database.query(" select * from t_js");
  while (rs.next())
  { 
    for (i = 1; i <= 7; i++)
    {
      //12课节
      jxz = "#";
      rs1 = database.query(" select * from t_yjskb where js_id = '" + rs.getString("js_id") + "' and xq = '" + String.valueOf(i) + "' and kj = '12' and time = '" + time + "' order by jxz");
      while (rs1.next())
      {
        kb_id = rs1.getString("kb_id");
        
        if (jxz.indexOf(rs1.getString("jxz")) == -1)
          jxz = jxz + rs1.getString("jxz") + "#";
      }
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (! jxz.equals("#"))
        database.update("insert into t_kb(kb_id, js_id, jxz, xq, kj, bz) values('" + kb_id + "', '" + rs.getString("js_id") + "', '" + jxz + "', '" + String.valueOf(i) + "', '#1#2#', '1')");
      
      //34课节
      jxz = "#";
      rs1 = database.query(" select * from t_yjskb where js_id = '" + rs.getString("js_id") + "' and xq = '" + String.valueOf(i) + "' and kj = '34' and time = '" + time + "' order by jxz");
      while (rs1.next())
      {
        kb_id = rs1.getString("kb_id");
        
        if (jxz.indexOf(rs1.getString("jxz")) == -1)
          jxz = jxz + rs1.getString("jxz") + "#";
      }
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (! jxz.equals("#"))
        database.update("insert into t_kb(kb_id, js_id, jxz, xq, kj, bz) values('" + kb_id + "', '" + rs.getString("js_id") + "', '" + jxz + "', '" + String.valueOf(i) + "', '#3#4#', '1')");
    
      //56课节
      jxz = "#";
      rs1 = database.query(" select * from t_yjskb where js_id = '" + rs.getString("js_id") + "' and xq = '" + String.valueOf(i) + "' and kj = '56' and time = '" + time + "' order by jxz");
      while (rs1.next())
      {
        kb_id = rs1.getString("kb_id");
        
        if (jxz.indexOf(rs1.getString("jxz")) == -1)
          jxz = jxz + rs1.getString("jxz") + "#";
      }
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (! jxz.equals("#"))
        database.update("insert into t_kb(kb_id, js_id, jxz, xq, kj, bz) values('" + kb_id + "', '" + rs.getString("js_id") + "', '" + jxz + "', '" + String.valueOf(i) + "', '#5#6#', '1')");
        
      //78课节
      jxz = "#";
      rs1 = database.query(" select * from t_yjskb where js_id = '" + rs.getString("js_id") + "' and xq = '" + String.valueOf(i) + "' and kj = '78' and time = '" + time + "' order by jxz");
      while (rs1.next())
      {
        kb_id = rs1.getString("kb_id");
        
        if (jxz.indexOf(rs1.getString("jxz")) == -1)
          jxz = jxz + rs1.getString("jxz") + "#";
      }
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (! jxz.equals("#"))
        database.update("insert into t_kb(kb_id, js_id, jxz, xq, kj, bz) values('" + kb_id + "', '" + rs.getString("js_id") + "', '" + jxz + "', '" + String.valueOf(i) + "', '#7#8#', '1')");
        
      //910课节
      jxz = "#";
      rs1 = database.query(" select * from t_yjskb where js_id = '" + rs.getString("js_id") + "' and xq = '" + String.valueOf(i) + "' and kj = '910' and time = '" + time + "' order by jxz");
      while (rs1.next())
      {
        kb_id = rs1.getString("kb_id");
        
        if (jxz.indexOf(rs1.getString("jxz")) == -1)
          jxz = jxz + rs1.getString("jxz") + "#";
      }
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (! jxz.equals("#"))
        database.update("insert into t_kb(kb_id, js_id, jxz, xq, kj, bz) values('" + kb_id + "', '" + rs.getString("js_id") + "', '" + jxz + "', '" + String.valueOf(i) + "', '#9#10#', '1')");
    }
  }

  message = "研究生课表生成完毕";
}
catch (Exception e)
{
  System.out.println(e.getMessage());
}
finally
{
  database.close();
}
%>

<script language="javascript">
  alert("<%=message%>");
</script>
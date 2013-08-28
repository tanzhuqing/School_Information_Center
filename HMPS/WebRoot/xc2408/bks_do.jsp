<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Global" %>
<%@ page import="xlh.Database" %>

<%
//本科生课表生成维护
String qsrq = request.getParameter("qsrq");
String jzrq = request.getParameter("jzrq");
String message = null;
ResultSet rs = null;
ResultSet rs1 = null;
String jxz = null;
String js_id = null;
String kj = null;
String[] s1 = null;
String[] s2 = null;
int i = 0;
int j = 0;

Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));

Database database = new Database(Global.getInstance().getPath() + "//WEB-INF//config.xml");

try
{
  database.open();
  
  //删除旧数据
  database.update("delete from t_kb where bz = '0'");
  
  //增加新数据
  rs = database.query(" select * from t_bkskb where firstyear = '" + qsrq.substring(0, 4) + "' and endyear = '" + jzrq.substring(0, 4) + "'");
  while (rs.next())
  { 
    rs1 = database.query(" select * from t_js_bks where roomno = '" + rs.getString("classroomno") + "'");
    if (rs1.next())
    {
      js_id = rs1.getString("js_id");
      rs1.getStatement().close(); //该句有的数据库可能用不上
      
      if (js_id != null)
      {
        kj = "#";
        for (i = Integer.parseInt(rs.getString("startsection")); i <= Integer.parseInt(rs.getString("endsection")); i++)
          kj = kj + String.valueOf(i) + "#";
        
        jxz = "#";
        s1 = rs.getString("resultweeks").split("\\.");
        for (i = 0; i < s1.length; i++)
        {
          if (s1[i].indexOf("-") == -1)
            jxz = jxz + s1[i] + "#";
          else
          {
            s2 = s1[i].split("\\-");
            
            for (j = Integer.parseInt(s2[0]); j <= Integer.parseInt(s2[s2.length - 1]); j++)
              jxz = jxz + j + "#";
          }
        }

        database.update("insert into t_kb(kb_id, js_id, jxz, xq, kj, bz) values('" + rs.getString("resultid") + "', '" + js_id + "', '" + jxz + "', '" + rs.getString("weekdayid") + "', '" + kj + "', '0')");
      }
    }
  }

  message = "本科生课表生成完毕";
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
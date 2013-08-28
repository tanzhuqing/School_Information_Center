<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="xlh.Global" %>
<%@ page import="xlh.Database" %>

<%
//教学周生成维护
String qsrq = request.getParameter("qsrq");
String jzrq = request.getParameter("jzrq");
String rq = null; //日期
int jxz = 1; //教学周
int xq = 0; //星期
java.util.Date current = null;
Calendar calendar = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String message = null;

Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));

Database database = new Database(Global.getInstance().getPath() + "//WEB-INF//config.xml");

try
{
  database.open();
  
  //删除旧数据
  database.update("delete from t_jxz where rq >= '" + qsrq + "' and rq <= '" + jzrq + "'");
  
  current = sdf.parse(qsrq);
  
  //增加新数据
  while (current.getTime() <= sdf.parse(jzrq).getTime())
  {
    calendar.setTime(current);
    xq = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    if (xq == 0)
      xq = 7;
    
    rq = sdf.format(current);

    database.update("insert into t_jxz(jxz, rq, xq) values('" + String.valueOf(jxz) + "', '" + rq + "', '" + String.valueOf(xq) + "')");
    
    current = new java.util.Date(current.getTime() + 24 * 60 * 60 * 1000);
    
    if (xq == 7)
      jxz = jxz + 1;
  }

  message = "教学周生成完毕";
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
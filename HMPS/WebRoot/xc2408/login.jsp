<%@ page contentType="text/html;charset=gb2312" %>

<%
String user = request.getParameter("user");
String password = request.getParameter("password");

if ((user.equals("admin")) && (password.equals("password")))
  out.println("yes");
else
  out.println("no");
%>
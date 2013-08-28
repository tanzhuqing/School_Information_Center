<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>
<%
//接受栏目代码
String lmid = request.getParameter("lmid");
if(lmid == null) lmid = "";

%>
<html>
<head>
<title>信息发布</title>
<script language="javascript">
function work_query()
{
  document.news.target = "fz_newslb";
  document.news.action = "fz_newslb.jsp";
  document.news.submit();
}
</script>
</head>
<body style="text-align:center;">
<table width="100%" height="25" border=0 cellPadding=0 cellSpacing=0  background="images/pic_gzdtbg.gif">
    <tr>
      <td width="28" height="25" align="left" valign="top"><IMG height=25 alt="" src="images/pic_glhongdian.jpg" width=28></td>
      <td width="100%" height="25" align="left" valign="middle" background="images/title_1.jpg" style="PADDING-TOP: 3px"><STRONG>&nbsp;信息管理</STRONG></td>
    </tr>
</table>

<input name="lmid" type="hidden" value="<%=lmid%>">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
  <tr><td width="100%" height="100%" align="center" valign="top">
    <iframe name="fz_newslb" src="fz_newslb.jsp?lmid=<%=lmid%>" frameborder="0" scrolling="no" style="height:477;width: 100%; visibility: inherit; z-index: 0;"></iframe>
  </td></tr>
</table>
</body>
<script language="JavaScript" type="text/javascript" src="../js/WebCalendar.js"></script>
</html>




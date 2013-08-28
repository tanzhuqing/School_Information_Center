<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动校园门户管理平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
A:link	{
	text-decoration:	none;
	color:	#000066;
	}

A:visited	{
	text-decoration:	none;
	color:	#003399;
	}

A:active	{
	text-decoration:	none;
	color:	#FF0000;
	}

A:hover	{
	text-decoration:	none;
	color:	#FF0000;
	}
</style>
</head>
<%
	if(request.getSession().getAttribute("userName") == null || request.getSession().getAttribute("userName").equals(""))
	{
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
%>
<frameset rows="141,*" cols="*" frameborder="NO" border="0" framespacing="0">
    <frame src="${ctx}/top.jsp" name="topFrame" scrolling="NO"  noresize="noresize">
    <frameset cols="198,18,*" name="mainFrame" id="mainFrame" frameborder="auto" border="1" framespacing="0" rows="*">
         <frame name="left" src="${ctx}/left.jsp"  >
         <frame name="mid" src="${ctx}/mid.jsp"  scrolling="NO" frameborder="NO">
         <frame name="main" src="${ctx}/main.jsp" frameborder="auto">
    </frameset>
</frameset><noframes></noframes>

<body  background="images/bg.jpg">
</body>
</html>

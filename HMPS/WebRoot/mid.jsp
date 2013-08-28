<%--
  User: Yingtao.Q
  Date: 11-9-2
  Time: 下午4:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上评教管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color:#dedfde;
}
-->
</style>
<link rel="StyleSheet" href="${ctx}/css/mainstyle.css" type="text/css" />
	<base target="main" />
	<script>
function preloadImg(src)
{
	var img=new Image();
	img.src=src
}
preloadImg("${ctx}/images/left_open.gif");

var displayBar=true;
function switchBar(obj)
{
	if (displayBar)
	{
		parent.document.getElementById('mainFrame').setAttribute("cols","0,18,*");
		displayBar=false;
		obj.src="${ctx}/images/left_open.gif";
		obj.title="打开左边管理菜单";
	}
	else{
		parent.document.getElementById('mainFrame').setAttribute("cols","198,18,*");
		displayBar=true;
		obj.src="${ctx}/images/left_close.gif";
		obj.title="关闭左边管理菜单";
	}
}
</script>

</head>
<body>
<table width="18" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="512" align="center" valign="top" background="${ctx}/images/bg-mid.jpg" style="background-repeat:repeat-x;"><table width="80%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30"></td>
  </tr>
  <tr>
    <td align="center"><img onclick="switchBar(this)" src="${ctx}/images/left_close.gif" title="关闭左边管理菜单" style="cursor:hand" /></td>
  </tr>
</table>
</td>
  </tr>
  <tr>
    <td height="25"><img src="${ctx}/images/mid2.jpg" height="25" width="18"/></td>
  </tr>
</table>
</body>
</html>

<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>


<%
//取所属站点代码
String sszd ="8";
Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
String fbsj = Global.getInstance().getYear() + "-" + Global.getInstance().getMonth() + "-" + Global.getInstance().getDay();

%>
<html>
<head>
<title>信息发布_增加</title>
<link href="style.css" rel="stylesheet" type="text/css">

<script language="javascript">
function work_do()
{	
  if (document.newsadd.bt.value == "")
  {
    alert("标题不能为空!");
    document.newsadd.bt.focus();
    return false;
  }  
    
  if (document.newsadd.lm.value == "")
  {
    alert("栏目不能为空!");
    return false;
  }
  
  if (document.newsadd.zw.value == "")
  {
    alert("内容不能为空!");
    return false;
    
  }

  document.newsadd.submit();
}


</script>
</head>
<body style="text-align:center;">
<table width="100%" height="25" border=0 cellPadding=0 cellSpacing=0 >
<tr>
<td width="100%" height="25" align="left" valign="middle" style="PADDING-TOP: 3px"><STRONG>&nbsp;信息发布_增加</STRONG></td>
</tr>
</table>
<table width="95%" height="400" border="0" align="center" cellpadding="0" cellspacing="1" bordercolordark="#FFFFFF" >
<tr>
<td width="100%" valign="top">
<form name="newsadd" method="post" action="fz_newswh.jsp?operate=add" enctype="multipart/form-data">
<table width="100%" height="104" border="0" align="center" cellpadding="0" cellspacing="1" bordercolordark="#FFFFFF" >

<tr>
<td width="113" height="26" align="center" bgcolor="#FFFFFF" class="text2">标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
<td width="1291" bgcolor="#FFFFFF"> &nbsp;
<input type="text" name="bt" value="" style="width:560px;"></td>
</tr>

<tr>
<td width="113" height="26" align="center" bgcolor="#FFFFFF" class="text2">发布时间：</td>
<td width="1291" bgcolor="#FFFFFF"> &nbsp;
<input type="text" name="fbsj" value="<%=fbsj%>" style="width:560px;"></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">栏&nbsp;&nbsp;&nbsp;&nbsp;目：</td>
<td bgcolor="#FFFFFF">&nbsp;
<select name="lm">
<option value=""></option>
<option value="1">新闻中心</option>
</select></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">作&nbsp;&nbsp;&nbsp;&nbsp;者：</td>
<td bgcolor="#FFFFFF">&nbsp; <input type="text" name="zz" value="本网采编" style="width:260px;"></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">标志图片：</td>
<td bgcolor="#FFFFFF"> &nbsp;
<input type="file" name="tp" style="width:560px;"></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">正文：</td>
<td width="100%" height="222" colspan="2" align="center" valign="top" bordercolordark="#FFFFFF">
<textarea name="zw" cols="99" rows="18"></textarea>
</td>
</tr>

<tr>
<td colspan="2" width="100%" height="17" align="center" valign="top" bordercolor="#ABCBCB" bordercolordark="#FFFFFF"><input name="button" type="button" class="anniu" onClick="work_do();" value="确 定">
</td>
</tr>
</table>

</form>
</td>
</tr>
</table>
</body>                                                            
</html>

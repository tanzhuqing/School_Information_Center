<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>


<%
//ȡ����վ�����
String sszd ="8";
Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
String fbsj = Global.getInstance().getYear() + "-" + Global.getInstance().getMonth() + "-" + Global.getInstance().getDay();

%>
<html>
<head>
<title>��Ϣ����_����</title>
<link href="style.css" rel="stylesheet" type="text/css">

<script language="javascript">
function work_do()
{	
  if (document.newsadd.bt.value == "")
  {
    alert("���ⲻ��Ϊ��!");
    document.newsadd.bt.focus();
    return false;
  }  
    
  if (document.newsadd.lm.value == "")
  {
    alert("��Ŀ����Ϊ��!");
    return false;
  }
  
  if (document.newsadd.zw.value == "")
  {
    alert("���ݲ���Ϊ��!");
    return false;
    
  }

  document.newsadd.submit();
}


</script>
</head>
<body style="text-align:center;">
<table width="100%" height="25" border=0 cellPadding=0 cellSpacing=0 >
<tr>
<td width="100%" height="25" align="left" valign="middle" style="PADDING-TOP: 3px"><STRONG>&nbsp;��Ϣ����_����</STRONG></td>
</tr>
</table>
<table width="95%" height="400" border="0" align="center" cellpadding="0" cellspacing="1" bordercolordark="#FFFFFF" >
<tr>
<td width="100%" valign="top">
<form name="newsadd" method="post" action="fz_newswh.jsp?operate=add" enctype="multipart/form-data">
<table width="100%" height="104" border="0" align="center" cellpadding="0" cellspacing="1" bordercolordark="#FFFFFF" >

<tr>
<td width="113" height="26" align="center" bgcolor="#FFFFFF" class="text2">��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
<td width="1291" bgcolor="#FFFFFF"> &nbsp;
<input type="text" name="bt" value="" style="width:560px;"></td>
</tr>

<tr>
<td width="113" height="26" align="center" bgcolor="#FFFFFF" class="text2">����ʱ�䣺</td>
<td width="1291" bgcolor="#FFFFFF"> &nbsp;
<input type="text" name="fbsj" value="<%=fbsj%>" style="width:560px;"></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">��&nbsp;&nbsp;&nbsp;&nbsp;Ŀ��</td>
<td bgcolor="#FFFFFF">&nbsp;
<select name="lm">
<option value=""></option>
<option value="1">��������</option>
</select></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">��&nbsp;&nbsp;&nbsp;&nbsp;�ߣ�</td>
<td bgcolor="#FFFFFF">&nbsp; <input type="text" name="zz" value="�����ɱ�" style="width:260px;"></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">��־ͼƬ��</td>
<td bgcolor="#FFFFFF"> &nbsp;
<input type="file" name="tp" style="width:560px;"></td>
</tr>

<tr>
<td height="26" align="center" bgcolor="#FFFFFF" class="text2">���ģ�</td>
<td width="100%" height="222" colspan="2" align="center" valign="top" bordercolordark="#FFFFFF">
<textarea name="zw" cols="99" rows="18"></textarea>
</td>
</tr>

<tr>
<td colspan="2" width="100%" height="17" align="center" valign="top" bordercolor="#ABCBCB" bordercolordark="#FFFFFF"><input name="button" type="button" class="anniu" onClick="work_do();" value="ȷ ��">
</td>
</tr>
</table>

</form>
</td>
</tr>
</table>
</body>                                                            
</html>

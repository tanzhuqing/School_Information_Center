<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="xlh.Global" %>

<%
String qsrq = Global.getInstance().getDate(); 
String jzrq = Global.getInstance().getDate(); 
%>

<html>
<head>
<title>���н�����������</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
.bg
{
  background-color: white;
  background-image: url("../style/images/bg01.jpg");
  background-repeat: repeat-x;
}

.title1
{
  FONT-SIZE: 20px; COLOR: #363737; LINE-HEIGHT: 22px; FONT-FAMILY: "����"; FONT-WEIGHT: bold;
}
</style>
<script language="javascript" src="../js/calendar.js"></script>
<script language="javascript">
function work_ok()
{
  if (document.jxz_form.qsrq.value > document.jxz_form.jzrq.value)
  {
    alert("��ʼ���ڲ��ܴ��ڽ�ֹ����");
    return;
  }
  
  document.jxz_form.action = "jxz_do.jsp";
  document.jxz_form.submit();
}

function work_yjs()
{
  document.jxz_form.action = "yjs_do.jsp";
  document.jxz_form.submit();
}

function work_bks()
{
  document.jxz_form.action = "bks_do.jsp";  
  document.jxz_form.submit();
}
</script>
</head>
<body style="text-align:center;" class="bg">
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td valign="middle" align="center">
  <table width="300" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="title1" align="center">���н�����������</td>
    </tr>
  </table>
  <form method="post" name="jxz_form" target="jxz_frame">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" align="right" class="text">��ʼ���ڣ�</td>
      <td><input name="qsrq" type="text" class="input" style="width:150px;" onfocus="showCalendar(this);" value="<%=qsrq%>" readonly></td>
    </tr>
    <tr>
      <td height="30" align="right" class="text">��ֹ���ڣ�</td>
      <td><input name="jzrq" type="text" class="input" style="width:150px;" onfocus="showCalendar(this);" value="<%=jzrq%>" readonly></td>
    </tr>
    <tr>
      <td colspan="2" height="70" align="center">&nbsp;&nbsp;
      <input onclick="work_ok();" value="�� �� �� ѧ ��" type="button">&nbsp;&nbsp;<br>
    </tr>
    <tr>
      <td colspan="2" height="70" align="center">&nbsp;&nbsp;
      <input onclick="work_yjs();" value="�����о����α�" type="button">&nbsp;&nbsp;<br>
    </tr>
    <tr>
      <td colspan="2" height="70" align="center">&nbsp;&nbsp;
      <input onclick="work_bks();" value="���ɱ������α�" type="button">&nbsp;&nbsp;<br>
    </tr>
  </table>
  </form>
</td></tr></table>
<iframe name="jxz_frame" src="" frameborder="0" scrolling="no" width="0" height="0"></iframe>
</body>
</html>

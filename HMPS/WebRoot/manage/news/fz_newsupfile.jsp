<%@ page language="java" %>
<%@ page contentType="text/html;charset=gbk"%>
<%@page import="com.hrbxlh.common.*"%>
<%@page import="java.util.*"%>

<% 
    int id = Integer.parseInt(request.getParameter("id"));
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link href="/CSS/style.css" rel="stylesheet" type="text/css" />
<title>�ϴ�����</title>


<SCRIPT LANGUAGE="JavaScript">
<!--
function checknull()
{
       if (form2.attachment.value==""){
      	alert("��ѡ���ϴ�����")
    	return false;
        }

    	form2.submit();

}
//-->
</SCRIPT>

</head>

<body>
<div id="main">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="4" style="margin-top:0px;" >
  <tr>
    <td height="50" align="center" valign="bottom" class="color013">�ϴ�����<br>
      <br>
    </span></td>
  </tr>
  <tr>
    <td height="2" align="center"></td>
  </tr>
     <form name="form2" action="fz_newsupload.jsp" method="post" ENCTYPE="multipart/form-data" >               
        <tr>
          <td height="30" colspan="4">
              &nbsp;&nbsp;1���㡰���������ť��ѡ������Ҫ�ϴ��ĸ�����<input type="FILE" name="attachment" class="textfield" size="45" onKeyPress="javascript:return false;">
          </td>
        </tr>
		     <tr>
          <td height="30" colspan="4">ͼƬ˵����
              <textarea name="sm" cols="120" rows="10" ></textarea>
          </td>
        </tr>
        <tr>
          <td height="30" colspan="4">&nbsp;&nbsp;2��ѡ��һ�������󣬵������ӡ���ť�ϴ�����ע�⣺�ϴ�������Ҫ����1M��&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="input1" value="��  ��" onclick="javascript:checknull();"></td>        
				
		</tr>
		<input type="hidden" name="id" value="<%=id%>">
		
		</form>
</table>
</div>
</body>
</html>

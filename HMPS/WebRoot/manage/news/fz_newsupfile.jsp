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
<title>上传附件</title>


<SCRIPT LANGUAGE="JavaScript">
<!--
function checknull()
{
       if (form2.attachment.value==""){
      	alert("请选择上传附件")
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
    <td height="50" align="center" valign="bottom" class="color013">上传附件<br>
      <br>
    </span></td>
  </tr>
  <tr>
    <td height="2" align="center"></td>
  </tr>
     <form name="form2" action="fz_newsupload.jsp" method="post" ENCTYPE="multipart/form-data" >               
        <tr>
          <td height="30" colspan="4">
              &nbsp;&nbsp;1、点“浏览…”按钮，选择你想要上传的附件：<input type="FILE" name="attachment" class="textfield" size="45" onKeyPress="javascript:return false;">
          </td>
        </tr>
		     <tr>
          <td height="30" colspan="4">图片说明：
              <textarea name="sm" cols="120" rows="10" ></textarea>
          </td>
        </tr>
        <tr>
          <td height="30" colspan="4">&nbsp;&nbsp;2、选择一个附件后，点击“添加”按钮上传；（注意：上传附件不要大于1M）&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="input1" value="添  加" onclick="javascript:checknull();"></td>        
				
		</tr>
		<input type="hidden" name="id" value="<%=id%>">
		
		</form>
</table>
</div>
</body>
</html>

<%@ page language="java"%>
<%@ page contentType="text/html;charset=gbk"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.File"%>
<%@page import="com.hrbxlh.common.*"%>
<%@ page import="com.hrbxlh.form.*"%>
<%@ page import="com.hrbxlh.dao.*"%>
<%@ page import="com.hrbxlh.common.*"%>



<%
 request.setCharacterEncoding("gbk"); 
 int fileID = ParameterParser.getIntPara(request,"fileID"); 
 int newid = ParameterParser.getIntPara(request,"newid"); 


 
 int count = 0;
	
	 
         ManageSLFile manageSLFile = new ManageSLFile();
		 count = manageSLFile.DeleteSLFile(fileID);
       
if(count>0)
{

		%>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
                alert("文件删除成功！");
        
		//window.opener.document.location.reload();
		//window.close();
		
		//-->
		</SCRIPT>
		<%
			response.sendRedirect("fz_newspicadd.jsp?newid="+newid);
	}
	else{//上传失败
		%>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
		alert("文件删除失败！");
           history.back();
		  // window.close();
		//-->
		</SCRIPT>
		<%
	}
%>

<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>
<%@ page import="com.hrbxlh.dao.*"%>
<%@ page import="com.hrbxlh.form.*"%>
<%
//ȡ����վ�����
String sszd = "8";
//��ȡ����id
//String newid = ParameterParser.getStrPara(request,"newid");
String lmid = request.getParameter("lmid");



 		 Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));
 		 Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
		 int newid =0;
         newid = Integer.parseInt(request.getParameter("newid"));
		 String fbsj = "";
		 String bt = "";
		
     ResultSet rs = null; 

     try{
     
     database.open();
     
     rs = database.query("select * from xlh_newsinfo where newsid="+newid);

	   if(rs.next()){
	    bt = rs.getString("bt");
	    fbsj = rs.getString("fbsj");
     }else{
     	
     	%>
			     	<SCRIPT LANGUAGE="JavaScript">
					<!--
			        alert("��ȷ����Ϣ�Ƿ��ѷ�����");
			        
					history.back();
					
					
					//-->
					</SCRIPT>
     	<%
     	
     	}
  
     }
     catch(Exception e)
    {
    
    }



%>

<html>
<head>
<title>��Ϣ����_��ͼƬ����</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="shareFunction.js"></script>
</head>
<body style="text-align:center;">

<table width="100%" height="340" border="0" align="center" cellpadding="0" cellspacing="1" bordercolordark="#FFFFFF" bgcolor="#FFFFFF">
<tr>
<td width="100%" valign="top">
<table width="100%" height="104" border="0" align="center" cellpadding="0" cellspacing="1" bordercolordark="#FFFFFF" bgcolor="#cccccc">
<tr>
<td width="113" height="26" align="center" bgcolor="#FFFFFF" class="text2">��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
<td width="1291" bgcolor="#FFFFFF"> &nbsp;<%=bt%> </td>
</tr>
<tr>
<td width="113" height="26" align="center" bgcolor="#FFFFFF" class="text2">����ʱ�䣺</td>
<td width="1291" bgcolor="#FFFFFF"> &nbsp;<%=fbsj%></td>
</tr>
<td width="" height="26" align="left" bgcolor="#FFFFFF" class="text2" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<A HREF="#" onclick="javascript:MM_openChildWindow('fz_newsupfile.jsp?id=<%=newid%>','','width=600,height=200');">&nbsp;���ͼƬ</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<FONT COLOR="#CC0000">*   �����ڴ˴����ͼƬ��</FONT></td>
<tr>
<td width="113" height="26" align="center" bgcolor="#FFFFFF" class="text2" colspan="2">ͼƬ����ͼ��</td>
</tr>
<tr>
	<td width="1291" height="26" align="center" bgcolor="#FFFFFF" class="text2" colspan="3">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
	<%

	   ArrayList arrayList = new ArrayList ();
	   ManageSLFile manageSLFile = new ManageSLFile();
	   arrayList = manageSLFile.getSpFile(newid);
	   PicFile tOperationWorkflowInfoFile = new PicFile();
		    for(int i=0;i<arrayList.size();i++)
			 {
	            tOperationWorkflowInfoFile = (PicFile)arrayList.get(i);
	            out.println("<td width=105 heigth=100 align=center><img src='../../upload/xwtp/"+tOperationWorkflowInfoFile.getFilePath()+"' width='100' height='80'/>");
	            out.println("<span ><A HREF='deletefile.jsp?fileID="+tOperationWorkflowInfoFile.getFileID()+"&newid="+newid+"'>ɾ��</A></span></td>");
		        if((i+1)%6==0){
		        out.print("</tr><tr>");
		        } 				
			 }	
		 
	%>
		</tr>
		</table>
	</td>
</tr>

</table>
</body>                                                            
</html>

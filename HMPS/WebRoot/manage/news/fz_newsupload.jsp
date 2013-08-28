<%@ page language="java"%>
<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.File"%>
<%@ page import="com.hrbxlh.form.*"%>
<%@ page import="com.hrbxlh.dao.*"%>
<%@ page import="com.hrbxlh.common.*"%>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>


<jsp:useBean id="myupload" scope="page" class="com.jspsmart.upload.SmartUpload" />


<%
 request.setCharacterEncoding("gb2312"); 
    String attachment = "";
    String savePath = "";
    String src_name = "";
    String file_path = "";
    String direct = "";
    int count = 0;
    String sm = "";
	
	String folderName = "";
	String tempPath = "";
     String stime = "";
     String Type = "";
     String id = "";
     int type=0;
	String nn ="";
try{
	    myupload.initialize(pageContext);        
		myupload.upload();

                //提取系统发布目录
               
		        savePath=Global.getInstance().getPath();
		       
		        attachment = myupload.getRequest().getParameter("attachment");
                id = myupload.getRequest().getParameter("id"); 
                sm = myupload.getRequest().getParameter("sm"); 
				//folderName="attechmentcolder";
				
				
				
				
				  savePath = savePath + "\\upload\\xwtp";
				  tempPath = "\\upload\\xwtp";
				  
		int MaxFileSize=1*1024*1024;	//上传文件的大小为4M
		int tempfilesize=0;

        Long ltime = new Long(System.currentTimeMillis());  //获得当前系统时间
         stime = ltime.toString();
		for (int i=0;i<myupload.getFiles().getCount();i++){
     
			com.jspsmart.upload.File myFile = myupload.getFiles().getFile(i);
						 //创建上传文件夹
                         java.io.File dir = new java.io.File(savePath);
						 dir.mkdir();

				 java.io.File temp = java.io.File.createTempFile(folderName+"CRE",stime+"."+myFile.getFileExt(),dir);
               
	        
	         
			//取得文件名		  
			src_name=myFile.getFileName();

			//判断文件扩展名
		
             if (!myFile.getFileExt().equals("gif")&&!myFile.getFileExt().equals("GIF")&&(type==1||type==2))
			   {
				out.println("<SCRIPT LANGUAGE='JavaScript'>");
				out.println("<!--");
				out.println("alert('错误：请上传.gif图片！');");
				out.println("window.history.go(-1);");
				out.println("//-->");
				out.println("</SCRIPT>");
				out.close();
				return;
			    
			   }

            //判断文件大小
			if (myFile.getSize()>MaxFileSize) {
			
				out.println("<SCRIPT LANGUAGE='JavaScript'>");
				out.println("<!--");
				out.println("alert('消息：你上传的附件总容量已经超过了1M了！');");
				out.println("window.history.go(-1);");
				out.println("//-->");
				out.println("</SCRIPT>");
				out.close();
				return;
		      }

            
			//存储文件，并得到上传路径
            myFile.saveAs(temp.getAbsolutePath(),myupload.SAVE_PHYSICAL);			
			file_path=temp.getAbsolutePath();

			
			count++;
        }
	}catch(Exception e){
              
               
	}
   //上传成功，进行业务处理


	if(count>0){
		
        int t = file_path.indexOf(tempPath);
        savePath = file_path.substring(t);
        nn = savePath.substring(savePath.lastIndexOf("\\"));

	     	PicFile tOperationWorkflowInfoFile = new PicFile();
      	ManageSLFile manageSLFile = new ManageSLFile();
				tOperationWorkflowInfoFile.setInfoID(Integer.parseInt(id));
		 		tOperationWorkflowInfoFile.setFileName(src_name);
		 		tOperationWorkflowInfoFile.setFilePath(nn);
		 		tOperationWorkflowInfoFile.setSm(sm);
		 	  manageSLFile.InsertSLFile(tOperationWorkflowInfoFile);
	     
				     Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));
			Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
	     try
			  {
			    database.open();
			    int a = database.update("update xlh_newsinfo set isImgNews = 'true' where newsid='"+id+"' ");
			  }
			  catch (Exception e) 
			  {
			    (new xlh.Log()).log("error", "更新新闻状态.jsp：" + Global.getInstance().getTime() + "：" + e.getMessage());
			  }
			  finally
			  {
			    database.close();
			  }
  
		%>
		<SCRIPT LANGUAGE="JavaScript">
		
                alert("文件上传成功！");
        
		window.opener.document.location.reload();
		window.close();
		
		
		</SCRIPT>
		<%
	}
	else{//上传失败
		%>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
		
		alert("上传失败！");
                history.back();
		//window.close();
		//-->
		</SCRIPT>
		<%
	}
%>

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

                //��ȡϵͳ����Ŀ¼
               
		        savePath=Global.getInstance().getPath();
		       
		        attachment = myupload.getRequest().getParameter("attachment");
                id = myupload.getRequest().getParameter("id"); 
                sm = myupload.getRequest().getParameter("sm"); 
				//folderName="attechmentcolder";
				
				
				
				
				  savePath = savePath + "\\upload\\xwtp";
				  tempPath = "\\upload\\xwtp";
				  
		int MaxFileSize=1*1024*1024;	//�ϴ��ļ��Ĵ�СΪ4M
		int tempfilesize=0;

        Long ltime = new Long(System.currentTimeMillis());  //��õ�ǰϵͳʱ��
         stime = ltime.toString();
		for (int i=0;i<myupload.getFiles().getCount();i++){
     
			com.jspsmart.upload.File myFile = myupload.getFiles().getFile(i);
						 //�����ϴ��ļ���
                         java.io.File dir = new java.io.File(savePath);
						 dir.mkdir();

				 java.io.File temp = java.io.File.createTempFile(folderName+"CRE",stime+"."+myFile.getFileExt(),dir);
               
	        
	         
			//ȡ���ļ���		  
			src_name=myFile.getFileName();

			//�ж��ļ���չ��
		
             if (!myFile.getFileExt().equals("gif")&&!myFile.getFileExt().equals("GIF")&&(type==1||type==2))
			   {
				out.println("<SCRIPT LANGUAGE='JavaScript'>");
				out.println("<!--");
				out.println("alert('�������ϴ�.gifͼƬ��');");
				out.println("window.history.go(-1);");
				out.println("//-->");
				out.println("</SCRIPT>");
				out.close();
				return;
			    
			   }

            //�ж��ļ���С
			if (myFile.getSize()>MaxFileSize) {
			
				out.println("<SCRIPT LANGUAGE='JavaScript'>");
				out.println("<!--");
				out.println("alert('��Ϣ�����ϴ��ĸ����������Ѿ�������1M�ˣ�');");
				out.println("window.history.go(-1);");
				out.println("//-->");
				out.println("</SCRIPT>");
				out.close();
				return;
		      }

            
			//�洢�ļ������õ��ϴ�·��
            myFile.saveAs(temp.getAbsolutePath(),myupload.SAVE_PHYSICAL);			
			file_path=temp.getAbsolutePath();

			
			count++;
        }
	}catch(Exception e){
              
               
	}
   //�ϴ��ɹ�������ҵ����


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
			    (new xlh.Log()).log("error", "��������״̬.jsp��" + Global.getInstance().getTime() + "��" + e.getMessage());
			  }
			  finally
			  {
			    database.close();
			  }
  
		%>
		<SCRIPT LANGUAGE="JavaScript">
		
                alert("�ļ��ϴ��ɹ���");
        
		window.opener.document.location.reload();
		window.close();
		
		
		</SCRIPT>
		<%
	}
	else{//�ϴ�ʧ��
		%>
		<SCRIPT LANGUAGE="JavaScript">
		<!--
		
		alert("�ϴ�ʧ�ܣ�");
                history.back();
		//window.close();
		//-->
		</SCRIPT>
		<%
	}
%>

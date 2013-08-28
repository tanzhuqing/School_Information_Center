<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>

<%
//��Ϣ����_ά��
String operate = request.getParameter("operate");
//ȡ����վ�����
String sszd = "8";

DiskFileUpload dfu = null;
List l = null;
Iterator itr = null;
FileItem fi = null;
File f = null;

boolean isok = false;
String news_tp = "";
String message = "";

ResultSet rs = null;
Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));
Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");

  String bt = "";
  String zz = "";
  String lm = "";
  String zw = "";
  String tp = "";
  String tp2 = "";
  String fbsj = "";
  String newsid = "";
  String ms ="";
if (operate.equals("add"))
{
  //Ĭ����Ϣ  String djl = "0";
  String zrbj = "����Ա";

  try
  {
    database.open();
    
    dfu = new DiskFileUpload();
    dfu.setSizeMax(3000000);
    dfu.setSizeThreshold(4096); //�����10M�ļ�
    dfu.setRepositoryPath("D:\\");
     
    l = dfu.parseRequest(request);
    itr = l.iterator();
    while (itr.hasNext())
    {
      fi = (FileItem) itr.next();
 
      if (fi.isFormField()) //���ļ���Ϣ
      {
        if (fi.getString().equals(""))
          continue;
    
        if (fi.getFieldName().equals("bt"))
          bt = fi.getString();

        if (fi.getFieldName().equals("zz"))
          zz = fi.getString();
  
        if (fi.getFieldName().equals("lm"))
          lm = fi.getString();

        if (fi.getFieldName().equals("zw"))
          zw = fi.getString();

        if (fi.getFieldName().equals("ms"))
              ms = fi.getString();
          
        if (fi.getFieldName().equals("fbsj"))
          fbsj = fi.getString();
      }
  
      if (!fi.isFormField()) //�ļ���Ϣ
      {
        if (fi.getName() == null || fi.getName().equals("") || (fi.getSize() == 0))
          continue;

        //�ļ���
        tp = sszd + "-" + Global.getInstance().getYear() + Global.getInstance().getMonth() + Global.getInstance().getDay() + Global.getInstance().getHour() + Global.getInstance().getMinute() + Global.getInstance().getSecond() + fi.getName().substring(fi.getName().lastIndexOf('.'), fi.getName().length());
        f = new File(Global.getInstance().getPath() + "\\upload\\xwtp");
        fi.write(new File(f, tp));
      }
    }
    
    if(tp.equals("")){
      news_tp = "0";
    }else{
      news_tp = "1";
    }
    
    //�ж�ͬվ�㡢ͬ��Ŀ�������������š�
    rs = database.query("select * from xlh_newsinfo where sszd = '"+sszd+"' and lmid = '"+lm+"' and bt = '"+bt+"'");
    if(rs.next())
    {
      message = "�����Ѵ���";
    }
    else
    {
   
      int a = database.update("insert into xlh_newsinfo(sszd, lmid, bt, zw, tp, news_tp, zz, zrbj, fbsj ,sfqy ,isImgNews,ms) " +
                            "values('"+sszd+"', '"+lm+"', '"+bt+"','"+zw+"', '"+tp+"', '"+news_tp+"', '"+zz+"', '"+zrbj+"', '"+fbsj+"', '0','false','"+ms+"')");
      if(a > 0)
        isok = true;
      else
        message = "�������ʧ��";
        
    }
  }
  catch (Exception e) 
  {
    (new xlh.Log()).log("error", "fz_newswh.jsp��" + Global.getInstance().getTime() + "��" + e.getMessage());
  }
  finally
  {
    database.close();
  }
  
  if(isok)
  {
    out.println("<script>location.href='fz_news.jsp?lmid="+lm+"'</script>");
  }
  else
  {
    out.println("<script>alert('"+message+"')</script>");
    out.println("<script>location.href='fz_news.jsp?lmid="+lm+"'</script>");
  }
}


if (operate.equals("sh"))
{
  String lmid_temp = request.getParameter("lmid_temp");
  newsid = request.getParameter("newsid");
  String sfqy = request.getParameter("sfqy");
  
  try
  {
    database.open();
  
    int a = database.update("update xlh_newsinfo set sfqy = '"+sfqy+"' where sszd = '"+sszd+"' and newsid = " + newsid);
    
    if(a > 0)
    {
      isok = true;
    }
  }
  catch (Exception e) 
  {
    (new xlh.Log()).log("error", "fz_newswh.jsp��" + Global.getInstance().getTime() + "��" + e.getMessage());
  }
  finally
  {
    database.close();
  }
  
  if(isok)
  {    
    
    out.println("<script>location.href='fz_newslb.jsp?lmid="+lmid_temp+"'</script>");
    
  }
  else
  {
    out.println("<script>alert('������������ʧ��')</script>");
    out.println("<script>location.href='fz_newslb.jsp?lmid="+lmid_temp+"'</script>");
  }
}


  
if (operate.equals("delete"))
{
  String lmid_temp = request.getParameter("lmid_temp");
  newsid = request.getParameter("newsid");
 
  try
  {
    database.open();
    
    int a = database.update("delete from xlh_newsinfo where sszd = '"+sszd+"' and newsid = '"+newsid+"'");
    
    if(a > 0)
    {
      isok = true;
    }
  }
  catch (Exception e) 
  {
    (new xlh.Log()).log("error", "fz_newswh.jsp��" + Global.getInstance().getTime() + "��" + e.getMessage());
  }
  finally
  {
    database.close();
  }
  
  if(isok)
  {
    out.println("<script>location.href='fz_newslb.jsp?lmid="+lmid_temp+"'</script>");
  }
  else
  {
    out.println("<script>alert('ɾ������ʧ��')</script>");
    out.println("<script>location.href='fz_newslb.jsp?lmid="+lmid_temp+"'</script>");
  }
}%>


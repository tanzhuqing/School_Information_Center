<%@ page contentType="text/html;charset=gbk"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="xlh.Database"%>
<%@ page import="xlh.Global"%>
<%@ page import="xlh.*"%>

<%
//�����б�
Global.getInstance().setPath(request.getSession(false).getServletContext().getRealPath(""));
Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
int length_bt = 40; //���ݿ��
int length_ms=  60;
String newsid = request.getParameter("newsid"); 
Vector v1 = null; //Ĭ���б�   ��������ҳ
Vector v2 = null; //Ĭ���б�   ��������ҳ
Properties p1 = null; //һ������
ResultSet rs1 = null; //���ݼ�
String sj = "";
String href = "http://125.223.113.81:8080/";

try
{
  database.open();
  v1 = new Vector();
  rs1 = database.query("select filePath,sm from t_pic_file where infoID ="+newsid+" order by fileID desc");
  while(rs1.next())
  {

    p1 = new Properties();
    
    p1.setProperty("sm", rs1.getString("sm"));

    if(rs1.getString("filePath").equals(""))
      p1.setProperty("filePath", "");
    else
      p1.setProperty("filePath", href+"HMPS/upload/xwtp/"+rs1.getString("filePath"));

      
    v1.add(p1);
  }

}
catch(Exception e)
{

}
finally
{
  database.close();
}
%>
{"newsinfoimglist":[
			        <%for (int ic = 0; ic < v1.size(); ic++) {
			        		if(ic>0){
			        		out.print(",");
			        		}
			        %>
			        
{
"img":"<%=(((Properties) v1.get(ic)).getProperty("filePath"))%>",
"Sm":"<%=(((Properties) v1.get(ic)).getProperty("sm"))%>"
}
<%}%>
]}

<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>

<%
//ȡ����վ�����
String sszd = "8"
//����newsid
String newsid = request.getParameter("newsid");
String lmid = "";
String bt = "";
String bt2 = "";
String zz = "";
String zw = "";
String tp = "";
String fbsj = "";
String lmmc = "";
Properties p = null;
Vector v = new Vector();
ResultSet res = null;
ResultSet rs = null;
String sqltmp ="";

Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
try
{
  database.open();
  //ȡ������Ϣ
  res = database.query("select n.*,c.lmmc from xlh_newsinfo as n ,xlh_column as c  where n.sszd = '"+sszd+"' and n.newsid = '"+newsid+"' and c.lmid = n.lmid");
  if(res.next())
  {
    bt = res.getString("bt");
    zz = res.getString("zz");
    zw = res.getString("zw");
    tp = res.getString("tp");
    if(tp.equals(""))
    tp = "";
    lmid = res.getString("lmid");
    lmmc = res.getString("lmmc");
    fbsj = res.getString("fbsj");
  }
  
 
			  sqltmp = "select * from xlh_column where sszd = '"+sszd+"' and sfqy = '1'  ";
			  rs = database.query(sqltmp);
			  while(rs.next())
			  {
			    p = new Properties();
			    p.setProperty("lmid", rs.getString("lmid").trim());
			    p.setProperty("lmmc", rs.getString("lmmc").trim());
			    v.add(p);
			  }

}
catch (Exception e) 
{
  
}
finally
{
  database.close();
}
%>
<html>
<head>
<title>��Ϣ����_�޸�</title>
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
  
  document.newsadd.submit();
}

</script>
</head>
<body style="text-align:center;">
<table width="100%" height="25" border=0 cellPadding=0 cellSpacing=0  background="images/pic_gzdtbg.gif">
    <tr>
      <td width="28" height="25" align="left" valign="top"><IMG height=25 alt="" src="images/pic_glhongdian.jpg" width=28></td>
      <td width="100%" height="25" align="left" valign="middle" background="images/title_1.jpg" style="PADDING-TOP: 3px"><STRONG>&nbsp;��Ϣ����_�޸�</STRONG></td>
    </tr>
</table>
<table width=100% height="310" border="0" align="center" cellspacing="0" bgcolor="#FFFFFF">
  <tr><td width="100%">
  <form name="newsadd" method="post" action="fz_newswh.jsp?operate=modify" enctype="multipart/form-data">
    <table width="100%" height="100%" border="0" cellspacing="1" bordercolordark="#FFFFFF" bgcolor="#CCCCCC">
      <tr><td align="center" bgcolor="#FFFFFF" class="text2">��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
          <td bgcolor="#FFFFFF"> &nbsp;
            <input type="text" name="bt" value="<%=bt%>" style="width:560px;"></td>
      </tr>
       <tr><td align="center" bgcolor="#FFFFFF" class="text2">����ʱ�䣺</td>
          <td bgcolor="#FFFFFF"> &nbsp;
            <input type="text" name="fbsj" value="<%=fbsj%>" style="width:560px;"></td>
      </tr>
      <tr>
        <td align="center" bgcolor="#FFFFFF" class="text2">��&nbsp;&nbsp;&nbsp;&nbsp;Ŀ��</td>
        <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0">
           <tr>
							<td>&nbsp;&nbsp;
							<select name="lm">
							<option value="<%=lmid%>"><%=lmmc%></option>
							<%for (int i = 0; i < v.size(); i++)
							{%>
							<option value="<%=(((Properties) v.get(i)).getProperty("lmid"))%>"><%=(((Properties) v.get(i)).getProperty("lmmc"))%></option>
							<%}%>
							</select>
							</td>
           </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#FFFFFF" class="text2">��&nbsp;&nbsp;&nbsp;&nbsp;�ߣ�</td>
          <td bgcolor="#FFFFFF"> &nbsp;
            <input type="text" name="zz" value="<%=zz%>" style="width:260px;"></td>
      </tr>
      <tr><td align="center" bgcolor="#FFFFFF" class="text2">��ҳͼƬ��</td>
          <td bgcolor="#FFFFFF"> &nbsp;
            <input type="file" name="tp" style="width:560px;" value="<%=tp%>"></td>
      </tr>
    </table>
    
    <input type="hidden" name="zw" value="">
    <input type="hidden" name="newsid" value="<%=newsid%>">
    <input type="hidden" name="tp2" value="<%=tp%>">
  </form>     
  </td><tr>
  <tr><td height="288" width="100%" colspan="2" align="center" valign="top">
    <textarea name="zw" value=""><%=zw%></textarea>
	</td></tr>  
  <tr><td  width="100%" height="37" colspan="2" align="center" valign="middle" bordercolor="#ABCBCB" bordercolordark="#FFFFFF">
       <input type="button" class="anniu" value="ȷ ��" onClick="work_do();">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="anniu" value="ȡ ��" onClick="window.close();">
  <td width="0%"></td>
  </tr>
</table>    
</body>                                                            
</html>

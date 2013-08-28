<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="xlh.Database" %>
<%@ page import="xlh.Global" %>
<%
//������Ŀ���뼰FORM�ύ��Ϣ
String lmid = request.getParameter("lmid");
if(lmid == null) lmid = "";

//ȡ����վ�����
String sszd = "8";



List lm = new ArrayList();
List lm_all = new ArrayList();
Properties p = null;
ResultSet rs1 = null;


Database database = new Database(Global.getInstance().getPath() + "\\WEB-INF\\config.xml");
try
{
  database.open();

  //ȡ��Ŀ
  rs1 = database.query("select a.*,b.lmmc from xlh_newsinfo a,xlh_column b where a.lmid = b.lmid and b.lmid = '"+lmid+"' and b.sfqy = '1'  order by a.newsid desc");
  while (rs1.next())
  {
    p = new Properties();
    p.setProperty("lmid", rs1.getString("lmid"));
    p.setProperty("lmmc", rs1.getString("lmmc"));
    p.setProperty("newsid", rs1.getString("newsid"));
    p.setProperty("sszd", rs1.getString("sszd"));
    p.setProperty("bt", rs1.getString("bt"));
    p.setProperty("zrbj", rs1.getString("zrbj"));
    p.setProperty("fbsj", rs1.getString("fbsj"));
    p.setProperty("sfqy", rs1.getString("sfqy"));
    lm_all.add(p);
  }
 
}
catch (Exception e)
{
  (new xlh.Log()).log("error", "fz_newslb.jsp��" + Global.getInstance().getTime() + "��" + e.getMessage());
}
finally
{
  database.close();
}


//���´����ҳ�б���ʾ
int rpage = 10; //ÿҳ��¼��
int npage = 0; //��ҳ��
int nrow = 0; //�ܼ�¼��
int i = 0;
int j = 0;

int cpage = 1; //��ǰҳ��
if (request.getParameter("cpage") != null){
  cpage = Integer.parseInt(request.getParameter("cpage"));
}
nrow = lm_all.size();
if (nrow < rpage)
  npage = 1;
else
{
  if ((nrow % rpage) == 0)
    npage = nrow / rpage;
  else
    npage = nrow / rpage + 1;
}

i = 0;
while (i < lm_all.size())
{
  if (((cpage == 1) && (i < rpage)) || ((cpage != 1) && (i >= (cpage - 1) * rpage) && (i < cpage * rpage)))
  {
    p = new Properties();
    p.setProperty("lmid", ((Properties) lm_all.get(i)).getProperty("lmid"));
    p.setProperty("lmmc", ((Properties) lm_all.get(i)).getProperty("lmmc"));
    p.setProperty("newsid", ((Properties) lm_all.get(i)).getProperty("newsid"));
    p.setProperty("sszd", ((Properties) lm_all.get(i)).getProperty("sszd"));
    p.setProperty("bt", ((Properties) lm_all.get(i)).getProperty("bt"));
    p.setProperty("zrbj", ((Properties) lm_all.get(i)).getProperty("zrbj"));
    p.setProperty("fbsj", ((Properties) lm_all.get(i)).getProperty("fbsj"));
    p.setProperty("sfqy", ((Properties) lm_all.get(i)).getProperty("sfqy"));
    lm.add(p);
  }
  
  if (i >= cpage * rpage)
    break;
  i = i + 1;
}
%>

<html>
<head>
<title>��Ϣ����_�б�</title>
<link href="../style/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function work_go(obj) 
{
  document.newslb.action = "fz_newslb.jsp";
  document.newslb.target = "_self";
  document.newslb.lmid.value = "<%=lmid%>";
  document.newslb.cpage.value = obj;
  document.newslb.submit();
  
  return false;
}
function work_over(obj) 
{
  obj.style.backgroundColor='#EBEBEB';
}

function work_out(obj) 
{
  obj.style.backgroundColor='';
}

function work_jump(obj) 
{
  if ((obj < 1) && (event.keyCode == 13))
    alert("ҳ������С��1");
  
  if ((obj > <%=npage%>) && (event.keyCode == 13)) 
    alert("ҳ�����ܴ���" + <%=npage%>);
  
  if ((obj >= 1) && (obj <= <%=npage%>) && (event.keyCode == 13)) 
  {
    document.newslb.action = "fz_newslb.jsp";
    document.newslb.target = "_self";
    document.newslb.lmid.value = "<%=lmid%>";
    document.newslb.cpage.value = obj;
    document.newslb.submit();
  }
  
  return false;
}


/*********************************
function work_check()
{
  var i = 0;
  for (i = 0; i < document.newslb.elements.length; i++)
  {
    if(document.newslb.elements[i].name == "list" )
      if(document.newslb.elements[i].checked == true) 
      	return true;
  }
  
  alert("����ѡ��һ�����ݲ��ܽ��д���!");
  return false;
}
*********************************/
</script>
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
.alt1 {background: #FFFFFF;}
</style>
</head>
<body>
<form name="newslb" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td>
      <table width="100%" height="46" border="0" align="center" cellpadding="0" cellspacing="1">
        <tr>
          <td width="2%" height="20" align="center" bgcolor="#F5F5F5" class="text1">ѡ��</td>
          <td width="5%" align="center" bgcolor="#F5F5F5" class="text1">����ID</td>
          <td width="9%" align="center" bgcolor="#F5F5F5" class="text1">����ʱ��</td>
          <td width="8%" align="center" bgcolor="#F5F5F5" class="text1">����Ա</td>
          <td width="25%" align="center" bgcolor="#F5F5F5" class="text1">���ű���</td>
          <td width="8%" align="center" bgcolor="#F5F5F5" class="text1">������Ŀ</td>
          <td width="8%" align="center" bgcolor="#F5F5F5" class="text1">����״̬</td>
          <td colspan="7" align="center" bgcolor="#F5F5F5" class="text1">&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;</td>
        </tr>
      <%for (i = 0; i < lm.size(); i++){%>
        <tr onMouseOver="work_over(this);" onMouseOut="work_out(this);"class="alt1">         
          <td height=26 align="center" ><input name="list" type="checkbox"  value="<%=(((Properties) lm.get(i)).getProperty("lmid"))%>"></td>
          <td height=20 align="center" ><%=(((Properties) lm.get(i)).getProperty("newsid"))%></td>
          <td height=20 align="center" ><%=(((Properties) lm.get(i)).getProperty("fbsj"))%></td>
          <td height=20 align="center" ><%=(((Properties) lm.get(i)).getProperty("zrbj"))%></td>
          <td height=20 align="center" ><%=(((Properties) lm.get(i)).getProperty("bt"))%></td>
          <td height=20 align="center" ><%=(((Properties) lm.get(i)).getProperty("lmmc"))%></td>
          <td height=20 align="center" ><%if(((Properties) lm.get(i)).getProperty("sfqy").equals("1")){%>���ͨ��<%}else{%>δ���<%}%></td>

          <td width="5%" align="center" nowrap>
            <%if(((Properties) lm.get(i)).getProperty("sfqy").equals("1")){%>
              <a href="fz_newswh.jsp?operate=sh&newsid=<%=(((Properties) lm.get(i)).getProperty("newsid"))%>&sfqy=0&lmid_temp=<%=lmid%>">����</a>
            <%}else{%>
              <a href="fz_newswh.jsp?operate=sh&newsid=<%=(((Properties) lm.get(i)).getProperty("newsid"))%>&sfqy=1&lmid_temp=<%=lmid%>">����</a>
            <%}%>
          </td>

          <td width="2%" align="center" nowrap><a href="fz_newspicadd.jsp?lmid=<%=lmid %>&newid=<%=(((Properties) lm.get(i)).getProperty("newsid"))%>">ͼƬ��</a></td>

          <td width="2%" align="center" nowrap><a href="fz_newswh.jsp?operate=delete&newsid=<%=(((Properties) lm.get(i)).getProperty("newsid"))%>&lmid_temp=<%=lmid%>">ɾ��</a></td>
        </tr>
      <%}%>
      </table>
  </td></tr>
  <tr><td height="38" align="right">
      <table border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td height="20" valign="middle" class="td">��<%=nrow%>�� ��<%=cpage%>/<%=npage%>ҳ</td>
        <td valign="middle" class="td">
         <%if(cpage>1){%>&nbsp;
         <a href="" onClick="return work_go(<%=(cpage - 1)%>);">��һҳ</a><%}else{%>&nbsp;��һҳ
         <%}if(cpage < npage){%>&nbsp;
         <a href="" onClick="return work_go(<%=(cpage + 1)%>);">��һҳ</a><%}else{%>&nbsp;��һҳ
         <%}%>
        </td>
        <td valign="top" class="td">&nbsp;��<input name="seach" type="text" class="box" style="height:16px; width:20px;" value="<%=cpage%>" onKeyDown="work_jump(this.value);">ҳ</td>
        </tr>
      </table>
  </td></tr>
</table>
<input type="hidden" name="lmid" value="<%=lmid%>">
<input type="hidden" name="cpage" value="<%=cpage%>">
</form>
</body>
</html>

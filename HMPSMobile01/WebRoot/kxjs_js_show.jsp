<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>

<%
String jxl = request.getParameter("jxl");
String period = request.getParameter("period");
String name = "第1教学楼";

Vector v = new Vector();
Vector v_line = null;
Properties p = null;
int i = 0;
int j = 0;

//*******************************测试数据开始
if (period.equals("0"))
{
  v_line = new Vector();
  p = new Properties();
  p.setProperty("name", "H2107");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "08:00");
  p.setProperty("free", "Y");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "08:55");
  p.setProperty("free", "N");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "09:55");
  p.setProperty("free", "Y");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "10:50");
  p.setProperty("free", "N");
  v_line.add(p);

  v.add(v_line);

  v_line = new Vector();
  p = new Properties();
  p.setProperty("name", "H2108");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "08:00");
  p.setProperty("free", "N");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "08:55");
  p.setProperty("free", "N");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "09:55");
  p.setProperty("free", "Y");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "10:50");
  p.setProperty("free", "Y");
  v_line.add(p);

  v.add(v_line);
}

if (period.equals("1"))
{
  v_line = new Vector();
  p = new Properties();
  p.setProperty("name", "H2107");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "13:30");
  p.setProperty("free", "N");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "14:25");
  p.setProperty("free", "N");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "15:20");
  p.setProperty("free", "Y");
  v_line.add(p);
  
  p = new Properties();
  p.setProperty("time", "16:15");
  p.setProperty("free", "Y");
  v_line.add(p);
  
  v.add(v_line);
}
//***************************************测试数据结束
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>空闲教室----教室</title>
    <link href="./jquery-mobile/jquery.mobile-1.0.1.css" rel="stylesheet" type="text/css"/>
    <script src="./jquery-mobile/jquery-1.7.1.js" type="text/javascript"></script>
    <script src="./department.js" type="text/javascript"></script>
    <script src="./jquery-mobile/jquery.mobile-1.0.1.js" type="text/javascript"></script>
</head>
<body>
<div data-role="page" id="home">
	<div data-role="header" data-theme="b"><a href="kxjs_jxl_show.jsp" target="_self" data-role="button" data-icon="back">教学楼</a>
        <h1>空闲教室----<%=name%></h1>
        <div data-role="navbar">
        	<ul>
            	<li><a href="kxjs_js_show.jsp?jxl=<%=jxl%>&period=0" target="_self" data-icon="home" data-iconpos="top">上午</a></li>
                <li><a href="kxjs_js_show.jsp?jxl=<%=jxl%>&period=1" target="_self" data-icon="grid" data-iconpos="top">下午</a></li>
                <li><a href="kxjs_js_show.jsp?jxl=<%=jxl%>&period=2" target="_self" data-icon="star" data-iconpos="top">晚上</a></li>
            </ul>
         </div>
    </div>
    <div data-role="content" data-theme="c">
        <table width="100%" border="0" cellpadding="0" cellspacing="2">
      <%for (i = 0; i < v.size(); i++)
        {
           v_line = (Vector) v.get(i);
      %>
	      <tr>
	         <td height="30" align="center" valign="middle" style="background-color:#99CC99"><%=(((Properties) v_line.get(0)).getProperty("name"))%></td>
        <%for (j = 1; j < v_line.size(); j++)
          {
            if (((Properties) v_line.get(j)).getProperty("free").equals("N"))
            {%>
            <td align="center" valign="middle" style="background-color:#CCFFCC"><%=(((Properties) v_line.get(j)).getProperty("time"))%></td>
          <%}
            else
            {%>
            <td align="center" valign="middle" style="background-color:#66FF00"><%=(((Properties) v_line.get(j)).getProperty("time"))%></td>
        <%  }
          }%>
        </tr>
      <%}%>
         </table>
    	</div>
        <div data-role="footer" data-theme="c" class="footer-text" align="center">
        	<br>
            <br>
            <small>&copy;2012 网络信息中心</small>
        </div>
    
</div>

</div>
	             
</body>	
</html>
